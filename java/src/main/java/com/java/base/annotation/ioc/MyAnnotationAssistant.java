package com.java.base.annotation.ioc;

import com.java.base.annotation.auto.*;

import java.lang.annotation.Annotation;
import java.util.HashMap;
import java.util.Map;

/**
 * 注解辅助
 * @author xuweizhi
 * @date 2019/04/11 20:29
 */
public class MyAnnotationAssistant {

    public final Map<String, Class<? extends Annotation>> componentRegister = new HashMap<>();

    public final Map<String, Class<? extends Annotation>> appRegister = new HashMap<>();

    {
        componentRegister.put("MyComponent", MyComponent.class);
        componentRegister.put("MyLocalMethod", MyLocalMethod.class);
        componentRegister.put("MyLocalMethodReinforce", MyLocalMethodReinforce.class);
        componentRegister.put("MyValue", MyValue.class);
        componentRegister.put("MyService", MyService.class);
        componentRegister.put("MyQualifier", MyQualifier.class);
        componentRegister.put("MyResource", MyResource.class);
        componentRegister.put("MyAutowired", MyAutowired.class);
        componentRegister.put("MyController", MyController.class);
        componentRegister.put("MyMapper", MyMapper.class);

        appRegister.put("MyApplication", MyApplication.class);
    }

}
