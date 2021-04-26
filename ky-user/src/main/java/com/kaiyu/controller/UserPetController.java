package com.kaiyu.controller;

import com.kaiyu.dto.Info;
import com.kaiyu.examine.ExamineGroupAdd;
import com.kaiyu.examine.ExamineGroupUpd;
import com.kaiyu.pojo.dto.PetDTO;
import com.kaiyu.pojo.entity.PetEntity;
import com.kaiyu.pojo.vo.PetBreedVO;
import com.kaiyu.pojo.vo.PetClassifyVO;
import com.kaiyu.service.UserPetService;
import com.kaiyu.utils.VerificationProcess;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import javax.validation.groups.Default;
import java.util.List;

/**
 * @Classname UserPetInfoController
 * @Description TODO
 * @Date 2021/3/30 0030 下午 2:10
 * @Created by 董乙辰
 */
@RestController
@RequestMapping("/pet")
public class UserPetController {

    private UserPetService userPetService;

    @Autowired
    public UserPetController(UserPetService petInfoService) {
        this.userPetService = petInfoService;
    }

    /**
     * 宠物全部分类信息
     *
     * @return
     */
    @GetMapping("/sel/classify")
    public ResponseEntity<Info<List<PetClassifyVO>>> selClassifyAll() {
        return ResponseEntity.ok(userPetService.selClassifyAll());
    }

    /**
     * 类型下的宠物品种信息
     *
     * @param classifyId
     * @return
     */
    @GetMapping("/sel/breeds")
    public ResponseEntity<Info<List<PetBreedVO>>> selBreeds(@RequestParam Long classifyId) {
        return ResponseEntity.ok(userPetService.selBreeds(classifyId));
    }

    /**
     * 添加宠物
     *
     * @param userId
     * @param petDTO
     * @param result
     * @return
     */
    @PostMapping("/add/pet")
    public ResponseEntity<Info<String>> addPet(
            @RequestParam Long userId,
            @RequestBody @Validated({ExamineGroupAdd.class, Default.class}) PetDTO petDTO,
            BindingResult result
    ) {
        VerificationProcess.revertMsg(result);
        return ResponseEntity.ok(userPetService.addPet(userId, petDTO));
    }

    /**
     * 删除宠物
     *
     * @param petId
     * @return
     */
    @DeleteMapping("/del/pet")
    public ResponseEntity<Info<String>> delPet(@RequestParam Long petId) {
        return ResponseEntity.ok(userPetService.delPet(petId));
    }

    /**
     * 查询用户全部宠物
     *
     * @return
     */
    @GetMapping("/sel/pets")
    public ResponseEntity<Info<List<PetEntity>>> selPets(@RequestParam Long userId) {
        return ResponseEntity.ok(userPetService.selPets(userId));
    }

    /**
     * 查询宠物详细信息
     *
     * @param petId
     * @return
     */
    @GetMapping("/sel/pet")
    public ResponseEntity<Info<PetEntity>> selPet(@RequestParam Long petId) {
        return ResponseEntity.ok(userPetService.selPet(petId));
    }

    /**
     * 更新宠物信息
     * @param petDTO
     * @return
     */
    @PutMapping("/upd/pet")
    public ResponseEntity<Info<PetEntity>> updPet(
            @RequestBody @Validated(ExamineGroupUpd.class) PetDTO petDTO,
            BindingResult result
    ){
        VerificationProcess.revertMsg(result);
        return ResponseEntity.ok(userPetService.updPet(petDTO));
    }

    /**
     * 宠物绑定硬件信息
     * @param gpsId
     * @param petId
     * @return
     */
    @PostMapping("/binding/mac")
    public ResponseEntity<Info<String>> bindingMac(@RequestParam Long petId,@RequestParam String gpsId){
        return ResponseEntity.ok(userPetService.bindingMac(petId,gpsId));
    }

    /**
     * 宠物实时数据（经纬度，温度）
     * @param petId
     * @return
     */
    @GetMapping("/sel/data")
    public ResponseEntity<Info<?>> selPetData(@RequestParam Long petId){
        return ResponseEntity.ok(userPetService.selPetData(petId));
    }
}
