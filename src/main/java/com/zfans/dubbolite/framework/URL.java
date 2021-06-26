package com.zfans.dubbolite.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * @Author Zfans
 * @DateTime 2021/6/26 1:35
 */
@Data
@AllArgsConstructor
public class URL implements Serializable {

    private String hostname;
    private Integer port;
}
