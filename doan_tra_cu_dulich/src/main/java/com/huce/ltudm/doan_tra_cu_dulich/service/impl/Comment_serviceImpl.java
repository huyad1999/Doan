package com.huce.ltudm.doan_tra_cu_dulich.service.impl;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.entity.User;
import com.huce.ltudm.doan_tra_cu_dulich.exception.NotFoundException;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.CommentDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CommentReq;
import com.huce.ltudm.doan_tra_cu_dulich.repository.Comment_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.repository.Diadiem_Dulich_Repository;
import com.huce.ltudm.doan_tra_cu_dulich.security.CustomUserDetails;
import com.huce.ltudm.doan_tra_cu_dulich.service.Comment_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class Comment_serviceImpl implements Comment_service {
    @Autowired
    Comment_Repository comment_repository;
    @Autowired
    Diadiem_Dulich_Repository diadiem_dulich_repository;
    @Override
    public List<CommentDto> getlistComment(int pid) {
        Diadiem_Dulich diadiem_dulich = diadiem_dulich_repository.findById(pid).get();
        List<CommentDto> list = new ArrayList<>();
        for (Comment a: diadiem_dulich.getCommentList()
             ) {
            CommentDto commentDto = new CommentDto(a);
            list.add(commentDto);
        }

        return list;
    }

    @Override
    public CommentDto getCommentByid(int id) {
        Comment comment = comment_repository.getById(id);

        return new CommentDto(comment);
    }

    @Override
    public void deleteComment(int id) {
       comment_repository.deleteById(id);
    }

    @Override
    public CommentDto createComment(CommentReq cmt, int pid) {
        Diadiem_Dulich diadiem_dulich = diadiem_dulich_repository.findById(pid).get();
        if (diadiem_dulich == null|| cmt ==null){
            throw new NotFoundException("khong thay id bai viet");
        }
        User user = ((CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal()).getUser();

        Comment newCmt = new Comment();
        newCmt.setTaikhoan_cmt(user.getTai_khoan());
        newCmt.setNoi_dung(cmt.getNoidung());
        newCmt.setDiadiem_dulichs(diadiem_dulich);
        newCmt.setUser(user);
        comment_repository.save(newCmt);
        return new CommentDto(newCmt);


    }

    @Override
    public CommentDto updateComment(CommentReq cmt, int pid, int id) {
        Diadiem_Dulich diadiem_dulich = diadiem_dulich_repository.findById(pid).get();
        if (diadiem_dulich == null|| cmt ==null){
            throw new NotFoundException("khong thay id bai viet");
        }
        Comment comment = comment_repository.findById(id).get();
        comment.setNoi_dung(cmt.getNoidung());
        comment_repository.save(comment);
        return new CommentDto(comment);
    }
}
