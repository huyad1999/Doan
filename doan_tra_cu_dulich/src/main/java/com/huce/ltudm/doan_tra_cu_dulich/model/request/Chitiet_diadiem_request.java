package com.huce.ltudm.doan_tra_cu_dulich.model.request;


import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.validation.constraints.Size;
import java.util.List;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class Chitiet_diadiem_request {

    @Size(min = 3,max = 200,message = "từ 3-30 ký tự ")
    private String ten_dddl;

    private float kinh_do;

    private float vi_do;
    @Size(min = 3,max = 30,message = "từ 3-30 ký tự ")
    private String diachi;

    private String mien;

    private List<String> anh_phu;

    private String anh_title;

    @Size(min = 5,max = 500,message = "từ 5-500 ký tự ")
    private String gioithieu_ngan;

    private String am_thuc;

    private String phuong_tien;

    private String noi_nghi;

    private String noi_checkin_dep;

    private List<String> anh_GT;

    private String ten_VH;

    private String ten_LH;

    private String dacdiem_VH;

    private String dacdiem_LH;

    private List<String> anh_LHVH;

}
