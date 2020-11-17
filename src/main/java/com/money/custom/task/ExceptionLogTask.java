package com.money.custom.task;

import com.money.custom.entity.ExceptionLog;
import com.money.custom.service.ExceptionLogService;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.io.FileUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.condition.ConditionalOnExpression;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@ConditionalOnExpression("'${switch.task}'.equals('on')")
@Component
public class ExceptionLogTask extends ConfigurableTaskBase {

    @Value("${log.error}")
    String errorLog;
    final String EXCEP_FLAG = "#ERROR#";
    @Autowired
    ExceptionLogService service;

    @Override
    void execute(Map<String, String> params) {
        try {
            errorLog = useParamsIfPresent(params, "logName", errorLog);
            File f = new File(errorLog);
            List<String> lines = FileUtils.readLines(f, "UTF-8");
            if (CollectionUtils.isEmpty(lines)) {
                return;
            }

            List<ExceptionLog> exceptionLogs = new ArrayList<>();

            ExceptionLog ex = null;
            for (int i = 0; i < lines.size(); i++) {
                String line = lines.get(i);
                if (line.contains(EXCEP_FLAG)) {
                    if (Objects.nonNull(ex)) {
                        exceptionLogs.add(ex);
                    }
                    ex = new ExceptionLog(line);
                    ex.setErrorMessage(lines.get(++i));
                    continue;
                }

                ex.appendStackTrace(line);
            }

            exceptionLogs.add(ex);
            service.addBatch(exceptionLogs);
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
