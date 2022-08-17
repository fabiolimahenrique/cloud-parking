package com.fabiolima.parking.service;

import com.fabiolima.parking.exception.ParkingNotFoundException;
import com.fabiolima.parking.model.Parking;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {

    private static Map<String, Parking> parkingMap = new HashMap();

    static {
        var id = getUUid();
        var id1 = getUUid();
        Parking parking = new Parking(id, "HHH-9987", "CE", "PASSAT", "Azul");
        Parking parking1 = new Parking(id1, "YYY-1111", "SP", "FUSCA", "Preta");
        parkingMap.put(id, parking);
        parkingMap.put(id1, parking1);

    }


    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }




    private static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }


    public Parking findById(String id) {
        Parking parking = parkingMap.get(id);

        if (parking == null){
           throw new ParkingNotFoundException(id);
        }

        return parking;
    }

    public Parking create(Parking parkingCreate) {
        String uuid = getUUid();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        parkingMap.put(uuid,parkingCreate);
        return parkingCreate;


    }

    public void delete(String id) {
        Parking parking = findById(id);
        parkingMap.remove(id);
    }

    public Parking updade(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        parkingMap.replace(id, parking);
        return parking;
    }
}
