package com.huce.ltudm.doan_tra_cu_dulich.controller;

import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Dd_Noibat_dto;
import com.huce.ltudm.doan_tra_cu_dulich.service.Diadiem_dulich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/home")
public class Trangchu_controller {

    @Autowired
    Diadiem_dulich_Service diadiem_dulich_service;

    @GetMapping("/dia-diem-noi-bat")
    public ResponseEntity<List<Dd_Noibat_dto>> getList_Notbat(){
        List<Dd_Noibat_dto> dd_noibat_dto = diadiem_dulich_service.getList_DdNoibat();
        return ResponseEntity.status(HttpStatus.OK).body(dd_noibat_dto);
    }

}
