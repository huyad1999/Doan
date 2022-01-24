package com.huce.ltudm.doan_tra_cu_dulich.service;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import com.huce.ltudm.doan_tra_cu_dulich.entity.Diadiem_Dulich;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.CommentDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CommentReq;

import java.util.List;

public interface Comment_service {
    public List<CommentDto> getlistComment(int pid);
    public CommentDto getCommentByid(int id);
    public void deleteComment(int id);
    public CommentDto createComment(CommentReq cmt, int pid);
    public CommentDto updateComment(CommentReq cmt,int pid,int id);
}
