package com.example.filetransfertappbackendv2.mapper;

import com.example.filetransfertappbackendv2.dto.TransfertDto;
import com.example.filetransfertappbackendv2.entities.Transfert;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface TransfertMapper {
   // TransfertDto transfertToTransfertDto(Transfert transfert);
    //Transfert transfertDtoToTransfert(TransfertDto transfertDto);
}
