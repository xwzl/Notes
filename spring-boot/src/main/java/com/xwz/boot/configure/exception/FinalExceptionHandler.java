package com.xwz.boot.configure.exception;

import com.xwz.boot.vo.BaseRestResultVO;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 在进入Controller之前，如果请求一个不存在的地址，404错误等
 *
 * @author xuweizhi
 * @date 2019/04/24 10:57
 */
@RestController
public class FinalExceptionHandler implements ErrorController {

    @Override
    public String getErrorPath() {
        return "/error";
    }

    @RequestMapping(value = "/error")
    @ResponseBody
    public BaseRestResultVO error(HttpServletResponse resp, HttpServletRequest req) {
        // 错误处理逻辑
        return new BaseRestResultVO(false, 404, "页面或者资源未找到");
    }

}
