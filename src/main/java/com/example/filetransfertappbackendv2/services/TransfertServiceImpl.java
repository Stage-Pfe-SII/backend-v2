package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.repositories.TransfertRepository;
import lombok.RequiredArgsConstructor;

import javax.transaction.Transactional;
import java.util.List;

@RequiredArgsConstructor
public class TransfertServiceImpl implements TransfertSercvice{

    private final TransfertRepository transfertRepository;

    @Override
    public Transfert save(Transfert transfert) {
        return transfertRepository.save(transfert);
    }

    @Override
    public List<Transfert> getAllTransferts() {
        return transfertRepository.findAll();
    }

    @Override
    public Transfert findByPath(String path) {
        return transfertRepository.findByPath(path);
    }

    @Override
    @Transactional
    public Transfert addFilesToTransfert(Transfert transfert, List<File> files) {
        files.forEach(file -> {
            file.setPath(transfert.getPath());
            file.setTransfert(transfert);
            transfert.getFiles().add(file);
        });
        return transfert;
    }

    @Override
    @Transactional
    public Transfert incrementDownloadTime(Transfert transfert) {
        transfert.setDownloadTimes(transfert.getDownloadTimes() + 1);
        return transfert;
    }
}
