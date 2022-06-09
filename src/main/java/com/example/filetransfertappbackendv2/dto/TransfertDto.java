package com.example.filetransfertappbackendv2.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class TransfertDto {
        private String sender;
        private String receiver;
        private String title;
        private String message;
}
