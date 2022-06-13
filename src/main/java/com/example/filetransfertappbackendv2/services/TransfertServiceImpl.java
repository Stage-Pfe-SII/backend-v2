package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.repositories.TransfertRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.Duration;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@RequiredArgsConstructor
@Service
public class TransfertServiceImpl implements TransfertService {

    private final TransfertRepository transfertRepository;

    @Override
    public Transfert save(Transfert transfert) {
        String path = UUID.randomUUID().toString();
        Duration duration = Duration.ofDays(7);
        Date expirationDate = new Date(System.currentTimeMillis()+duration.toMillis());
        transfert.setPath(path);
        transfert.setExpirationDate(expirationDate);
        transfert.setDownloadTimes(0);
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

    @Override
    public Transfert getTransfertById(Long id) {
        return transfertRepository.findById(id).get();
    }
}
