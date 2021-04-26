package com.kaiyu.error;

import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.multipart.MaxUploadSizeExceededException;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Objects;

@Slf4j
@RestControllerAdvice
public class BaseExceptionAdvice extends ResponseEntityExceptionHandler {

    /**
     * 处理zoologyException异常
     *
     * @param e 宠物生态异常
     * @return
     */
    @ExceptionHandler(KyException.class)
    public ResponseEntity<JSONObject> handlerException(KyException e) {
        JSONObject info = createMessage(e.getStatus(), e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
    }

    /**
     * 处理图片上传字节溢出异常
     *
     * @param e
     * @return
     */
    @ExceptionHandler(MaxUploadSizeExceededException.class)
    public ResponseEntity<JSONObject> handlerException(MaxUploadSizeExceededException e) {
        String message = Objects.requireNonNull(e.getMessage()).split("\\$FileSizeLimitExceededException:")[1];
        JSONObject info = createMessage(404, message);
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
    }

    /**
     * 处理校验异常信息
     *
     * @param e 校验异常
     * @return
     */
    @ExceptionHandler(ConstraintViolationException.class)
    public ResponseEntity<JSONObject> handlerException(ConstraintViolationException e) {
        JSONObject info = createMessage(404, e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(info);
    }


    /**
     * 创建异常信息
     *
     * @param status  状态码
     * @param message 提示消息
     * @return
     */
    public static JSONObject createMessage(int status, String message) {
        JSONObject info = new JSONObject();
        info.put("timestamp", DateTime.now().toString("yyyy-MM-dd HH:mm:ss"));
        info.put("status", status);
        info.put("message", message);
        return info;
    }

}
