package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;

import java.util.List;

public interface TransfertSercvice {
    Transfert save(Transfert transfert);
    List<Transfert> getAllTransferts();
    Transfert findByPath(String path);
    Transfert addFilesToTransfert(Transfert transfert, List<File> files);
    Transfert incrementDownloadTime(Transfert transfert);
    Transfert getTransfertById(Long id);
}
