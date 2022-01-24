package com.huce.ltudm.doan_tra_cu_dulich;

import com.huce.ltudm.doan_tra_cu_dulich.property.FileStorageProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
@EnableConfigurationProperties({
        FileStorageProperties.class
})

public class DoanTraCuDulichApplication {

    public static void main(String[] args) {
        SpringApplication.run(DoanTraCuDulichApplication.class, args);
    }

}
