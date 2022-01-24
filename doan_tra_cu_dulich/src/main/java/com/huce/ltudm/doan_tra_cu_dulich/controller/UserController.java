package com.huce.ltudm.doan_tra_cu_dulich.controller;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.UserDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.ChangePasswordReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CreateUserReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.LoginReq;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.UpdateUser;
import com.huce.ltudm.doan_tra_cu_dulich.security.CustomUserDetails;
import com.huce.ltudm.doan_tra_cu_dulich.security.JwtTokenUtil;
import com.huce.ltudm.doan_tra_cu_dulich.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/user")
public class UserController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private UserService userService;


    @PostMapping("/register")
    public ResponseEntity<?> createUser(@Valid @RequestBody CreateUserReq req) {
        UserDto result = userService.createUser(req);
        return ResponseEntity.ok(result);
    }

    @PostMapping("/login")
    public ResponseEntity<?> login(@Valid @RequestBody LoginReq loginReq) {
        // Xác thực từ username và password.
        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        loginReq.getTai_khoan(),
                        loginReq.getMatkhau()
                )
        );

        // Nếu không xảy ra exception tức là thông tin hợp lệ
        // Set thông tin authentication vào Security Context
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Gen token
        String token = jwtTokenUtil.generateToken((UserDetails) authentication.getPrincipal());

        return ResponseEntity.ok(token);
    }
    @PutMapping("/change-password")
    public ResponseEntity<?> doiMK(@Valid @RequestBody ChangePasswordReq req){
        //lấy ra user hiện tại đang đăng nhập
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        userService.changePassword(user, req);
        return ResponseEntity.ok("Đổi mật khẩu thành công");
    }
    @PutMapping("/update-user")
    public ResponseEntity<?> updateUser(@Valid @RequestBody UpdateUser updateUser){
        userService.updateUser(updateUser);
        return ResponseEntity.ok("Cập nhật tài khoản thành công");

    }
}