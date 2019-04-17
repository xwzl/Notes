package com.java.frame.http;

import com.google.gson.Gson;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.java.frame.util.GsonUtil;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.*;
import io.netty.handler.codec.http.multipart.DefaultHttpDataFactory;
import io.netty.handler.codec.http.multipart.HttpPostRequestDecoder;
import io.netty.handler.codec.http.multipart.InterfaceHttpData;
import io.netty.handler.codec.http.multipart.MemoryAttribute;
import io.netty.util.CharsetUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.lang.reflect.InvocationTargetException;
import java.net.URL;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static com.java.frame.exception.ComponentConstance.GITHUB;
import static com.java.frame.exception.ComponentConstance.ICON;
import static com.java.frame.exception.JavaType.*;
import static io.netty.buffer.Unpooled.copiedBuffer;
import static java.lang.Boolean.FALSE;

/**
 * @author xuweizhi
 * @date 2019/04/17 14:58
 */
public class MyRequestHandlerPlus {
    public Object getValue(Class<?> clazz, Object obj, boolean flag) throws NoSuchMethodException, IllegalAccessException, InvocationTargetException, InstantiationException {
        switch (clazz.getName()) {
            case INTEGER:
                obj = flag ? Integer.valueOf((String) obj) : Integer.valueOf("0");
                break;
            case STRING:
                obj = flag ? (String) obj : (String) null;
                break;
            case DATE:
                obj = flag ? (String) obj : (Date) null;
                break;
            case LOCAL_DATE_TIME:
                obj = flag ? (String) obj : (LocalDateTime) null;
                break;
            case SHORT:
                obj = flag ? Short.valueOf((String) obj) : (Short) null;
                break;
            case LONG:
                obj = flag ? Long.valueOf((String) obj) : (Long) null;
                break;
            case FLOAT:
                obj = flag ? Float.valueOf((String) obj) : (Float) null;
                break;
            case DOUBLE:
                obj = flag ? Double.valueOf((String) obj) : (Double) null;
                break;
            case BYTE:
                obj = flag ? Byte.valueOf((String) obj) : (Byte) null;
                break;
            case BOOLEAN:
                obj = flag ? Boolean.valueOf((String) obj) : FALSE;
                break;
            default:
                if (clazz.getName().contains("List")) {
                    obj = GsonUtil.parseJsonArrayWithGson((String) obj, clazz);
                } else {
                    obj = GsonUtil.parseJsonWithGson((String) obj, clazz);
                }
        }
        return obj;
    }

    void responseMessage(ChannelHandlerContext ctx, FullHttpRequest req, String uri, Object invoke) {
        Gson gson = new Gson();
        Map<String, String> resMap = new HashMap<>();
        resMap.put("method", req.method().name());
        resMap.put("uri", uri);
        //String msg = "<html><head><title>test</title></head><body>你请求uri为：" + msss + "</body></html>";
        String msg = "";
        if (invoke instanceof String) {
            msg = (String) invoke;
        } else {
            msg = gson.toJson(invoke);
        }
        // 创建http响应
        FullHttpResponse response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                copiedBuffer(msg, CharsetUtil.UTF_8));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/html; charset=UTF-8");
        //response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        // 将html write到客户端
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }


    /**
     * 获取GET方式传递的参数
     */
    Map<String, Object> getGetParamsFromChannel(FullHttpRequest fullHttpRequest) {

        Map<String, Object> params = new HashMap<String, Object>();

        if (fullHttpRequest.method() == HttpMethod.GET) {
            // 处理get请求
            QueryStringDecoder decoder = new QueryStringDecoder(fullHttpRequest.uri());
            Map<String, List<String>> paramList = decoder.parameters();
            for (Map.Entry<String, List<String>> entry : paramList.entrySet()) {
                params.put(entry.getKey(), entry.getValue().get(0));
            }
            return params;
        } else {
            return null;
        }

    }

    /**
     * 获取POST方式传递的参数
     */
    Map<String, Object> getPostParamsFromChannel(FullHttpRequest fullHttpRequest) {

        Map<String, Object> params = new HashMap<String, Object>();

        if (fullHttpRequest.method() == HttpMethod.POST) {
            // 处理POST请求，普通浏览器请求
            String strContentType = fullHttpRequest.headers().get("Content-Type").trim();
            if (strContentType.contains("x-www-form-urlencoded")) {
                params = getFormParams(fullHttpRequest);

                // 上传文件
            } else if (strContentType.contains("multipart/form-data")) {
                params = getFormParams(fullHttpRequest);
                // json 请求
            } else if (strContentType.contains("application/json")) {
                try {
                    params = getJSONParams(fullHttpRequest);
                } catch (UnsupportedEncodingException e) {
                    return null;
                }
            } else {
                return null;
            }
            return params;
        } else {
            return null;
        }
    }

    /**
     * 解析from表单数据（Content-Type = x-www-form-urlencoded）
     */
    Map<String, Object> getFormParams(FullHttpRequest fullHttpRequest) {
        Map<String, Object> params = new HashMap<String, Object>();

        HttpPostRequestDecoder decoder = new HttpPostRequestDecoder(new DefaultHttpDataFactory(false), fullHttpRequest);
        List<InterfaceHttpData> postData = decoder.getBodyHttpDatas();

        for (InterfaceHttpData data : postData) {
            if (data.getHttpDataType() == InterfaceHttpData.HttpDataType.Attribute) {
                MemoryAttribute attribute = (MemoryAttribute) data;
                params.put(attribute.getName(), attribute.getValue());
            }
        }

        return params;
    }

    /**
     * 解析json数据（Content-Type = application/json）
     */
    Map<String, Object> getJSONParams(FullHttpRequest fullHttpRequest) throws UnsupportedEncodingException {
        Map<String, Object> params = new HashMap<String, Object>();

        ByteBuf content = fullHttpRequest.content();
        byte[] reqContent = new byte[content.readableBytes()];
        content.readBytes(reqContent);
        String strContent = new String(reqContent, "UTF-8");

        //JSONObject jsonParams = JSONObject.fromObject(strContent);
        //for (Object key : jsonParams.keySet()) {
        //    params.put(key.toString(), jsonParams.get(key));
        //}
        Gson gson = new Gson();
        JsonElement jsonElement = gson.toJsonTree(strContent);
        jsonElement.isJsonObject();
        JsonObject jsonObject = jsonElement.getAsJsonObject();
        for (Object key : jsonObject.keySet()) {
            params.put(key.toString(), jsonObject.get((String) key));
        }
        //String s = gson.toJson(strContent);
        //System.out.println(s);

        return params;
    }

    FullHttpResponse responseOK(HttpResponseStatus status, ByteBuf content) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, content);
        if (content != null) {
            response.headers().set("Content-Type", "text/plain;charset=UTF-8");
            response.headers().set("Content_Length", response.content().readableBytes());
        }
        return response;
    }

    void getFavicaon(ChannelHandlerContext ctx) throws IOException {
        FullHttpResponse response;
        URL url = Thread.currentThread().getContextClassLoader().getResource(ICON);
        if (url == null) {
            url = Thread.currentThread().getContextClassLoader().getResource(GITHUB);
        }
        FileInputStream in = new FileInputStream(url.getPath());
        byte[] bytes = new byte[in.available()];
        in.read(bytes);
        in.close();
        // 创建http响应
        response = new DefaultFullHttpResponse(
                HttpVersion.HTTP_1_1,
                HttpResponseStatus.OK,
                copiedBuffer(bytes));
        // 设置头信息
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "application/x-png");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }
}
