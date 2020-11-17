package com.money.custom.rabbitmq.receiver;

import com.alibaba.fastjson.JSON;
import com.money.custom.entity.History;
import com.money.custom.entity.enums.ChangeLogEntityEnum;
import com.money.custom.rabbitmq.QueueConsts;
import com.money.custom.service.HistoryService;
import com.money.framework.base.dao.BaseDao;
import org.apache.commons.collections4.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

@Component
@RabbitListener(queues = QueueConsts.HISTORY_QUEUE)
public class HistoryReceiver {

    private static final Logger LOG = LoggerFactory.getLogger(HistoryReceiver.class);

    @Autowired
    HistoryService historyService;


    @RabbitHandler
    public void process(Map message) {
        if (message.size() == 0) {
            return;
        }
        List<String> ids = (List<String>) message.get("ids");
        String type = message.get("type").toString();
        String updater = message.get("updater").toString();
        String createDate = message.get("createDate").toString();
        ChangeLogEntityEnum entityEnum = ChangeLogEntityEnum.get(type);
        if (Objects.isNull(entityEnum)) {
            LOG.warn("历史记录类型{}不存在", type);
            return;
        }
        List<History> histories = buildHistories(entityEnum, updater, ids, createDate);
        if (CollectionUtils.isNotEmpty(histories)) {
            historyService.addBatch(histories);
        }
        LOG.info("添加历史记录 {} 个", histories.size());

    }

    private List<History> buildHistories(ChangeLogEntityEnum changeLogEntity, String updater, List<String> ids, String createDate) {
        List<History> historyList = new ArrayList<>();
        if (CollectionUtils.isNotEmpty(ids)) {
            for (String id : ids) {
                Object detail4Log = getDetail4Log(changeLogEntity, id);
                if (Objects.isNull(detail4Log)) {
                    continue;
                }
                historyList.add(new History(id, changeLogEntity.getName(), JSON.toJSONString(detail4Log), updater, createDate));
            }
        }
        return historyList;
    }

    private Object getDetail4Log(ChangeLogEntityEnum changeLogEntity, String id) {
        BaseDao baseDao = null;
//        switch (changeLogEntity) {
//            case DEVICE:
//                return deviceService.findById(id);
//            case VEHICLE:
//                return vehicleService.findById(id);
//            case DRIVER:
//                return driverService.findById(id);
//            case ROUTE:
//                return routeService.selectRouteTimeDetail(id);
//            default:
//        }
        return null;
    }
}