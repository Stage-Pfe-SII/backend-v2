package com.example.filetransfertappbackendv2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Set;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
public class User {

    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    @Column(unique = true)
    private String username;
    private String password;

    private String email;
    @OneToMany(mappedBy="sender")
    @JsonIgnoreProperties({"sender","receiver"})
    Collection<Transfert> sendedTransferts = new ArrayList<>();

    @OneToMany(mappedBy="receiver")
    @JsonIgnoreProperties({"sender","receiver"})
    Collection<Transfert> receivedTranferts = new ArrayList<>();

}
