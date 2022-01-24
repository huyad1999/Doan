package com.huce.ltudm.doan_tra_cu_dulich.model.dto;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class CommentDto {
    private int id;
    private String tai_khoancmt;
    private String noidung;
    private String time_cmt;

    public CommentDto(Comment cmt) {
        this.id = cmt.getId();
        this.tai_khoancmt = cmt.getTaikhoan_cmt();
        this.noidung = cmt.getNoi_dung();
        this.time_cmt = cmt.getTime_cmt().toString();
    }
}
