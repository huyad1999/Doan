package com.huce.ltudm.doan_tra_cu_dulich.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "Comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "TK_cmt")
    private String taikhoan_cmt;
    @Column(name = "noi_dung", columnDefinition = "TEXT")
    private String noi_dung;
    @Column(name = "thoi_gian_cmt")
    private Date time_cmt = new Date();

    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    @ManyToOne
    @JoinColumn(name = "diadiem_dulich_id")
    private Diadiem_Dulich diadiem_dulichs;
}
