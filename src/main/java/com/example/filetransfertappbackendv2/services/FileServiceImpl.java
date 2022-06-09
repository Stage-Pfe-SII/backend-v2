package com.example.filetransfertappbackendv2.services;

import com.example.filetransfertappbackendv2.entities.File;
import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.repositories.FileRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class FileServiceImpl implements FileService{

    private final FileRepository fileRepository;

    @Override
    public File save(File file) {
        return fileRepository.save(file);
    }

    @Override
    public List<File> findByTransfert(Transfert transfert) {
        return fileRepository.findByTransfert(transfert);
    }

    @Override
    public List<File> findByPath(String path) {
        return fileRepository.findByPath(path);
    }
}
