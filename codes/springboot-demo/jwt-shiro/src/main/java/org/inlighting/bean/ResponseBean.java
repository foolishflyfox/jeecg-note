package org.inlighting.bean;

import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * @author benfeihu
 */
@Data
@AllArgsConstructor
public class ResponseBean {
    private int code;
    private String msg;
    private Object data;
}
