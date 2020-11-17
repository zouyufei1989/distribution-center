package com.money.custom.task;

import com.google.common.collect.Maps;
import com.money.custom.entity.ScheduleConfig;
import com.money.custom.service.ScheduleConfigService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;

import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
public abstract class ConfigurableTaskBase extends TaskBase {

    @Autowired
    ScheduleConfigService scheduleConfigService;

    @Transactional
    @Scheduled(cron = "0 0/1 * * * ? ")
    public void run() {
        if (validateExecutorPerMinute(this)) {
            List<ScheduleConfig> configs = scheduleConfigService.runnable(this.getClass().getName());
            if (CollectionUtils.isEmpty(configs)) {
                getLogger().warn("无任务配置");
                return;
            }

            getLogger().info("任务开始");
            long start = System.currentTimeMillis();

            configs.forEach(config -> {
                Map<String, String> params = getParams(config.getParams());
                execute(params);

                getLogger().info("任务完成,耗时{} 毫秒", System.currentTimeMillis() - start);
            });
        } else {
            getLogger().warn("任务已分配");
        }
    }

    abstract void execute(Map<String, String> params);

    protected Map<String, String> getParams(String str) {
        Map<String, String> map = Maps.newHashMap();

        if (StringUtils.isEmpty(str)) {
            return map;
        }

        String[] pairs = str.split("&");
        for (String pair : pairs) {
            String[] kv = pair.split("=");
            map.put(kv[0], kv[1]);
        }

        return map;
    }

    String useParamsIfPresent(Map<String, String> params, String key, Object def) {
        Assert.notNull(def, "参数默认值不可以为Null");
        if (StringUtils.isNotEmpty(params.get(key))) {
            def = params.get(key);
        }
        return String.valueOf(def);
    }

    Integer useIntParamsIfPresent(Map<String, String> params, String key, Object def) {
        if (StringUtils.isNotEmpty(params.get(key))) {
            def = params.get(key);
        }
        return Objects.isNull(def) ? null : Integer.parseInt(String.valueOf(def));
    }

}
