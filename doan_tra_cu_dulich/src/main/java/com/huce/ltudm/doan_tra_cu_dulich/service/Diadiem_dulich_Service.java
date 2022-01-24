package com.huce.ltudm.doan_tra_cu_dulich.service;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Chitiet_diadiem_dto2;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Dd_Noibat_dto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.GGmap;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.Chitiet_diadiem_request;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.Query;


import java.util.List;


public interface Diadiem_dulich_Service {
    public Diadiem_Dulich createDiadiem(Chitiet_diadiem_request chitiet_dd) ;
    public List<Chitiet_diadiem_dto2> getListDiadiem();

    boolean deleteDiadiem(int id);

    public Chitiet_diadiem_dto updateDiadiem(Chitiet_diadiem_request chitiet_dd,int id);

    public List<Dd_Noibat_dto> getList_DdNoibat();

    Chitiet_diadiem_dto2 getDiadiemByid(int id);

    List<Chitiet_diadiem_dto2> getDiadiembyName(String name);

    GGmap getToado(int id);
}
