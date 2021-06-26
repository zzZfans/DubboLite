package com.zfans.dubbolite.framework;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * 调用
 *
 * @Author Zfans
 * @DateTime 2021/6/24 20:06
 */
@Data
@AllArgsConstructor
public class Invocation implements Serializable {

    private String interfaceName;
    private String methodName;
    private String version;
    private Object[] params;
    private Class[] paramType;
}
