package com.huce.ltudm.doan_tra_cu_dulich.exception;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class ErrorResponse {
    private HttpStatus status;
    private String message;
}