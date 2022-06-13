package com.example.filetransfertappbackendv2.controllers;

import com.example.filetransfertappbackendv2.entities.Transfert;
import com.example.filetransfertappbackendv2.services.TransfertSercvice;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequiredArgsConstructor
@CrossOrigin("*")
public class TransfertController {

    private final TransfertSercvice transfertSercvice;

    @GetMapping("/transferts")
    List<Transfert> getAllTransferts(){
        return transfertSercvice.getAllTransferts();
    }

    @GetMapping("/transferts/{id}")
    Transfert getTransfert(@PathVariable Long id){
        return transfertSercvice.getTransfertById(id);
    }

}
