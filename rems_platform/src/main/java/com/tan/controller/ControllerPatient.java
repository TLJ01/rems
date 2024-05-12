package com.tan.controller;

import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.entity.EntityPageBean;
import com.tan.entity.EntityPatient;
import com.tan.entity.EntityResult;
import com.tan.service.ServicePatient;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:22
 */
@Slf4j
@RestController
@RequestMapping("/patients")
public class ControllerPatient {

    @Autowired
    private ServicePatient servicePatient;

    /**
     * 获取病人列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public EntityResult list(@RequestParam(defaultValue = "1") Integer currentPage, @RequestParam(defaultValue = "10") Integer pageSize){
        EntityPageBean<EntityPatient> entityPageBean = servicePatient.page(currentPage,pageSize);
        return EntityResult.success(entityPageBean);
    }

    /**
     * 根据id查询病人信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public EntityResult getById(@PathVariable Integer id){
        return servicePatient.getById(id);
    }

    /**
     * 根据病人名字或者病型进行查询
     * @param key
     * @return
     */
    @GetMapping("/key")
    public EntityResult getByNameOrCategory(String key){
        List<EntityPatient> list = servicePatient.getByNameOrCategory(key);
        return EntityResult.success(list);
    }

    /**
     * 根据id删除病人信息-->针对已经康复的人,逻辑删除就行,即更新is_deleted
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public EntityResult deleteById(@PathVariable Integer id){
        servicePatient.deleteById(id);
        return EntityResult.success();
    }


    /**
     * 新增病人
     * @param dtoPatient
     * @return
     */
    @PostMapping("/save")
    public EntityResult save(@RequestBody DtoPatientSave dtoPatient){
        return servicePatient.save(dtoPatient);
    }

    /**
     * 更新病人信息
     * @param dtoPatient
     * @return
     */
    @PutMapping("/update")
    public EntityResult update(@RequestBody DtoPatientUpdate dtoPatient){
        servicePatient.update(dtoPatient);
        return EntityResult.success();
    }




}
