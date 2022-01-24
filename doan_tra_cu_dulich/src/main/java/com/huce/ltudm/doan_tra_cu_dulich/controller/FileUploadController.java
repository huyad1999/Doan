package com.huce.ltudm.doan_tra_cu_dulich.controller;

import com.huce.ltudm.doan_tra_cu_dulich.model.dto.ResponseObject;
import com.huce.ltudm.doan_tra_cu_dulich.service.IStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.mvc.method.annotation.MvcUriComponentsBuilder;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping(path = "/api/v1/FileUpload")
public class FileUploadController {
    //Inject Storage Service here
    @Autowired
    private IStorageService storageService;
    @PostMapping("")
    public ResponseEntity<ResponseObject> uploadFile(@RequestParam("file")MultipartFile file) {
        try {
            //lưu trong folder
            String generatedFileName = storageService.storeFile(file);
                //convert fileName to url(send request "readDetailFile")
//                String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                        "readDetailFile", generatedFileName.toString()).build().toUri().toString();
            return ResponseEntity.status(HttpStatus.OK).body(
               new ResponseObject("ok", "upload thành công", generatedFileName)
            );

        }catch (Exception exception) {
            return ResponseEntity.status(HttpStatus.NOT_IMPLEMENTED).body(
               new ResponseObject("ok", exception.getMessage(), "")
            );
        }
    }
    @PostMapping("/multi-file")
    public ResponseEntity<ResponseObject> uploadFileMulti(@RequestParam("files")MultipartFile[] uploadfiles) {
        List<String> listfile = new ArrayList<>();
        for (MultipartFile file : uploadfiles) {
            String generatedfilename = storageService.storeFile(file);

            listfile.add(generatedfilename);
        }
        return ResponseEntity.status(HttpStatus.OK).body(
                new ResponseObject("ok", "upload thanh cong", listfile)
        );
    }
    //get image's url
    @GetMapping("/files/{fileName:.+}")
     // /files/06a290064eb94a02a58bfeef36002483.png
    public ResponseEntity<byte[]> readDetailFile(@PathVariable String fileName) {
        try {
            byte[] bytes = storageService.readFileContent(fileName);
            return ResponseEntity
                    .ok()
                    .contentType(MediaType.IMAGE_JPEG)
                    .body(bytes);
        }catch (Exception exception) {
            return ResponseEntity.noContent().build();
        }
    }
    //load tất cả ảnh trong thư mục ?
//    @GetMapping("")
//    public ResponseEntity<ResponseObject> getUploadedFiles() {
//        try {
//            List<String> urls = storageService.loadAll()
//                    .map(path -> {
//                        //convert fileName to url(send request "readDetailFile")
//                        String urlPath = MvcUriComponentsBuilder.fromMethodName(FileUploadController.class,
//                                "readDetailFile", path.getFileName().toString()).build().toUri().toString();
//                        return urlPath;
//                    })
//                    .collect(Collectors.toList());
//            return ResponseEntity.ok(new ResponseObject("ok", "List files successfully", urls));
//        }catch (Exception exception) {
//            return ResponseEntity.ok(new
//                    ResponseObject("failed", "List files failed", new String[] {}));
//        }
//    }
}
