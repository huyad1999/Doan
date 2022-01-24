package com.huce.ltudm.doan_tra_cu_dulich.service;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.UserDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.ChangePasswordReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CreateUserReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.LoginReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.UpdateUser;
import org.springframework.stereotype.Service;

import java.util.List;

public interface UserService {
    public UserDto createUser(CreateUserReq req);

    public void changePassword(User user, ChangePasswordReq req);

    List<UserDto> getListUser();

    boolean deleteUser(int id);

    UserDto getbyTaikhoan(String taikhoan);

    void updateUser(UpdateUser updateUser);
}
