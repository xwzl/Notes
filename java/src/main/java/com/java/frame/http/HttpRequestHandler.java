package com.java.frame.http;

import com.java.frame.handler.MyRequestHandler;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.frame.exception.ComponentConstance.FAVICON;
import static io.netty.buffer.Unpooled.copiedBuffer;
import static io.netty.handler.codec.http.HttpUtil.is100ContinueExpected;

/**
 * @author xuweizhi
 */
public class HttpRequestHandler extends SimpleChannelInboundHandler<FullHttpRequest> {

    Map<String, Object> single;

    Map<String, Map<MyRequestHandler, String>> handlers;

    static Map<String, MyRequestHandler> requestMapping = new HashMap<>();

    MyRequestHandlerPlus plus = new MyRequestHandlerPlus();

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

        FullHttpResponse response = null;
        String uri = req.uri();
        Object invoke = "1";
        if (uri.equalsIgnoreCase(FAVICON)) {
            plus.getFavicaon(ctx);
            return;
        }

        if (req.method() == HttpMethod.GET) {
            Map<String, Object> params = plus.getGetParamsFromChannel(req);

            if (HttpMethod.GET.equals(req.method())) {
                uri = uri.substring(0, uri.indexOf("?"));
                // 缓存获取 url 路径
                if (requestMapping.get(uri) != null) {
                    MyRequestHandler handler = requestMapping.get(uri);
                    invoke = parseGet(uri, invoke, params, handler.getControllerName(), handler);
                    plus.responseMessage(ctx, req, uri, invoke);
                    return;
                }
                for (Map.Entry<String, Map<MyRequestHandler, String>> entry : handlers.entrySet()) {
                    String className = entry.getKey();
                    Map<MyRequestHandler, String> entryValue = entry.getValue();
                    for (Map.Entry<MyRequestHandler, String> v : entryValue.entrySet()) {
                        MyRequestHandler requestHandler = v.getKey();
                        invoke = parseGet(uri, invoke, params, className, requestHandler);
                    }
                }
            }
        } else if (req.method() == HttpMethod.POST) {
            Map<String, Object> params = plus.getPostParamsFromChannel(req);
            if (requestMapping.get(uri) != null) {
                MyRequestHandler handler = requestMapping.get(uri);
                invoke = parseGet(uri, invoke, params, handler.getControllerName(), handler);
                plus.responseMessage(ctx, req, uri, invoke);
                return;
            }
            for (Map.Entry<String, Map<MyRequestHandler, String>> entry : handlers.entrySet()) {
                String className = entry.getKey();
                Map<MyRequestHandler, String> entryValue = entry.getValue();
                for (Map.Entry<MyRequestHandler, String> v : entryValue.entrySet()) {
                    MyRequestHandler requestHandler = v.getKey();
                    invoke = parseGet(uri, invoke, params, className, requestHandler);
                }
            }
            //String data = "POST method over";
            //ByteBuf content = copiedBuffer(data, CharsetUtil.UTF_8);
            //response = plus.responseOK(HttpResponseStatus.OK, content);
        } else {
            response = plus.responseOK(HttpResponseStatus.INTERNAL_SERVER_ERROR, null);
        }
        plus.responseMessage(ctx, req, uri, invoke);
    }


    private Object parseGet(String uri, Object invoke, Map<String, Object> params, String className, MyRequestHandler requestHandler) throws ClassNotFoundException, NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        String url = requestHandler.getUrl();

        if (uri.equalsIgnoreCase(url)) {

            Object o = single.get(className);
            Class<?> clazz = Class.forName(className);
            List<String> list = requestHandler.getList();

            Class<?>[] methodParamTypes = requestHandler.getMethodParamTypes();
            Method method = clazz.getDeclaredMethod(requestHandler.getMethodName(), methodParamTypes);
            requestMapping.put(url, requestHandler);

            Object[] param = new Object[list.size()];
            for (int i = 0; i < list.size(); i++) {
                boolean b = params.containsKey(list.get(i));
                if (b) {
                    param[i] = plus.getValue(methodParamTypes[i], params.get(list.get(i)), true);
                } else {
                    param[i] = plus.getValue(methodParamTypes[i], params.get(list.get(i)), false);
                }
            }

            method.setAccessible(true);
            System.out.println(param[0].getClass());
            invoke = method.invoke(o, param);
        }
        return invoke;
    }

    /**
     * 异常处理
     */
    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        ctx.close();
        super.exceptionCaught(ctx, cause);
    }


}