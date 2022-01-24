package com.huce.ltudm.doan_tra_cu_dulich.repository;

import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface User_Repository extends JpaRepository<User,Integer> {
     @Query(value = "select * from user u where u.tai_khoan = ?1", nativeQuery = true)
     public User findByTai_khoan(String taikhoan);



}
