package com.example.filetransfertappbackendv2.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

import static javax.persistence.GenerationType.AUTO;

@Entity
@Getter
@Setter
public class File {
    @Id
    @GeneratedValue(strategy = AUTO)
    private Long id;
    private String name;
    private String path;
    private long size;

    @Lob
    @JsonIgnore
    private byte[] content;

    @ManyToOne
    @JoinColumn(name = "transfert_id")
    private Transfert transfert;

}
