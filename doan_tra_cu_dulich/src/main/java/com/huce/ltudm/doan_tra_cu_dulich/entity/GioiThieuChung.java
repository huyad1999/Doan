package com.huce.ltudm.doan_tra_cu_dulich.entity;


import com.vladmihalcea.hibernate.type.json.JsonStringType;
import lombok.*;
import org.hibernate.annotations.Type;
import org.hibernate.annotations.TypeDef;


import javax.persistence.*;

import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

@TypeDef(
        name = "json",
        typeClass = JsonStringType.class
)
public class GioiThieuChung {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "gioithieu_ngan", length = 500)
    private String gioithieu_ngan;
    @Column(name = "am_thuc")
    private String am_thuc;
    @Column(name = "phuong_tien")
    private String phuong_tien;
    @Column(name = "noi_nghi")
    private String noi_nghi;
    @Column(name = "noi_checkin_dep",length = 500)
    private String noi_checkin_dep;
    @Type(type = "json")
    @Column(name = "anh_GT",columnDefinition = "json")
    private List<String> anh_GT;

   
}
