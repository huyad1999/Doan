package com.huce.ltudm.doan_tra_cu_dulich.model.request;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ChangePasswordReq {
    @NotNull(message = "Mật khẩu cũ trống")
    @NotEmpty(message = "Mật khẩu cũ trống")
    @Size(min = 4, max = 20, message = "Mật khẩu phải chứa từ 4 - 20 ký tự")
    @JsonProperty("matkhaucu")
    private String matkhaucu;

    @NotNull(message = "Mật khẩu mới trống")
    @NotEmpty(message = "Mật khẩu mới trống")
    @Size(min = 4, max = 20, message = "Mật khẩu phải chứa từ 4 - 20 ký tự")
    @JsonProperty("matkhaumoi")
    private String matkhaumoi;
}
