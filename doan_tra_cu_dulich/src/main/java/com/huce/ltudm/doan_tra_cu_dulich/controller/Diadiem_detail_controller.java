package com.huce.ltudm.doan_tra_cu_dulich.controller;

import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto2;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.GGmap;
import com.huce.ltudm.doan_tra_cu_dulich.service.Diadiem_dulich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/diadiem_detail")
public class Diadiem_detail_controller {
    @Autowired
    Diadiem_dulich_Service diadiem_dulich_service;

    @GetMapping("/{id}")
    public ResponseEntity<Chitiet_diadiem_dto2> getDiadiembyId(@PathVariable int id){
        Chitiet_diadiem_dto2 chitiet_diadiem_dto = diadiem_dulich_service.getDiadiemByid(id);
        return ResponseEntity.status(HttpStatus.OK).body(chitiet_diadiem_dto);
    }
    @GetMapping("/search")
    public ResponseEntity<List<Chitiet_diadiem_dto2>> getDiadiembyName(@RequestParam String name){
        List<Chitiet_diadiem_dto2> chitiet_diadiem_dtos = diadiem_dulich_service.getDiadiembyName(name);
        return ResponseEntity.status(HttpStatus.OK).body(chitiet_diadiem_dtos);
    }
    @GetMapping("/toado/{id}")
    public ResponseEntity<GGmap> gettoado(@PathVariable int id){
        GGmap gGmap = diadiem_dulich_service.getToado(id);
        return ResponseEntity.status(HttpStatus.OK).body(gGmap);
    }
}
