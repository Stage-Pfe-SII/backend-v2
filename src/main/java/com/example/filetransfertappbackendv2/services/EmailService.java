package com.example.filetransfertappbackendv2.services;


import com.example.filetransfertappbackendv2.entities.Transfert;

public interface EmailService {
    void sendToSender(Transfert transfert);
    void sendToReceiver(Transfert transfert);
}
