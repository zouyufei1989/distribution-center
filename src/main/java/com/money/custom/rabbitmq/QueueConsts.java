package com.money.custom.rabbitmq;


import com.money.custom.entity.Consts;

public class QueueConsts {

    /**
     * 交换器名
     */
    public static final String EXCHANGE = Consts.GROUP_NAME + "_exchange";

    public static final String HISTORY_QUEUE = Consts.GROUP_NAME + "_history_queue";

    public static final String MSG_QUEUE = Consts.GROUP_NAME + "_msg_queue";

    public static final String VISIT_LOG_QUEUE = Consts.GROUP_NAME + "_visit_log_queue";

    public static final String HTTP_LOG_QUEUE = Consts.GROUP_NAME + "_http_log_queue";

}
