package com.huce.ltudm.doan_tra_cu_dulich.entity;

import com.sun.istack.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


import javax.persistence.*;


import java.io.Serializable;
import java.util.List;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Table(name = "user")
public class User implements Serializable{
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    @NotNull
    @Column(name="tai_khoan", unique = true)
    private String tai_khoan;

    @Column(name = "mat_khau")
    private String mat_khau;

    @Column(name = "dia_chi")
    private String dia_chi;

    @Column(name = "sdt")
    private String sdt;

    @Column(name = "role", nullable = false, columnDefinition = "varchar(255) default 'USER'")
    private String role;

    @OneToMany(mappedBy = "user",cascade = CascadeType.ALL)
    private List<Comment> comments;


}
