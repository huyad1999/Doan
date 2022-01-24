package com.huce.ltudm.doan_tra_cu_dulich.controller.admin;

import com.huce.ltudm.doan_tra_cu_dulich.controller.FileUploadController;
import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto2;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.Chitiet_diadiem_request;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.service.Diadiem_dulich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import javax.validation.Valid;
import java.util.List;

@Controller
@RequestMapping("/admin/diadiem")
public class Manager_Diadiem_dulich_Controller {

    @Autowired
    private Diadiem_dulich_Service diadiem_dulich_service;

    @PostMapping ("/add_dd")
    public ResponseEntity<?> addDiadiem_dulich(@Valid @RequestBody Chitiet_diadiem_request chitiet_dd){

        Diadiem_Dulich diadiem = diadiem_dulich_service.createDiadiem(chitiet_dd);

        return ResponseEntity.status(HttpStatus.OK).body(new Chitiet_diadiem_dto(diadiem));
    }
    @GetMapping("/")
    public ResponseEntity<List<Chitiet_diadiem_dto2>> getlist(){
        List<Chitiet_diadiem_dto2> list = diadiem_dulich_service.getListDiadiem();
        return ResponseEntity.status(HttpStatus.OK).body(list);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        diadiem_dulich_service.deleteDiadiem(id);
        return ResponseEntity.ok("Delete success");
    }
    @PutMapping("/update/{id}")
    public ResponseEntity<Chitiet_diadiem_dto> updateDiadiem_dulich(@Valid @RequestBody Chitiet_diadiem_request chitiet_dd, @PathVariable int id){
        Chitiet_diadiem_dto diadiem = diadiem_dulich_service.updateDiadiem(chitiet_dd,id);
        return ResponseEntity.status(HttpStatus.OK).body(diadiem);
    }

    @GetMapping("/search")
    public ResponseEntity<List<Chitiet_diadiem_dto2>> getDiadiembyName(@RequestParam String name){
        List<Chitiet_diadiem_dto2> chitiet_diadiem_dtos = diadiem_dulich_service.getDiadiembyName(name);
        return ResponseEntity.status(HttpStatus.OK).body(chitiet_diadiem_dtos);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Chitiet_diadiem_dto2> getDiadiembyId(@PathVariable int id){
        Chitiet_diadiem_dto2 chitiet_diadiem_dto = diadiem_dulich_service.getDiadiemByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(chitiet_diadiem_dto);
    }

}
