package com.kaiyu.function;

import com.kaiyu.utils.SnowFlake;
import lombok.AccessLevel;
import lombok.Data;
import lombok.Getter;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;
import tk.mybatis.mapper.util.StringUtil;

import java.io.File;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;

/**
 * @Classname UploadFunction
 * @Description 上传功能抽象类
 * @Date 2021/2/4 0004 上午 10:37
 * @Created by 董乙辰
 */
@Data
@Slf4j
public abstract class UploadFunction<T> {



    /**
     * 默认目录
     */
    protected String default_directory;

    /**
     * 雪花算法
     */
    private static final SnowFlake snowFlake = new SnowFlake(1, 30);

    @Getter()
    protected class Directory {
        /**
         * 初始化
         */
        @Getter(AccessLevel.NONE)
        private String initPath;

        /**
         * 扩展目录
         */
        @Getter(AccessLevel.NONE)
        private String extend_directory;

        /**
         * 图片路径
         */
        private File path;

        /**
         * 目录
         */
        private File directoryPath;

        /**
         * 文件最终名称
         */
        private String file_final_name;

        public Directory(String extend_directory) {
            this.extend_directory = extend_directory;
            initPath = default_directory.concat(extend_directory);
        }

        @SneakyThrows
        protected void create(String name) {
            directoryPath = new File(initPath);
            long nextId = snowFlake.nextId();
            file_final_name = nextId + name;
            String filePath = initPath.concat(file_final_name);
            log.info("\n扩展目录：" + extend_directory +
                    "\n路径：" + filePath +
                    "\n最终名称：" + file_final_name +
                    "\n目录：" + directoryPath);
            path = new File(filePath);
        }
    }


    /**
     * 上传功能
     *
     * @param files            文件内容
     * @param extend_directory 扩展目录
     * @return
     */
    public abstract T upload(List<MultipartFile> files, String extend_directory);

}