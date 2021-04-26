package com.kaiyu.service;

import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.mapper.UploadRecordMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Objects;

/**
 * @Classname DownloadService
 * @Description TODO
 * @Date 2021/2/9 0009 上午 9:14
 * @Created by 董乙辰
 */
@Slf4j
@Service
public class DownloadService {

    private UploadRecordMapper recordMapper;

    @Autowired
    public DownloadService(UploadRecordMapper recordMapper) {
        this.recordMapper = recordMapper;
    }

    /**
     * 图片下载
     *
     * @param name 图片名称
     * @return
     */
    public byte[] downloadImage(String name) {
        byte[] stream;
        InputStream input = null;
        UploadRecordMapper.Download download;
        try {
            download = recordMapper.selectByFinalName(name);
            if (Objects.isNull(download)) {
                throw new KyException(ExceptionEnum.DOWNLOAD_INVALID_ERROR);
            }
            input = new FileInputStream(download.getLocation());
            stream = new byte[input.available()];
            while (true) {
                if ((input.read(stream)) <= 0) break;
            }
        } catch (IOException e) {
            e.printStackTrace();
            log.error(e.getMessage());
            throw new KyException(ExceptionEnum.SERVER_UNKNOWN_ERROR);
        } finally {
            if (Objects.nonNull(input)) {
                try {
                    input.close();
                } catch (IOException ignored) {
                }
            }
        }
        return stream;
    }
}
