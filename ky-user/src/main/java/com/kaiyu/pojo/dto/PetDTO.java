package com.kaiyu.pojo.dto;

import com.kaiyu.examine.ExamineGroupAdd;
import com.kaiyu.examine.ExamineGroupUpd;
import lombok.Data;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Null;

/**
 * @Author 董乙辰
 * @Date 2021-03-31 14:15:32
 * @Description ky_pet
 **/
@Data
public class PetDTO {

    /**
     * id
     */
    @Null(message = "id need is null",groups = ExamineGroupAdd.class)
    @NotNull(message = "id is not null",groups = ExamineGroupUpd.class)
    private Long id;

    /**
     * 宠物图片
     */
    @NotBlank(message = "imgUrl is not null")
    private String imgUrl;

    /**
     * 宠物名称
     */
    @NotBlank(message = "name is not null")
    private String name;

    /**
     * 分类id
     */
    @NotNull(message = "classifyId is not null")
    private Long classifyId;

    /**
     * 分类名
     */
    private String classify;

    /**
     * 品种id
     */
    @NotNull(message = "breedId is not null")
    private Long breedId;

    /**
     * 品种名
     */
    private String breed;

    /**
     * 年龄
     */
    private Integer age;

    /**
     * 0=母 1=公
     */
    private Integer sex;

}