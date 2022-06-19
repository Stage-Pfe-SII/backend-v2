package com.example.filetransfertappbackendv2.services;


import com.example.filetransfertappbackendv2.entities.File;

public interface EncryptionService {
    File encrypt(File file) throws Exception;
    File decrypt(File file) throws Exception;
}
