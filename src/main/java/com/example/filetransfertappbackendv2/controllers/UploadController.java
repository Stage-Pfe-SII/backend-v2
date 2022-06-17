package com.example.filetransfertappbackendv2.controllers;


import com.example.filetransfertappbackendv2.dto.TransfertDto;
import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.entities.User;
import com.example.filetransfertappbackendv2.mapper.TransfertMapper;
import com.example.filetransfertappbackendv2.services.*;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

import static com.example.filetransfertappbackendv2.mapper.MultipartFileToFileMapper.multipartFileListToFileList;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class UploadController {

    private final TransfertService transfertService;
    private final FileService fileService;
    private final UserService userService;
    private final TransfertMapper transfertMapper;
    private final EmailService emailService;


    @PostMapping("/upload")
    public void upload(
            @RequestParam("files") List<MultipartFile> multipartFiles,
            @RequestParam("transfertJSON") String transfertJSON
    ) {
        try {

            List<File> files = multipartFileListToFileList(multipartFiles);
            TransfertDto trans = new ObjectMapper().readValue(transfertJSON, TransfertDto.class);
            User sender = userService.findUserbyEmail(trans.getSender());
            User receiver = userService.findUserbyEmail(trans.getReceiver());

            Transfert transfert = new Transfert();
            transfert.setSender(sender);
            transfert.setReceiver(receiver);
            transfertService.addFilesToTransfert(transfert,files);
            transfert.setMessage(trans.getMessage());
            transfert.setTitle(trans.getTitle());
            transfertService.save(transfert);

            emailService.sendToSender(transfert);
            emailService.sendToReceiver(transfert);

        } catch (JsonProcessingException e) {
            e.printStackTrace();
        }
    }

}
