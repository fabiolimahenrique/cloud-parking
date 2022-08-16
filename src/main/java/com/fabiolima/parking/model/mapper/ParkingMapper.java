package com.fabiolima.parking.model.mapper;

import com.fabiolima.parking.model.Parking;
import com.fabiolima.parking.model.dto.ParkingCreateDTO;
import com.fabiolima.parking.model.dto.ParkingDTO;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.stream.Collectors;

@Component
public class ParkingMapper {

    private static final ModelMapper MODEL_MAPPER = new ModelMapper();

    public ParkingDTO toParkingDTO(Parking parking) {
        return MODEL_MAPPER.map(parking, ParkingDTO.class);
    }

    public List<ParkingDTO> toParkingDTOList(List<Parking> parkingList) {
        return parkingList.stream().map(this::toParkingDTO).collect(Collectors.toList());
    }


    public Parking toParking(ParkingDTO parkingDTO) {
       return MODEL_MAPPER.map(parkingDTO, Parking.class);
    }

    public Parking toParkingCreateDTO(ParkingCreateDTO parkingCreateDTO) {
        return MODEL_MAPPER.map(parkingCreateDTO, Parking.class);
    }
}
