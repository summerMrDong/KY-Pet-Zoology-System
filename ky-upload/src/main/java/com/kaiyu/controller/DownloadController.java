package com.kaiyu.controller;

import com.kaiyu.service.DownloadService;
import lombok.extern.slf4j.Slf4j;
import org.joda.time.DateTime;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletResponse;
import javax.validation.constraints.NotBlank;
import java.io.IOException;


/**
 * @Classname DownloadController
 * @Description 图片下载
 * @Date 2021/2/9 0009 上午 9:12
 * @Created by 董乙辰
 */
@Slf4j
@RestController
@RequestMapping("/file/download")
public class DownloadController {

    private static final String CHARSET = ";charset=utf-8";
    private static final String CONTENT_DISPOSITION = "attachment;filename=%s";

    private DownloadService downloadService;

    @Autowired
    public DownloadController(DownloadService downloadService) {
        this.downloadService = downloadService;
    }

    /**
     * 图片下载
     *
     * @param name     图片名称
     * @param response
     * @throws IOException
     */
    @GetMapping("/{name}")
    public void downloadImage(
            @PathVariable @NotBlank String name,
            HttpServletResponse response
    ) throws IOException {
        String filename = name.split("&")[0];
        byte[] bytes = downloadService.downloadImage(filename);
        String content_type = MediaType.APPLICATION_OCTET_STREAM.toString().concat(CHARSET);
        response.setHeader("Content-Disposition", String.format(CONTENT_DISPOSITION, filename));
        response.setHeader("Content-Type", content_type);
        response.getOutputStream().write(bytes);
        log.info(DateTime.now().toString("yyyy-MM-dd HH:mm:ss") + ":" + filename + " 下载成功");
    }

}
