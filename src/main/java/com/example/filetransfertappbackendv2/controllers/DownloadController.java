package com.example.filetransfertappbackendv2.controllers;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.excpetions.TransfertNotFoundException;
import com.example.filetransfertappbackendv2.services.EncryptionService;
import com.example.filetransfertappbackendv2.services.FileService;
import com.example.filetransfertappbackendv2.services.TransfertService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpHeaders;
import org.springframework.util.StreamUtils;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

@CrossOrigin("*")
@RestController
@RequiredArgsConstructor
public class DownloadController {

    private final TransfertService transfertService;
    private final FileService fileService;
    private final EncryptionService encryptionService;

    @GetMapping("/download/{path}")
    public void downloadZipFile(@PathVariable String path, HttpServletResponse response)throws Exception{
        Transfert transfert = transfertService.findByPath(path);
        if(transfert == null){
            throw new TransfertNotFoundException("the transfert with the path "+path+" does not exist");
        }

        List<File> files = (List<File>) transfert.getFiles();
        files.forEach(file -> {
            try {
                encryptionService.decrypt(file);
            } catch (Exception e) {
                e.printStackTrace();
            }
        });
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        response.setContentType("application/zip");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"transfert.zip\"");
        transfertService.incrementDownloadTime(transfert);
        files.forEach(file -> {
            ZipEntry entry = new ZipEntry(file.getName());
            entry.setSize(file.getSize());
            try {
                zipOutputStream.putNextEntry(entry);
                StreamUtils.copy(file.getContent(), zipOutputStream);
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        });
        zipOutputStream.close();
    }


    @GetMapping("/download/file/{path}")
    public void downloadFile(@PathVariable String path, HttpServletResponse response)throws Exception{
        File file = fileService.findByPath(path);

        Transfert transfert = file.getTransfert();
        try {
            encryptionService.decrypt(file);
        } catch (Exception e) {
            e.printStackTrace();
        }
        ZipOutputStream zipOutputStream = new ZipOutputStream(response.getOutputStream());
        response.setContentType("application/zip");
        response.setStatus(HttpServletResponse.SC_OK);
        response.setHeader(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"file.zip\"");
        transfertService.incrementDownloadTime(transfert);

        ZipEntry entry = new ZipEntry(file.getName());
            entry.setSize(file.getSize());
            try {
                zipOutputStream.putNextEntry(entry);
                StreamUtils.copy(file.getContent(), zipOutputStream);
                zipOutputStream.closeEntry();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }

        zipOutputStream.close();
    }
}
