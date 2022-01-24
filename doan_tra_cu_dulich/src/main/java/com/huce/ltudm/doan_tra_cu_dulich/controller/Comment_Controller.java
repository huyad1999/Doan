package com.huce.ltudm.doan_tra_cu_dulich.controller;

import com.huce.ltudm.doan_tra_cu_dulich.entity.Comment;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.CommentDto;
import com.huce.ltudm.doan_tra_cu_dulich.model.dto.ResponseObject;
import com.huce.ltudm.doan_tra_cu_dulich.model.request.CommentReq;
import com.huce.ltudm.doan_tra_cu_dulich.service.Comment_service;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;
import java.util.List;
import java.util.Optional;

@RestController
public class Comment_Controller {
    @Autowired
    Comment_service comment_service;

    @GetMapping("/diadiem_detail/{pid}/comments")
    public ResponseEntity<List<CommentDto>> retrieveComments(@PathVariable("pid") int pid){
        return ResponseEntity.ok(comment_service.getlistComment(pid));
    }

    @GetMapping("/diadiem_detail/{pid}/comments/{id}")
    public ResponseEntity<CommentDto> getComment(@PathVariable("id") int id){
        return ResponseEntity.ok(comment_service.getCommentByid(id));
    }

    @DeleteMapping("/diadiem_detail/{pid}/comments/{id}")
    public ResponseEntity<?> deleteComment(@PathVariable("id") int id){
        comment_service.deleteComment(id);
        return ResponseEntity.ok("xóa comment thành công");
    }

    @PostMapping("/diadiem_detail/{pid}/comments")
    public ResponseEntity<Object> createComment(@RequestBody CommentReq comment, @PathVariable("pid") int pid){
        CommentDto cmt = comment_service.createComment(comment,pid);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cmt.getId()).toUri();

        return ResponseEntity.created(location).body("da comment");
    }

    @PutMapping("/diadiem_detail/{pid}/comments/{id}")
    public ResponseEntity<Object> updateComment(@RequestBody CommentReq comment,
                                                @PathVariable("id") int id,
                                                @PathVariable("pid") int pid) {
        CommentDto cmt = comment_service.updateComment(comment,pid,id);
        URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(cmt.getId()).toUri();
        return ResponseEntity.created(location).build();
    }
}
