package com.money.custom.rabbitmq.receiver;

import com.gexin.fastjson.JSON;
import com.money.custom.dao.HttpLogDao;
import com.money.custom.entity.HttpLog;
import com.money.custom.entity.enums.HttpLogTypeEnum;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.framework.util.DateUtils;
import com.money.framework.util.EnumUtils;
import com.money.framework.util.FileUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.Date;
import java.util.Map;

@Component
@RabbitListener(queues = QueueConsts.HTTP_LOG_QUEUE)
public class HttpLogReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(HttpLogReceiver.class);

    @Autowired
    HttpLogDao httpLogDao;

    @RabbitHandler
    public void process(Map message) {
        if (message.size() == 0) {
            return;
        }
        HttpLog log = (HttpLog) message.get("log");
        if (StringUtils.isNotEmpty(log.getResponse()) && log.getResponse().length() > 5000) {
            recordToFile(log);
            log.setResponse(log.getResponse().substring(0, 5000));
        }

        httpLogDao.add(log);
        LOG.info("添加http访问记录");
    }

    private static void recordToFile(HttpLog log) {

        String logType = null;
        try {
            logType = EnumUtils.getByValue(HttpLogTypeEnum.class, log.getType()).get().toString();
        } catch (Exception e) {
            e.printStackTrace();
            logType = "unknown";
        }
        String logName = logType + "_" + DateUtils.format(new Date(), "yyyyMMdd_HHmmss");

        String filePath = "";
        try {
            filePath = FileUtils.saveToFile("httpLogs", JSON.toJSONString(log), logName);
        } catch (IOException e) {
            e.printStackTrace();
            LOG.error("recordToFile fail", e);
        }

        LOG.info("记录http日志{}", filePath);
    }

}