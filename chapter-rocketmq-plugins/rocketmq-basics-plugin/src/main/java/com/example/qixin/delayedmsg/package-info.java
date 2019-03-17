package com.example.qixin.delayedmsg;
/** 延时消息
 延时消息，简单来说就是当 producer 将消息发送到 broker 后，会延时一定时间后才投递给 consumer 进行消费。
 RcoketMQ的延时等级为：1s，5s，10s，30s，1m，2m，3m，4m，5m，6m，7m，8m，9m，10m，20m，30m，1h，2h。level=0，表示不延时。level=1，表示 1 级延时，对应延时 1s。level=2 表示 2 级延时，对应5s，以此类推。
 这种消息一般适用于消息生产和消费之间有时间窗口要求的场景。比如说我们网购时，下单之后是有一个支付时间，超过这个时间未支付，系统就应该自动关闭该笔订单。那么在订单创建的时候就会就需要发送一条延时消息（延时15分钟）后投递给 consumer，consumer 接收消息后再对订单的支付状态进行判断是否关闭订单。
 设置延时非常简单，只需要在Message设置对应的延时级别即可：


 Message msg = new Message("TopicTest",// topic
 "TagA",// tag
 ("Hello RocketMQ " + i).getBytes(RemotingHelper.DEFAULT_CHARSET)// body
 );
 // 这里设置需要延时的等级即可
 msg.setDelayTimeLevel(3);
 SendResult sendResult = producer.send(msg);

 */