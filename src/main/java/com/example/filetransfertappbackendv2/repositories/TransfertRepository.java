package com.example.filetransfertappbackendv2.repositories;

import com.example.filetransfertappbackendv2.entities.Transfert;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TransfertRepository extends JpaRepository<Transfert, Long> {
    Transfert findByPath(String path);
}
