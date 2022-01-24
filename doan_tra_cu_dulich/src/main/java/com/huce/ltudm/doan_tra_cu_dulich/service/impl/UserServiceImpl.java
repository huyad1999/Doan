package com.huce.ltudm.doan_tra_cu_dulich.service.impl;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.exception.BadRequestException;
import com.huce.ltudm.doan_tra_cu_dulich.exception.DuplicateRecordException;
import com.huce.ltudm.doan_tra_cu_dulich.exception.NotFoundException;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.UserDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.mapper.UserMapper;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.ChangePasswordReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CreateUserReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.LoginReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.UpdateUser;
import com.huce.ltudm.doan_tra_cu_dulich.repository.User_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.security.CustomUserDetails;
import com.huce.ltudm.doan_tra_cu_dulich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.bcrypt.BCrypt;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class UserServiceImpl implements UserService {
    @Autowired
    private User_Repository userRepository;

    @Override
    public UserDto createUser(CreateUserReq req) {
        // Check tai khoan
        User user = userRepository.findByTai_khoan(req.getTai_khoan());
        if (user != null) {
            throw new DuplicateRecordException("tai khoan da ton tai");
        }

        user = UserMapper.toUser(req);
        userRepository.save(user);

        return UserMapper.toUserDto(user);
    }

    @Override
    public void changePassword(User user, ChangePasswordReq req) {
        // check mk cu
        if (!BCrypt.checkpw(req.getMatkhaucu(), user.getMat_khau())) {
            throw new BadRequestException("Mật khẩu cũ không chính xác");
        }
        String code = BCrypt.hashpw(req.getMatkhaumoi(), BCrypt.gensalt(12));
        user.setMat_khau(code);

        userRepository.save(user);
    }

    @Override
    public List<UserDto> getListUser() {
            List<User> userList =userRepository.findAll();
            List<UserDto> userDtoslist = new ArrayList<>();
        for (User u: userList) {
            UserDto userDto = UserMapper.toUserDto(u);
            userDtoslist.add(userDto);
        }
            return userDtoslist;
    }

    @Override
    public boolean deleteUser(int id) {

            for (User user : userRepository.findAll()){
                if (user.getId()==id){
                    userRepository.deleteById(id);
                    return true;
                }
            }
            throw new NotFoundException("không tìm thấy địa điểm cần xóa");

        }

    @Override
    public UserDto getbyTaikhoan(String taikhoan) {
        for (User user : userRepository.findAll()){
            if (user.getTai_khoan().equals(taikhoan)){
                 UserDto userDto = UserMapper.toUserDto(user);
                 return userDto;
            }
        }
        throw new NotFoundException("không tìm thấy tài khoản");
    }

    @Override
    public void updateUser(UpdateUser updateUser) {
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();
        user.setSdt(updateUser.getSdt());
        user.setDia_chi(updateUser.getDia_chi());
        userRepository.save(user);
    }
}

