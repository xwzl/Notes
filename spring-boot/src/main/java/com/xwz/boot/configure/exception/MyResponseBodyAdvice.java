package com.xwz.boot.configure.exception;

import com.xwz.boot.enums.ResultEnum;
import com.xwz.boot.vo.BaseRestResultVO;
import com.xwz.boot.vo.RestResultVO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.core.MethodParameter;
import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.http.server.ServerHttpRequest;
import org.springframework.http.server.ServerHttpResponse;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseBodyAdvice;

/**
 * 一定要指明切面
 *
 * @author xuweizhi
 */
@Slf4j
@RestControllerAdvice(value = "com.xwz.boot")
public class MyResponseBodyAdvice implements ResponseBodyAdvice {

    /**
     * 判断支持的类型
     */
    @Override
    public boolean supports(MethodParameter returnType, Class converterType) {
        return MappingJackson2HttpMessageConverter.class.isAssignableFrom(converterType);
    }

    /**
     * 对结果进行封装，或者进行加密处理
     */
    @Override
    public Object beforeBodyWrite(Object body, MethodParameter returnType, MediaType selectedContentType, Class selectedConverterType, ServerHttpRequest request, ServerHttpResponse response) {
        if (body == null) {
            return body;
        }
        if (body instanceof ErrorInfo) {
            RestResultVO<ErrorInfo<Object>> restResult = new RestResultVO<>();
            restResult.setSuccess(ResultEnum.FAILURE.getStatus());
            restResult.setCode(ResultEnum.FAILURE.getCode());
            restResult.setMessage(ResultEnum.FAILURE.getMessage());
            restResult.setData((ErrorInfo<Object>) body);
            return restResult;
        }
        if (body instanceof BaseRestResultVO) {
            return body;
        }

        if (body instanceof RestResultVO) {
            RestResultVO<Object> resultVO = (RestResultVO<Object>) body;
            resultVO.setSuccess(ResultEnum.SUCCESS.getStatus());
            resultVO.setCode(ResultEnum.SUCCESS.getCode());
            resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
            resultVO.setData(body);
            return resultVO;
        } else {
            RestResultVO<Object> resultVO = new RestResultVO<>();
            resultVO.setSuccess(ResultEnum.SUCCESS.getStatus());
            resultVO.setCode(ResultEnum.SUCCESS.getCode());
            resultVO.setMessage(ResultEnum.SUCCESS.getMessage());
            resultVO.setData(body);
            return resultVO;
        }

    }
}