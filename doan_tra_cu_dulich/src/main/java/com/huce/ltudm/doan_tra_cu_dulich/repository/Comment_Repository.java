package com.huce.ltudm.doan_tra_cu_dulich.repository;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface Comment_Repository extends JpaRepository<Comment, Integer> {
}
