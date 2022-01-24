package com.huce.ltudm.doan_tra_cu_dulich.entity;

import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Dd_Noibat_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.Chitiet_diadiem_request;
import lombok.*;
import org.hibernate.annotations.Type;
import org.springframework.data.jpa.repository.Query;

import javax.persistence.*;
import java.util.Date;
import java.util.List;
@SqlResultSetMappings(value = {
        @SqlResultSetMapping(
                name = "Dd_Noibat_dto",
                classes = @ConstructorResult(
                        targetClass = Dd_Noibat_dto.class,
                        columns = {
                                @ColumnResult(name = "anh_title",type = String.class),
                                @ColumnResult(name = "ten_dddl",type = String.class),
                                @ColumnResult(name = "diachi",type = String.class),
                                @ColumnResult(name = "gioithieu_ngan",type = String.class)
                        }
                )
        )
})
@NamedNativeQuery(
        name = "get_Dd_noibat_dto",
        resultSetMapping = "Dd_Noibat_dto",
        query = "select dd.ten_dddl,gtc.gioithieu_ngan,dd.anh_title,dd.diachi\n" +
                "from diadiem_dulich dd\n" +
                "inner join gioi_thieu_chung gtc on dd.gt_chung_id = gtc.id \n" +
                "order by dd.id desc\n" +
                "limit 3")
@Entity
@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter

public class Diadiem_Dulich {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;
    @Column(name = "ten_DDDL")
    private String ten_dddl;
    @Column(name = "kinh_do")
    private float kinh_do;
    @Column(name = "vi_do")
    private float vi_do;
    @Column(name = "ngay_dang_bai")
    private Date date_dang_bai=new Date();
    @Column(name = "diachi")
    private String diachi;
    @Column(name = "mien")
    private String mien;
    @Column(name = "anh_title")
    private String anh_title;

    @Type(type = "json")
    @Column(name = "anh_phu",columnDefinition = "json")
    private List<String> anh_phu;

    @OneToMany(fetch = FetchType.LAZY,mappedBy = "diadiem_dulichs",cascade = CascadeType.ALL)
    private List<Comment> commentList;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "GT_chung_id",referencedColumnName = "id")
    private GioiThieuChung gioiThieuChung;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "LeHoi_Vanhoa_id",referencedColumnName = "id")
    private LeHoi_VanHoa leHoi_vanHoa;

    public Diadiem_Dulich(Chitiet_diadiem_request chitiet_rq){
        this.ten_dddl =chitiet_rq.getTen_dddl();
        this.kinh_do =chitiet_rq.getKinh_do();
        this.vi_do =chitiet_rq.getVi_do();
        this.diachi =chitiet_rq.getDiachi();
        this.mien = chitiet_rq.getMien();
        this.anh_phu = chitiet_rq.getAnh_phu();
        this.anh_title = chitiet_rq.getAnh_title();

    }
}
