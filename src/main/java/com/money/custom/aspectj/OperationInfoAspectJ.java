package com.money.custom.aspectj;

import com.money.custom.entity.Consts;
import com.money.custom.entity.User;
import com.money.framework.base.entity.ExcelMultipartFile;
import com.money.framework.base.entity.OperationalEntity;
import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.aspectj.lang.annotation.Pointcut;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import org.springframework.web.context.request.RequestContextHolder;
import org.springframework.web.context.request.ServletRequestAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.Arrays;

@Component
@Aspect
public class OperationInfoAspectJ {

    final static Logger logger = LoggerFactory.getLogger(OperationInfoAspectJ.class);

    /**
     * 对所有controller切片 (方法说明描述)
     *
     * @author yufei.zou
     */
    @Pointcut("within(@org.springframework.stereotype.Controller *)")
    public void anyMethod() {
    }

    @Before(value = "anyMethod()")
    public void process(JoinPoint point) {
        // 访问目标方法的参数：
        Object[] args = point.getArgs();

        HttpServletRequest request = ((ServletRequestAttributes) RequestContextHolder.getRequestAttributes()).getRequest();
        HttpSession session = request.getSession();
        User loginUser = ((User) session.getAttribute(Consts.LOGIN_USER));
        final String userId = loginUser == null ? "" : String.valueOf(loginUser.getId());
        final String ip = getRemoteHost(request);
        final Integer groupId = loginUser == null ? null : loginUser.getGroupId();

        Arrays.stream(args).filter(arg -> arg instanceof OperationalEntity).forEach(arg -> {
            ((OperationalEntity) arg).setOperationInfo(userId, ip, groupId);
            ((OperationalEntity) arg).setLoginUser(loginUser);
        });

        Arrays.stream(args).filter(arg -> arg instanceof ExcelMultipartFile).forEach(arg -> {
            ((ExcelMultipartFile) arg).getOperationalEntity().setGroupId(groupId);
            ((ExcelMultipartFile) arg).getOperationalEntity().setUpdater(userId);
            ((ExcelMultipartFile) arg).getOperationalEntity().setCreator(userId);
        });


    }

    public static String getRemoteHost(HttpServletRequest request) {
        String ip = request.getHeader("x-forwarded-for");
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getHeader("WL-Proxy-Client-IP");
        }
        if (ip == null || ip.length() == 0 || "unknown".equalsIgnoreCase(ip)) {
            ip = request.getRemoteAddr();
        }
        return StringUtils.equals(ip, "0:0:0:0:0:0:0:1") ? "127.0.0.1" : ip;
    }

}