package com.tan.controller;

import com.tan.dto.DtoPatientSave;
import com.tan.dto.DtoPatientUpdate;
import com.tan.pojo.PageBean;
import com.tan.pojo.Patient;
import com.tan.pojo.Result;
import com.tan.service.ServicePatient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Created by TanLiangJie
 * Time:2024/5/3 下午1:22
 */
@RestController
@RequestMapping("/patients")
public class ControllerPatient {

    @Autowired
    private ServicePatient servicePatient;

    /**
     * # TODO 这里应该加个判断,让每个医生看到自己的病人
     * 获取病人列表
     * @param currentPage
     * @param pageSize
     * @return
     */
    @GetMapping("/list")
    public Result list(@RequestParam(defaultValue = "1") Integer currentPage,@RequestParam(defaultValue = "10") Integer pageSize){
        PageBean<Patient> pageBean = servicePatient.page(currentPage,pageSize);
        return Result.success(pageBean);
    }

    /**
     * 根据id查询病人信息
     * @param id
     * @return
     */
    @GetMapping("/{id}")
    public Result getById(@PathVariable Integer id){
        Patient patient = servicePatient.getById(id);
        return Result.success(patient);
    }

    /**
     * 根据病人名字或者病型进行查询
     * @param key
     * @return
     */
    @GetMapping("/key")
    public Result getByNameOrCategory(String key){
        List<Patient> list = servicePatient.getByNameOrCategory(key);
        return Result.success(list);
    }

    /**
     * 根据id删除病人信息-->针对已经康复的人,逻辑删除就行,即更新is_deleted
     * @param id
     * @return
     */
    @DeleteMapping("/{id}")
    public Result deleteById(@PathVariable Integer id){
        servicePatient.deleteById(id);
        return Result.success();
    }


    /**
     * 新增病人
     * @param dtoPatient
     * @return
     */
    @PostMapping("/save")
    public Result save(@RequestBody DtoPatientSave dtoPatient){
        servicePatient.save(dtoPatient);
        return Result.success();
    }

    /**
     * 更新病人信息
     * @param dtoPatient
     * @return
     */
    @PutMapping("/update")
    public Result update(@RequestBody DtoPatientUpdate dtoPatient){
        servicePatient.update(dtoPatient);
        return Result.success();
    }




}
