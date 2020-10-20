package org.stlife.commons.web;

import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

/**
 * Result
 * 描述：TODO
 *
 * @author Stlife
 * @version 1.0
 * @since 2020-09-23 15:30
 **/
@Data
@AllArgsConstructor
public class Result<T> implements Serializable {

    private int code;

    private String msg;

    private T data;
}
