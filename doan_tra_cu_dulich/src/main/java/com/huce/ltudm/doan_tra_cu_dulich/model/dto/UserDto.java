package com.huce.ltudm.doan_tra_cu_dulich.model.dto;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import lombok.Data;

@Data
public class UserDto {
    private int id;

    private String tai_khoan;

    private String dia_chi;

    private String sdt;

    private String role;

}
