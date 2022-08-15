package com.fabiolima.parking.service;

import com.fabiolima.parking.model.Parking;
import org.springframework.stereotype.Service;

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
        Parking parking = new Parking(id, "HHH-9987", "CE", "PASSAT", "Azul");
        parkingMap.put(id, parking);

    }


    public List<Parking> findAll() {
        return parkingMap.values().stream().collect(Collectors.toList());
    }

    private static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }

}
