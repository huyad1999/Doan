package com.huce.ltudm.doan_tra_cu_dulich.model.mapper;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.UserDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CreateUserReq;
import org.springframework.security.crypto.bcrypt.BCrypt;

public class UserMapper {
    public static UserDto toUserDto(User user) {
        UserDto tmp = new UserDto();
        tmp.setId(user.getId());
        tmp.setTai_khoan(user.getTai_khoan());
        tmp.setSdt(user.getSdt());
        tmp.setDia_chi(user.getDia_chi());
        tmp.setRole(user.getRole());

        return tmp;
    }

    public static User toUser(CreateUserReq req) {
        User user = new User();
        user.setTai_khoan(req.getTai_khoan());
        user.setDia_chi(req.getDia_chi());
        user.setSdt(req.getSdt());
        // ma hoa mk
        String code = BCrypt.hashpw(req.getMat_khau(), BCrypt.gensalt(12));
        user.setMat_khau(code);
        user.setRole(req.getRole());

        return user;
    }
}