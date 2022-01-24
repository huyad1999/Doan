package com.huce.ltudm.doan_tra_cu_dulich.model.request;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.Column;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class CreateUserReq {
    @NotNull(message = "Họ tên trống")
    @NotEmpty(message = "Họ tên trống")
    private String tai_khoan;

    @NotNull(message = "Mật khẩu trống")
    @NotEmpty(message = "Mật khẩu trống")
    @Size(min = 4, max = 20, message = "Mật khẩu phải chứa từ 4 - 20 ký tự")
    private String mat_khau;

    @Size(min = 3, max = 30, message = "địa chỉ phải chứa từ 3 - 30 ký tự")
    private String dia_chi;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "nhập đúng định dạng số điện thoại")
    private String sdt;

    private String role;
}