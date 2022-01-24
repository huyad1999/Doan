package com.huce.ltudm.doan_tra_cu_dulich.model.request;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Setter
@Getter
public class LoginReq {
    @NotNull(message = "tai khoan là bắt buộc,từ 3 đến 30 ký tự")
    @NotEmpty(message = "tai khoan là bắt buộc,từ 3 đến 30 ký tự")
    @Size(min = 3,max = 30)
    private String tai_khoan;

    @NotNull(message = "mật khẩu là bắt buộc")
    @NotEmpty(message = "mật khẩu là bắt buộc")
    @Size(min = 4, max = 20, message = "mật khẩu phải từ 4 - 20 kí tự")
    private String matkhau;
}
