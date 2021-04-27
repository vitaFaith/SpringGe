package com.ge.mq;

import com.ge.config.RabbitMQConfig;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class SendMsgController {
    //注入RabbitMQ的模板
    @Autowired
    private RabbitTemplate rabbitTemplate;

    /**
     * 测试
     */
    @GetMapping("/sendmsg22")
    public String sendMsg(@RequestParam String msg, @RequestParam String key){
        /**
         * 发送消息
         * 参数一：交换机名称
         * 参数二：路由key: item.springboot-rabbitmq,符合路由item.#规则即可
         * 参数三：发送内容
         */
        rabbitTemplate.convertAndSend(RabbitMQConfig.ITEM_TOPIC_EXCHANGE ,key ,msg);
        //返回消息
        return "发送消息成功！";
    }
}
