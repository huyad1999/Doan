package com.huce.ltudm.doan_tra_cu_dulich.service.impl;



import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.entity.GioiThieuChung;
import com.huce.ltudm.doan_tra_cu_dulich.entity.LeHoi_VanHoa;
import com.huce.ltudm.doan_tra_cu_dulich.exception.NotFoundException;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto2;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Dd_Noibat_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.GGmap;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.Chitiet_diadiem_request;
import com.huce.ltudm.doan_tra_cu_dulich.property.FileStorageProperties;
import com.huce.ltudm.doan_tra_cu_dulich.repository.Diadiem_Dulich_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.repository.Gioi_thieu_chung_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.repository.Lehoi_Vanhoa_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.service.Diadiem_dulich_Service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import java.io.File;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.List;

@Service
public class Diadiem_dulich_ServiceImpl implements Diadiem_dulich_Service {
    public final static String PORT="http://localhost:8080/api/v1/FileUpload/files/";
    @Autowired
    Diadiem_Dulich_Repository diadiem_dulich_repository;
    @Autowired
    Gioi_thieu_chung_Repository gioi_thieu_chung_repository;
    @Autowired
    Lehoi_Vanhoa_Repository lehoi_vanhoa_repository;
    @Autowired
    FileStorageProperties fileStorageProperties;
    @Override
    public Diadiem_Dulich createDiadiem(Chitiet_diadiem_request chitiet_req) {
        Diadiem_Dulich dd = new Diadiem_Dulich(chitiet_req);

        GioiThieuChung gtc = new GioiThieuChung();
        gtc.setGioithieu_ngan(chitiet_req.getGioithieu_ngan());
        gtc.setAm_thuc(chitiet_req.getAm_thuc());
        gtc.setNoi_checkin_dep(chitiet_req.getNoi_checkin_dep());
        gtc.setPhuong_tien(chitiet_req.getPhuong_tien());
        gtc.setNoi_nghi(chitiet_req.getNoi_nghi());
        chitiet_req.setAnh_GT(chitiet_req.getAnh_GT());
        gtc.setAnh_GT(chitiet_req.getAnh_GT());

        LeHoi_VanHoa lhvh = new LeHoi_VanHoa();
        lhvh.setTen_LH(chitiet_req.getTen_LH());
        lhvh.setDacdiem_LH(chitiet_req.getDacdiem_LH());
        lhvh.setTen_VH(chitiet_req.getTen_VH());
        lhvh.setDacdiem_VH(chitiet_req.getDacdiem_VH());
        chitiet_req.setAnh_LHVH(chitiet_req.getAnh_LHVH());
        lhvh.setAnh_LHVH(chitiet_req.getAnh_LHVH());

        lhvh = lehoi_vanhoa_repository.save(lhvh);
        dd.setLeHoi_vanHoa(lhvh);
        gtc = gioi_thieu_chung_repository.save(gtc);
        dd.setGioiThieuChung(gtc);

        dd =diadiem_dulich_repository.save(dd);

        return dd;
    }

    @Override
    public List<Chitiet_diadiem_dto2> getListDiadiem() {
        List<Chitiet_diadiem_dto2> list_diadiem_dto =new ArrayList<>();

        for (Diadiem_Dulich dd:diadiem_dulich_repository.findAll()) {
            //covert dd sang dto
            Chitiet_diadiem_dto2 diadiem_dto = new Chitiet_diadiem_dto2(dd);

            diadiem_dto.setAnh_GT(xulyLinkAnh(diadiem_dto.getAnh_GT()));
            diadiem_dto.setAnh_phu(xulyLinkAnh(diadiem_dto.getAnh_phu()));
            diadiem_dto.setAnh_LHVH(xulyLinkAnh(diadiem_dto.getAnh_LHVH()));
            diadiem_dto.setAnh_title(PORT+diadiem_dto.getAnh_title());

            list_diadiem_dto.add(diadiem_dto);
        }

        return list_diadiem_dto;
    }

    @Override
    public boolean deleteDiadiem(int id) {
        for (Diadiem_Dulich d : diadiem_dulich_repository.findAll()){
            if (d.getId()==id){
                diadiem_dulich_repository.deleteById(id);
                return true;
            }
        }
        throw new NotFoundException("không tìm thấy địa điểm cần xóa");
    }

    @Override
    public Chitiet_diadiem_dto updateDiadiem(Chitiet_diadiem_request chitiet_dd,int id) {

        for (Diadiem_Dulich d : diadiem_dulich_repository.findAll()) {
            if (d.getId()==id){

            d.setTen_dddl(chitiet_dd.getTen_dddl());
            d.setKinh_do(chitiet_dd.getKinh_do());
            d.setVi_do(chitiet_dd.getVi_do());
            d.setMien(chitiet_dd.getMien());
            d.setAnh_phu(chitiet_dd.getAnh_phu());
            //xóa ảnh cũ
//            Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                        .toAbsolutePath().normalize();
//            Path targetLocation = fileStorageLocation.resolve(d.getAnh_title());
//            File file = new File(targetLocation.toString());
//                if (file.exists()){
//                    file.delete();
//                }
            d.setAnh_title(chitiet_dd.getAnh_title());

            GioiThieuChung gtc = d.getGioiThieuChung();
            gtc.setGioithieu_ngan(chitiet_dd.getGioithieu_ngan());
            gtc.setPhuong_tien(chitiet_dd.getPhuong_tien());
            gtc.setNoi_nghi(chitiet_dd.getNoi_nghi());
            gtc.setNoi_checkin_dep(chitiet_dd.getNoi_checkin_dep());
            gtc.setAm_thuc(chitiet_dd.getAm_thuc());

            gtc.setAnh_GT(chitiet_dd.getAnh_GT());
            gtc=gioi_thieu_chung_repository.save(gtc);
            d.setGioiThieuChung(gtc);

            LeHoi_VanHoa lhvh = d.getLeHoi_vanHoa();
            lhvh.setTen_LH(chitiet_dd.getTen_LH());
            lhvh.setDacdiem_LH(chitiet_dd.getDacdiem_LH());
            lhvh.setTen_VH(chitiet_dd.getTen_VH());
            lhvh.setDacdiem_VH(chitiet_dd.getDacdiem_VH());

            lhvh.setAnh_LHVH(chitiet_dd.getAnh_LHVH());
            lhvh = lehoi_vanhoa_repository.save(lhvh);
            d.setLeHoi_vanHoa(lhvh);

            diadiem_dulich_repository.save(d);
            Chitiet_diadiem_dto chitiet_diadiem_dto = new Chitiet_diadiem_dto(d);

            return chitiet_diadiem_dto;
            }
        }
       throw new NotFoundException("khong tim thay dia diem co id ="+id);
    }

    @Override
    public List<Dd_Noibat_dto> getList_DdNoibat() {
        List<Dd_Noibat_dto> nBat_dto=diadiem_dulich_repository.getList_Dd_noibat();
        List<Dd_Noibat_dto> noibat_dtos = new ArrayList<>();
        for (Dd_Noibat_dto noibat_dto:nBat_dto) {

            noibat_dto.setAnh_title(PORT+noibat_dto.getAnh_title());
            noibat_dtos.add(noibat_dto);
        }
        return noibat_dtos;
    }

    @Override
    public Chitiet_diadiem_dto2 getDiadiemByid(int id) {
        for (Diadiem_Dulich d : diadiem_dulich_repository.findAll()){
            if (d.getId()==id){
                Chitiet_diadiem_dto2 chitiet_diadiem_dto = new Chitiet_diadiem_dto2(d);

                chitiet_diadiem_dto.setAnh_GT(xulyLinkAnh(chitiet_diadiem_dto.getAnh_GT()));
                chitiet_diadiem_dto.setAnh_phu(xulyLinkAnh(chitiet_diadiem_dto.getAnh_GT()));
                chitiet_diadiem_dto.setAnh_LHVH(xulyLinkAnh(chitiet_diadiem_dto.getAnh_LHVH()));
                chitiet_diadiem_dto.setAnh_title(PORT+chitiet_diadiem_dto.getAnh_title());
                return chitiet_diadiem_dto;
            }
        }
        throw new NotFoundException("không tìm thấy địa điểm có id:"+id);
    }

    @Override
    public List<Chitiet_diadiem_dto2> getDiadiembyName(String name) {
        List<Diadiem_Dulich> lists = diadiem_dulich_repository.finbyName(name);
        List<Chitiet_diadiem_dto2> diadiemDtoList = new ArrayList<>();
        if (lists != null){
        for (Diadiem_Dulich d : lists) {

            Chitiet_diadiem_dto2 chitiet_diadiem_dto = new Chitiet_diadiem_dto2(d);

            chitiet_diadiem_dto.setAnh_GT(xulyLinkAnh(chitiet_diadiem_dto.getAnh_GT()));
            chitiet_diadiem_dto.setAnh_phu(xulyLinkAnh(chitiet_diadiem_dto.getAnh_GT()));
            chitiet_diadiem_dto.setAnh_LHVH(xulyLinkAnh(chitiet_diadiem_dto.getAnh_LHVH()));
            chitiet_diadiem_dto.setAnh_title(PORT + chitiet_diadiem_dto.getAnh_title());
            diadiemDtoList.add(chitiet_diadiem_dto);
            }
        return diadiemDtoList;
        }
            throw new NotFoundException("không tìm thấy ten nào:"+name);

    }

    @Override
    public GGmap getToado(int id) {
        Diadiem_Dulich diadiem_dulich = diadiem_dulich_repository.findById(id).get();
        GGmap gGmap = new GGmap();
        gGmap.setKinh_do(diadiem_dulich.getKinh_do());
        gGmap.setVi_do(diadiem_dulich.getVi_do());
        return gGmap;
    }

    protected List<String> xulyLinkAnh(List<String> listlink){
        List<String> result = new ArrayList<>();
        for (String s: listlink) {
            s = PORT+s;
            result.add(s);
        }
        return result;
    }

//    protected List<String> xoaAnh(List<String> list){
//        for (String s: list) {
//            Path fileStorageLocation = Paths.get(fileStorageProperties.getUploadDir())
//                    .toAbsolutePath().normalize();
//            Path targetLocation = fileStorageLocation.resolve(s);
//            File file = new File(targetLocation.toString());
//            if (file.exists()){
//                file.delete();
//                list.remove(s);
//            }else throw new NotFoundException("thu muc trống");
//        }
//        return list;
//    }
}
