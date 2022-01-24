package com.huce.ltudm.doan_tra_cu_dulich.controller.admin;

import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.UserDto;
import com.huce.ltudm.doan_tra_cu_dulich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/admin/user")
public class Manager_UserController {
    @Autowired
    private UserService userService;

    @GetMapping("")
    public ResponseEntity<List<UserDto>> getlistUser(){
        List<UserDto> userDtos = userService.getListUser();
        return ResponseEntity.status(HttpStatus.OK).body(userDtos);
    }

    @DeleteMapping("/delete/{id}")
    public ResponseEntity<?> deleteUser(@PathVariable int id) {
        userService.deleteUser(id);
        return ResponseEntity.ok("Delete thành công");
    }
    @GetMapping("/search")
    public ResponseEntity<UserDto> getUserbytaikhoan(@RequestParam String taikhoan){
        UserDto userDto = userService.getbyTaikhoan(taikhoan);
        return ResponseEntity.status(HttpStatus.OK).body(userDto);
    }

}
