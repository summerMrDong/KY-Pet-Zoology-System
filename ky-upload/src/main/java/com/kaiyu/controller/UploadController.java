package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.function.FileType;
import com.kaiyu.pojo.UploadRecordEntity;
import com.kaiyu.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import java.util.List;
import java.util.Objects;

@Validated
@RestController
@RequestMapping("/upload")
public class UploadController {

    private UploadService uploadService;

    @Autowired
    public UploadController(UploadService uploadService) {
        this.uploadService = uploadService;
    }

    /**
     * 上传文件
     *
     * @param fileType  文件类型
     * @param directory 扩展目录
     * @param files     文件
     * @return
     */
    @PostMapping("/file")
    public ResponseEntity<Info<List<UploadRecordEntity>>> uploadImage(
            @RequestParam String fileType,
            @RequestParam(defaultValue = "") String directory,
            @RequestParam @NotEmpty(message = "未选择文件") List<MultipartFile> files
    ) {
        Info<List<UploadRecordEntity>> info = chooser(fileType.toUpperCase(), directory, files);
        return ResponseEntity.ok(Objects.requireNonNull(info));
    }

    private Info<List<UploadRecordEntity>> chooser(String fileType, String directory, List<MultipartFile> files) {
        FileType type;
        try {
            type = FileType.valueOf(fileType);
        } catch (Exception e) {
            throw new KyException(ExceptionEnum.UPLOAD_TYPE_NOT_SUPPORTED_ERROR);
        }
        Info<List<UploadRecordEntity>> info = null;
        switch (type) {
            case TEXT:
            case VIDEO:
            case IMAGE:
                info = uploadService.uploadImage(directory, files);
                break;
            case VOICE:
                break;
        }
        return info;
    }
}
