package com.money.custom.rabbitmq.config;

import com.money.custom.rabbitmq.QueueConsts;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TopicRabbitConfig {

    private static final Logger LOG = LoggerFactory.getLogger(TopicRabbitConfig.class);

    @Bean
    TopicExchange topicExchange() {
        return new TopicExchange(QueueConsts.EXCHANGE);
    }

    @Bean
    public Queue historyQueue() {
        return new Queue(QueueConsts.HISTORY_QUEUE, true);
    }

    @Bean
    Binding bindHistoryQueueExchange() {
        return BindingBuilder.bind(historyQueue()).to(topicExchange()).with(QueueConsts.HISTORY_QUEUE);
    }


    @Bean
    public Queue msgQueue() {
        return new Queue(QueueConsts.MSG_QUEUE, true);
    }

    @Bean
    Binding bindMsgQueueExchange() {
        return BindingBuilder.bind(msgQueue()).to(topicExchange()).with(QueueConsts.MSG_QUEUE);
    }


    @Bean
    public Queue httpLogQueue() {
        return new Queue(QueueConsts.HTTP_LOG_QUEUE, true);
    }

    @Bean
    Binding bindHttpLogQueueExchange() {
        return BindingBuilder.bind(httpLogQueue()).to(topicExchange()).with(QueueConsts.HTTP_LOG_QUEUE);
    }


    @Bean
    public Queue visitLogQueue() {
        return new Queue(QueueConsts.VISIT_LOG_QUEUE, true);
    }

    @Bean
    Binding bindVisitLogQueueExchange() {
        return BindingBuilder.bind(visitLogQueue()).to(topicExchange()).with(QueueConsts.VISIT_LOG_QUEUE);
    }

}