package com.huce.ltudm.doan_tra_cu_dulich.repository;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.Dd_Noibat_dto;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface Diadiem_Dulich_Repository extends JpaRepository<Diadiem_Dulich,Integer> {
    @Query(nativeQuery = true, name = "get_Dd_noibat_dto")
    public List<Dd_Noibat_dto> getList_Dd_noibat();
    @Query("Select dd from Diadiem_Dulich dd where dd.ten_dddl like %:name%")
    public List<Diadiem_Dulich> finbyName(String name);

}
