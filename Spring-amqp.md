# Spring-amqp reference

来源[Spring amqp](http://docs.spring.io/spring-amqp/docs/1.4.5.RELEASE/reference/html/amqp.html#d4e369)

<hr/>

### AMQP抽象

Spring-AMQP包括三个模块，分别是spring-amqp，spring-rabbit， spring-erlang。
spring-amqp：抽象了AMQP的核心模型（exchange，queue，bingding)
spring-rabbit: rabbitMQ-client（java）客户端封装
spring-erlang：rabbitMQ-client（erlang)客户端封装

- Message

    amqp协议中未定义message模型，当执行比如basicPublish方法时，需要传入二进制数据和附加的属性。spring-amqp定义
    Message模型，简单的封装了二进制数据和附加的属性为一个模型，简化API。Message模型：

```

public class Message {

    private final MessageProperties messageProperties;

    private final byte[] body;

    public Message(byte[] body, MessageProperties messageProperties) {
        this.body = body;
        this.messageProperties = messageProperties;
    }

    public byte[] getBody() {
        return this.body;
    }

    public MessageProperties getMessageProperties() {
        return this.messageProperties;
    }
}
```

- Exchange

    Spring-amqp的exchange模型封装了amqp协议的exchange，消息生产者将消息发送给exchange。在MQ中，
    同一个虚拟主机下的exhange必须有唯一的名称，当然exhange还有一些其他的属性（type等），定义如下：

```

public interface Exchange {

    String getName();

    String getExchangeType();

    boolean isDurable();

    boolean isAutoDelete();

    Map<String, Object> getArguments();

    }
```
- Queue

    Queue是消费者接受消息的组件，有名字和各种属性。如下：

```
public class Queue  {

    private final String name;

    private volatile boolean durable;

    private volatile boolean exclusive;

    private volatile boolean autoDelete;

    private volatile Map<String, Object> arguments;

    public Queue(String name) {
        this(name, true, false, false);
    }
```

- Binding

生产者将消息发送给exhagne，消费者从queue中消费消息，Binding用来连接exhange和queue。例如：

使用固定routingkey绑定queue到directExcheng

```
new Binding(someQueue, someDirectExchange, "foo.bar")
```

使用routing模式绑定queue到TopicExhange

```
new Binding(someQueue, someTopicExchange, "foo.*")
```

绑定queue到fanoutExahnge

```
new Binding(someQueue, someFanoutExchange)
```

"fluent API" 风格编程

```
Binding b = BindingBuilder.bind(someQueue).to(someTopicExchange).with("foo.*");
```

### 连接和资源管理

连接管理的核心接口是ConnectionFactory，它负责构建org.springframework.amqp.rabbit.connection.Connection，
该接口是com.rabbitmq.client.Connection的包装。CachingConnectionFactory是目前的唯一实用的ConnectionFactory
实现，默认它建立能被应用程序共享单一的连接代理。使连接能够共享的是因为AMQP的工作单元实际上是channel，连接通过createChannel
来构建channel，CachingConnectionFactory缓存构建好的channal，默认缓存size为1，可以通过setChannelCacheSize()修改。
注意该size默认情况下并不是channel的限制，只是表示能够被缓存可重复使用的size，真正的channel实例可以超过该size。但是如果CachingConnectionFactory
配置了属性channelCheckoutTimeout之后，size就变成了真正的channel实例个数限制了。

CachingConnectionFactory的构建：

```
<rabbit:connection-factory
    id="connectionFactory"  channel-cache-size="25" addresses="host1:5672,host2:5672"/>
```

消息发布确认和返回
通过设置CachingConnectionFactory's publisherConfirms and publisherReturns properties to 'true'。
当设置该属性之后，创建的channel被包装为PublisherCallbackChannel，客户端能够注册 PublisherCallbackChannel.Listener到该channel上。


### AmqpTemplate

