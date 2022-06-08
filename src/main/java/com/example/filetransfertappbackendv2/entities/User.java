package com.example.filetransfertappbackendv2.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Set;

@Entity
@Data
public class User {

    @Id
    private Long id;

    private String username;
    private String password;
    private String email;

    @OneToMany(mappedBy="sender")
    Set<Transfert> sendedTransferts;

    @OneToMany(mappedBy="receiver")
    Set<Transfert> receivedTranferts;

}
