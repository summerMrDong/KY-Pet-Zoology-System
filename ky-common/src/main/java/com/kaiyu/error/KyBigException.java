package com.kaiyu.error;

import com.kaiyu.enums.ExceptionEnum;
import lombok.Data;

@Data
public class KyBigException extends Exception{
    private int status;

    public KyBigException(int status, String message){
        super(message);
        this.status = status;
    }
    public KyBigException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }

}
