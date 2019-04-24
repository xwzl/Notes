package com.xwz.boot.configure.data;

import lombok.Getter;
import org.jetbrains.annotations.Contract;

/**
 * 创建DataSourceType，枚举区分读写库
 *
 * @author xuweizhi
 */
public enum DataSourceType {

    /**
     *  从库
     */
    read("read", "从库"),
    /**
     * 主库
     */
    write("write", "主库");

    @Getter
    private String type;

    @Getter
    private String name;

    @Contract(pure = true)
    DataSourceType(String type, String name) {
        this.type = type;
        this.name = name;
    }
}
