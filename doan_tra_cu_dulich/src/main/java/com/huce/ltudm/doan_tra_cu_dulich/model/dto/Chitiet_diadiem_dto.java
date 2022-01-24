package com.huce.ltudm.doan_tra_cu_dulich.model.dto;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import lombok.Data;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Data
public class Chitiet_diadiem_dto {
       private int id;

       private String ten_dddl;

       private float kinh_do;

       private float vi_do;

       private String diachi;

       private String mien;

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

       private List<String> anh_phu;

       private String anh_title;

//       private List<CommentDto> commentlist;

       // mapper du lieu ve database
       public Chitiet_diadiem_dto(Diadiem_Dulich dd){
              this.id = dd.getId();
              this.ten_dddl = dd.getTen_dddl();
              this.diachi =dd.getDiachi();
              this.kinh_do = dd.getKinh_do();
              this.vi_do = dd.getVi_do();
              this.mien =dd.getMien();

              this.gioithieu_ngan =dd.getGioiThieuChung().getGioithieu_ngan();
              this.am_thuc =dd.getGioiThieuChung().getAm_thuc();
              this.phuong_tien =dd.getGioiThieuChung().getPhuong_tien();
              this.noi_nghi =dd.getGioiThieuChung().getNoi_nghi();
              this.noi_checkin_dep =dd.getGioiThieuChung().getNoi_checkin_dep();
              this.anh_GT =dd.getGioiThieuChung().getAnh_GT();

              this.ten_VH =dd.getLeHoi_vanHoa().getTen_VH();
              this.dacdiem_VH =dd.getLeHoi_vanHoa().getDacdiem_VH();
              this.ten_LH =dd.getLeHoi_vanHoa().getTen_LH();
              this.dacdiem_LH =dd.getLeHoi_vanHoa().getDacdiem_LH();
              this.anh_LHVH = dd.getLeHoi_vanHoa().getAnh_LHVH();

             this.anh_title = dd.getAnh_title();
             this.anh_phu = dd.getAnh_phu();
//             List<CommentDto> list = new ArrayList<>();
//              for (Comment a:dd.getCommentList()) {
//                     CommentDto commentDto = new CommentDto(a);
//                     list.add(commentDto);
//              }
//              this.commentlist = list;
       }

}
