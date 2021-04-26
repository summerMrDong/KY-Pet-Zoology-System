package com.kaiyu.error;

import com.kaiyu.enums.ExceptionEnum;
import lombok.Data;

@Data
public class KyException extends RuntimeException{
    private int status;

    public KyException(int status, String message){
        super(message);
        this.status = status;
    }
    public KyException(ExceptionEnum exceptionEnum) {
        super(exceptionEnum.getMessage());
        this.status = exceptionEnum.getStatus();
    }

}
