package com.fabiolima.parking.service;

import com.fabiolima.parking.exception.ParkingNotFoundException;
import com.fabiolima.parking.model.Parking;
import com.fabiolima.parking.repository.ParkingRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class ParkingService {
    private final ParkingRepository parkingRepository;

    public ParkingService(ParkingRepository parkingRepository) {
        this.parkingRepository = parkingRepository;
    }

    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public List<Parking> findAll() {
        return parkingRepository.findAll();
    }


    @Transactional(readOnly = true, propagation = Propagation.SUPPORTS)
    public Parking findById(String id) {
        Parking parking = parkingRepository.findById(id).orElseThrow(() ->
                new ParkingNotFoundException(id)
        );
        return parking;
    }

    @Transactional
    public Parking create(Parking parkingCreate) {
        String uuid = getUUid();
        parkingCreate.setId(uuid);
        parkingCreate.setEntryDate(LocalDateTime.now());
        return parkingRepository.save(parkingCreate);
    }

    @Transactional
    public void delete(String id) {
        parkingRepository.deleteById(id);
    }

    @Transactional
    public Parking updade(String id, Parking parkingCreate) {
        Parking parking = findById(id);
        parking.setColor(parkingCreate.getColor());
        return parkingRepository.save(parking);
    }

    @Transactional
    public Parking checkOut(String id){
        Parking parking =findById(id);
        parking.setExitDate(LocalDateTime.now());
        parking.setBill( ParkingCheckout.getBill(parking) );
        return parkingRepository.save(parking);
    }

    private static String getUUid() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
