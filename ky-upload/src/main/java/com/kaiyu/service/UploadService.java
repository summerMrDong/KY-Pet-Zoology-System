package com.kaiyu.service;

import com.kaiyu.dto.Info;
import com.kaiyu.function.UploadFactory;
import com.kaiyu.function.UploadFunction;
import com.kaiyu.mapper.UploadRecordMapper;
import com.kaiyu.pojo.UploadRecordEntity;
import lombok.extern.slf4j.Slf4j;
import org.omg.CORBA.DATA_CONVERSION;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.PropertySource;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import java.util.Date;
import java.util.List;

@Slf4j
@Service
@PropertySource(value = "classpath:application-file.properties")
public class UploadService {

    @Value(value = "${preview.domain.url}")
    private String previewURL;

    @Value(value = "${download.domain.url}")
    private String downloadURL;

    private UploadFactory uploadFactory;

    private UploadRecordMapper recordMapper;


    @Autowired
    public UploadService(UploadFactory uploadFactory, UploadRecordMapper recordMapper) {
        this.uploadFactory = uploadFactory;
        this.recordMapper = recordMapper;
    }


    /**
     * 上传图片
     *
     * @param directory 扩展目录
     * @param images    图片
     * @return
     */
    @Transactional
    public Info<List<UploadRecordEntity>> uploadImage(String directory, List<MultipartFile> images) {
        String username = ((User) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUsername();

        UploadFunction<List<UploadRecordEntity>> uploadImage = uploadFactory.getUploadImage();

        directory = adjustDirectory(directory);

        List<UploadRecordEntity> record = uploadImage.upload(images, directory);

        String preview = String.format(previewURL, directory);

        Date date = new Date();
        record.forEach((a) -> {
            String finalName = a.getFinalName();
            a.setPreviewUrl(preview.concat(finalName));
            a.setDownloadUrl(downloadURL.concat(finalName));
            a.setUsername(username);
            a.setUpdateTime(date);
        });

        recordMapper.insertList(record);
        return Info.ok(record);
    }

    /**
     * 调整扩展目录
     *
     * @return
     */
    private String adjustDirectory(String directory) {

        if (StringUtil.isEmpty(directory)) {
            return directory;
        }

        StringBuilder stringBuilder = new StringBuilder(directory);
        int i;

        while ((i = stringBuilder.indexOf("//")) != -1) {
            stringBuilder.deleteCharAt(i);
        }

        if (stringBuilder.charAt(0) == '/') {
            stringBuilder.deleteCharAt(0);
        }

        if (stringBuilder.charAt(stringBuilder.length() - 1) != '/') {
            stringBuilder.append('/');
        }

        return stringBuilder.toString();
    }
}
