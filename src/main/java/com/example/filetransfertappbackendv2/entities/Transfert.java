package com.example.filetransfertappbackendv2.entities;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
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

}
