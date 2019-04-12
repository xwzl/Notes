/*
 * Copyright 2002-2018 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java.spring.xml;

import org.springframework.core.convert.ConversionFailedException;
import org.springframework.core.convert.ConversionService;
import org.springframework.core.convert.TypeDescriptor;
import org.springframework.core.convert.converter.GenericConverter;
import org.springframework.lang.Nullable;
import org.springframework.util.Assert;

/**
 * Internal utilities for the conversion package.
 *
 * @author Keith Donald
 * @author Stephane Nicoll
 * @since 3.0
 */
abstract class ConversionUtils {

    @Nullable
    public static Object invokeConverter(GenericConverter converter, @Nullable Object source,
                                         TypeDescriptor sourceType, TypeDescriptor targetType) {
        try {
            /**
             * 	GenericConverter 是一个转换接口，一个用于在两种或更多种类型之间转换的通用型转换器接口。它是 Converter SPI
             * 	体系中最灵活的，也是最复杂的接口，灵活性在于 GenericConverter 可以支持在多个源/目标类型对之间进行转换，同时也
             * 	可以在类型转换过程中访问源/目标字段上下文。由于该接口足够复杂，所有当更简单的 Converter 或 ConverterFactory
             * 	接口足够使用时，通常不应使用此接口。
             *
             * 	GenericConverter 的子类有这么多（看类名就知道是干嘛的了）
             *
             * 	我们看一个子类的实现 StringToArrayConverter，该子类将逗号分隔的 String 转换为 Array 。
             *
             * 	Converter 是一个将 <S> 类型的源对象转换为 <T> 类型的目标对象的转换器。该接口是线程安全的，所以可以共享。
             *
             * 	ConditionalConverter 接口用于表示有条件的类型转换，通过转入的sourceType 与 targetType 判断转换能否匹配，
             * 	只有可匹配的转换才会调用convert 方法进行转换。
             *
             * 一个用于“远程”转换的转换工厂，可以将对象从 <S> 转换为 <R> 的子类型。代码如下：
             *
             * 四种不同的转换器承载着不同的转换过程：
             *
             * Converter：用于 1:1 的 source -> target 类型转换。
             * ConverterFactory：用于 1:N 的 source -> target 类型转换。
             * GenericConverter用于 N:N 的 source -> target 类型转换。
             * ConditionalConverter：有条件的 source -> target 类型转换。
             *
             *   {@link StringToArrayConverter#convert(Object, TypeDescriptor, TypeDescriptor)}
             *
             *
             */
            return converter.convert(source, sourceType, targetType);
        } catch (ConversionFailedException ex) {
            throw ex;
        } catch (Throwable ex) {
            throw new ConversionFailedException(sourceType, targetType, source, ex);
        }
    }

    public static boolean canConvertElements(@Nullable TypeDescriptor sourceElementType,
                                             @Nullable TypeDescriptor targetElementType, ConversionService conversionService) {

        if (targetElementType == null) {
            // yes
            return true;
        }
        if (sourceElementType == null) {
            // maybe
            return true;
        }
        if (conversionService.canConvert(sourceElementType, targetElementType)) {
            // yes
            return true;
        }
        if (sourceElementType.getType().isAssignableFrom(targetElementType.getType())) {
            // maybe
            return true;
        }
        // no
        return false;
    }

    public static Class<?> getEnumType(Class<?> targetType) {
        Class<?> enumType = targetType;
        while (enumType != null && !enumType.isEnum()) {
            enumType = enumType.getSuperclass();
        }
        Assert.notNull(enumType, () -> "The target type " + targetType.getName() + " does not refer to an enum");
        return enumType;
    }

}
