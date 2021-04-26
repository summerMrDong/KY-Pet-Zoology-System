package com.kaiyu.function;

import com.kaiyu.enums.ExceptionEnum;
import com.kaiyu.error.KyException;
import com.kaiyu.pojo.UploadRecordEntity;
import lombok.Setter;
import org.springframework.core.io.Resource;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.regex.Pattern;

/**
 * @Classname UploadImage
 * @Description 上传图片实现类
 * @Date 2021/2/4 0004 上午 11:00
 * @Created by 董乙辰
 */
@Setter
public class UploadImageImpl extends UploadFunction<List<UploadRecordEntity>> {

    private static final String FORMAT = "(.+)\\.(jpg|bmp|gif|ico|pcx|jpeg|tif|png|raw|tga)$";

    private static UploadImageImpl uploadImage = new UploadImageImpl();

    private UploadImageImpl() {
    }

    public static UploadImageImpl getUploadImage(String default_directory) {
        uploadImage.setDefault_directory(default_directory);
        return uploadImage;
    }

    @Override
    public List<UploadRecordEntity> upload(List<MultipartFile> files, String extend_directory) {
        List<UploadRecordEntity> records = new ArrayList<>();

        try {
            Directory directory = new Directory(extend_directory);

            for (MultipartFile image : files) {

                if (image.isEmpty() || image.getSize() <= 0) {
                    throw new KyException(ExceptionEnum.UPLOAD_FILE_INVALID_ERROR);
                }

                Resource resource = image.getResource();
                String filename = resource.getFilename();
                assert filename != null;

                if (!Pattern.matches(FORMAT, filename)) {
                    throw new KyException(ExceptionEnum.UPLOAD_IMAGE_FORMAT_ERROR);
                }

                directory.create(filename);
                File directoryPath = directory.getDirectoryPath();
                File path = directory.getPath();

                if (!directoryPath.exists()) {
                    boolean mkdirs = directoryPath.mkdirs();
                }

                FileOutputStream outputStream = new FileOutputStream(path);
                outputStream.write(image.getBytes());
                UploadRecordEntity entity = new UploadRecordEntity();
                entity.setFileType(FileType.IMAGE.code);
                entity.setFileFormat(image.getContentType());
                entity.setDefaultName(filename);
                entity.setLocation(path.toString());
                entity.setFinalName(directory.getFile_final_name());
                entity.setCreateTime(new Date());
                records.add(entity);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
            throw new KyException(ExceptionEnum.UPLOAD_FILE_ERROR);
        }
        return records;
    }
}
