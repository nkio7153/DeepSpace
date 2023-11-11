package com.depthspace.utils.commonDto;

import lombok.Data;

@Data
public class ResultResponse<T> {
    private Integer code =200;
    private  T message;
}
