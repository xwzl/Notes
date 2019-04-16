package com.java.frame.model;

import com.java.frame.auto.MyColumn;
import lombok.Data;

import java.io.Serializable;
import java.time.LocalDateTime;

/**
 * @author 徐伟智
 * @since 2019-04-03
 */
@Data
public class User implements Serializable {

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
