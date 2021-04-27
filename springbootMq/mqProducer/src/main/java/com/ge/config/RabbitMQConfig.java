package com.ge.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitMQConfig {

    //交换机名称
    public static final String ITEM_TOPIC_EXCHANGE = "amq.topic";
    //短信名称
    public static final String SMS_QUEUE = "sms_queue";
    //邮件名称
    public static final String MAIL_QUEUE = "mail_queue";

    //声明交换机
    @Bean("itemTopicExchange")
    public Exchange topicExchange(){
        return ExchangeBuilder.topicExchange(ITEM_TOPIC_EXCHANGE).durable(true).build();
    }

    //声明队列
    @Bean("smsQueue")
    public Queue smsQueue(){
        return QueueBuilder.durable(SMS_QUEUE).build();
    }

    //声明队列
    @Bean("mailQueue")
    public Queue mailQueue(){
        return QueueBuilder.durable(MAIL_QUEUE).build();
    }

    //绑定短信和交换机
    @Bean
    public Binding smsQueueExchange(@Qualifier("smsQueue") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("sms.#").noargs();
    }

    //绑定邮件和交换机
    @Bean
    public Binding mailQueueExchange(@Qualifier("mailQueue") Queue queue,
                                     @Qualifier("itemTopicExchange") Exchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("mail.#").noargs();
    }
}
