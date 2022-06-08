package com.example.filetransfertappbackendv2.entities;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Data;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Data
public class Transfert {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;

    private String title;
    private String message;
    private String path;
    private int downloadTimes;
    private Date expirationDate;


    @ManyToOne
    private User sender;

    @ManyToOne
    private User receiver;


    @OneToMany(mappedBy = "transfert", cascade = CascadeType.ALL)
    @JsonIgnoreProperties("transfert")
    private Collection<File> files = new ArrayList<>();
}
