package com.huce.ltudm.doan_tra_cu_dulich.service.impl;

import com.huce.ltudm.doan_tra_cu_dulich.property.FileStorageProperties;
import com.huce.ltudm.doan_tra_cu_dulich.service.IStorageService;

import org.apache.commons.io.FilenameUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.UrlResource;
import org.springframework.stereotype.Service;
import org.springframework.util.StreamUtils;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.UUID;
import java.util.stream.Stream;

@Service
public class ImageStorageServiceImpl implements IStorageService {
    private final Path fileStorageProperties;
    //constructor
    @Autowired
    public ImageStorageServiceImpl(FileStorageProperties fileStorageProperties) {
        this.fileStorageProperties = Paths.get(fileStorageProperties.getUploadDir())
                .toAbsolutePath().normalize();
        try {
            Files.createDirectories(this.fileStorageProperties);
        }catch (IOException exception) {
            throw new RuntimeException("Cannot initialize storage", exception);
        }
    }
    private boolean isImageFile(MultipartFile file) {
        //bắt đầu từ những đuôi png jpg jpeg bmp
        String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
        return Arrays.asList(new String[] {"png","jpg","jpeg", "bmp"})
                .contains(fileExtension.trim().toLowerCase());
    }
    @Override
    public String storeFile(MultipartFile file) {
        try {
            System.out.println("haha");
            if (file.isEmpty()) {
                throw new RuntimeException("Failed to store empty file.");
            }
            //kiểm tra xem có phải file ảnh
            if(!isImageFile(file)) {
                throw new RuntimeException("You can only upload image file");
            }
            //file phải nhỏ <= 5Mb
            float fileSizeInMegabytes = file.getSize() / 1_000_000.0f;
            if(fileSizeInMegabytes > 5.0f) {
                throw new RuntimeException("File must be <= 5Mb");
            }
            //đổi tên file
            String fileExtension = FilenameUtils.getExtension(file.getOriginalFilename());
            String generatedFileName = UUID.randomUUID().toString().replace("-", "");
            generatedFileName = generatedFileName+"."+fileExtension;
            Path destinationFilePath = this.fileStorageProperties.resolve(
                            Paths.get(generatedFileName))
                    .normalize().toAbsolutePath();
            if (!destinationFilePath.getParent().equals(this.fileStorageProperties.toAbsolutePath())) {
                throw new RuntimeException(
                        "khong the luu file ơ ngoai thu muc hien tai");
            }
            try (InputStream inputStream = file.getInputStream()) {
                Files.copy(inputStream, destinationFilePath, StandardCopyOption.REPLACE_EXISTING);
            }
            return generatedFileName;
        }
        catch (IOException exception) {
            throw new RuntimeException("lưu file thất bại.", exception);
        }
    }

//    @Override
//    public Stream<Path> loadAll() {
//        try {
//            //list all files in storageFolder
//            //How to fix this ?
//            return Files.walk(this.fileStorageProperties, 1)
//                    .filter(path -> !path.equals(this.fileStorageProperties) && !path.toString().contains("._"))
//                    .map(this.fileStorageProperties::relativize);
//        }
//        catch (IOException e) {
//            throw new RuntimeException("Failed to load stored files", e);
//        }
//
//    }

    @Override
    public byte[] readFileContent(String fileName) {
        try {
            Path file = fileStorageProperties.resolve(fileName);
            Resource resource = new UrlResource(file.toUri());
            if (resource.exists() || resource.isReadable()) {
                byte[] bytes = StreamUtils.copyToByteArray(resource.getInputStream());
                return bytes;
            }
            else {
                throw new RuntimeException(
                        "khong the doc file: " + fileName);
            }
        }
        catch (IOException exception) {
            throw new RuntimeException("không the doc file: " + fileName, exception);
        }
    }
}
