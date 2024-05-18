package com.challengespring1.services;

import com.challengespring1.dto.Vehicle.VehicleCreateDto;
import com.challengespring1.dto.Vehicle.VehicleResponseDto;
import com.challengespring1.dto.Vehicle.VehicleUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.repository.ClientRepository;
import com.challengespring1.repository.VehicleRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ClientRepository clientRepository;

    public VehicleResponseDto createVehicle(VehicleCreateDto vehicleCreateDto){
        Vehicle vehicle=new Vehicle(vehicleCreateDto);
        vehicleRepository.save(vehicle);
        return new VehicleResponseDto(vehicle);
    }

    public VehicleResponseDto createAssociation(Long id,VehicleUpdateDto vehicleUpdateDto){
        Optional<Vehicle> optionalVehicle=vehicleRepository.findById(id);
        Optional<Client> optionalClient=clientRepository.findById(vehicleUpdateDto.client_id());
        if(optionalVehicle.isEmpty()) throw new RuntimeException("Vehicle not found");
        if(optionalClient.isEmpty()) throw new RuntimeException("Client not found");
        Vehicle vehicle=optionalVehicle.get();
        Client client=optionalClient.get();
        List<Vehicle> vehicles=client.getVehicles();
        vehicles.add(vehicle);
        client.setVehicles(vehicles);
        clientRepository.save(client);
        return new VehicleResponseDto(vehicle);
    }

    @Transactional
    public VehicleResponseDto deleteVehicle(Long id){
        Optional<Vehicle> optionalVehicle=vehicleRepository.findById(id);
        if(optionalVehicle.isEmpty()) throw new RuntimeException("Vehicle not found");
        Vehicle vehicle=optionalVehicle.get();
        List<Client> clients = vehicle.getClients();
        for (Client client : clients) {
            client.getVehicles().remove(vehicle);
        }
        clientRepository.saveAll(clients);
        vehicleRepository.deleteById(id);
        return new VehicleResponseDto(vehicle);
    }

}
