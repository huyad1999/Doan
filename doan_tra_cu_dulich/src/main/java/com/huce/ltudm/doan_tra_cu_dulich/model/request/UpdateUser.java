package com.huce.ltudm.doan_tra_cu_dulich.model.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class UpdateUser {
    @Size(min = 3,max = 100)
    private String dia_chi;

    @Pattern(regexp="(09|01[2|6|8|9])+([0-9]{8})\\b", message = "nhập đúng định dạng số điện thoại")
    private String sdt;
}
