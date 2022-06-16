package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;

import java.util.List;

public interface FileService {
    File save(File file);
    List<File> findByTransfert(Transfert transfert);
    File findByPath(String path);
}
