package com.java.frame.http;

import com.java.frame.handler.MyRequestHandler;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;

/**
 * @author xuweizhi
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    Map<String, Object> single;

    Map<String, Map<MyRequestHandler, String>> handlers;

    public HttpRequestHandler(Map<String, Object> single, Map<String, Map<MyRequestHandler, String>> handlers) {
        this.single = single;
        this.handlers = handlers;
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) {
        ctx.flush();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, FullHttpRequest req) throws Exception {
        // HTTP客户端程序有一个实体的主体部分要发送给服务器，但希望在发送之前查看下服务器是否会接受这个实体，所以在发送实
        // 体之前先发送了一个携带100 Continue的Expect请求首部的请求。
        //
        // 服务器在收到这样的请求后，应该用 100 Continue或一条错误码来进行响应。
        if (is100ContinueExpected(req)) {
            ctx.write(new DefaultFullHttpResponse(
                    HttpVersion.HTTP_1_1,
                    HttpResponseStatus.CONTINUE));
        }
        // 获取请求的uri
        String uri = req.uri();
        Object invoke=null;
        for (Map.Entry<String, Map<MyRequestHandler, String>> entry : handlers.entrySet()) {
            String className = entry.getKey();
            Map<MyRequestHandler, String> entryValue = entry.getValue();
            for (Map.Entry<MyRequestHandler, String> v : entryValue.entrySet()) {
                if (uri.equalsIgnoreCase(v.getKey().getUrl())) {
                    Object o = single.get(className);
                    Class<?> clazz = Class.forName(className);
                    String methodName = v.getValue().substring(v.getValue().indexOf("#") + 1);
                    Method[] methods = clazz.getDeclaredMethods();
                    for (Method method : methods) {
                        if (method.getName().equals(methodName)) {
                            method.setAccessible(true);
                            invoke= method.invoke(o);
                        }
                    }
                }
            }
        }
        String msss= (String)invoke;
        Map<String, String> resMap = new HashMap<>();
        resMap.put("method", req.method().name());
        resMap.put("uri", uri);
        String msg = "<html><head><title>test</title></head><body>你请求uri为：" + msss + "</body></html>";
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                Unpooled.copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}