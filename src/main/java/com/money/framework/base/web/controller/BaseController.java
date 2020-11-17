package com.money.framework.base.web.controller;

import com.money.framework.base.entity.OperationalEntity;
import com.money.framework.base.exception.PandabusSpecException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Objects;

@Controller
public abstract class BaseController {

    protected Logger getLogger() {
        return LoggerFactory.getLogger(this.getClass());
    }

    protected void goToInfoPage(String message, HttpSession session, HttpServletRequest request, HttpServletResponse response) throws IOException {
        session.setAttribute("message", message);
        String path = request.getContextPath() + "/info";
        getLogger().info("path:" + path);
        response.sendRedirect(path);
    }

    protected void requireGroupId(OperationalEntity operationalEntity) {
        if (Objects.isNull(operationalEntity.getGroupId())) {
            throw new PandabusSpecException("当前账号无所属公司");
        }
    }
}
