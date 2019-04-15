package com.java.mybatis.model;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import com.java.base.annotation.auto.MyColumn;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * <p>
 *
 * </p>
 *
 * @author 徐伟智
 * @since 2019-04-03
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class User implements Serializable {

    private static final long serialVersionUID = 1L;

    @TableId(value = "u_id", type = IdType.AUTO)
    @MyColumn("u_id")
    private Integer uId;

    @MyColumn("address")
    private String address;

    @MyColumn("apartment")
    private String apartment;

    @MyColumn("create_time")
    private LocalDateTime createTime;

    @MyColumn("password")
    private String password;

    @MyColumn("phone_number")
    private String phoneNumber;

    @MyColumn("role")
    private Integer role;

    @MyColumn("username")
    private String username;


}
