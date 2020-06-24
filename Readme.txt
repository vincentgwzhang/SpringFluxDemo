Webflux itsself is NIO framework, and its core is Reactive programming

异步和同步的区别：
针对的是调用者。如果调用者发送请求后能立即做接下来的事情而不需要等待，那就是同步。

阻塞和非阻塞的区别：
针对的是被调用者。如果被调用后要经过计算才把结果给对方，那就是阻塞


WebMVC   的核心是 DispatcherServlet
WebFlux  的核心是 DispatcherHandler

SpringWebflux 实现函数式编程，RouterFunction 和 HandlerFunction

SpringWebflux 基于注解编程模型 (传统)
SpringWebFlux + Reactor + Netty


SpringWebflux 基于函数式编程模型 (函数式)
* RouterFunction
* HandlerFunction

请求和相应不再是 ServletRequest 和 ServletRsponse
而是 ServerRequest 和 ServerResponse
