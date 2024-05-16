package com.challengespring1.services;

import com.challengespring1.dto.Client.ClientCreateDto;
import com.challengespring1.dto.Client.ClientResponseDto;
import com.challengespring1.dto.Client.ClientUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.repository.ClientRepository;
import com.challengespring1.repository.HouseRepository;
import com.challengespring1.repository.VehicleRepository;
import com.challengespring1.utils.ValidateFields;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Service
public class ClientService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private VehicleRepository vehicleRepository;


    public List<ClientResponseDto> getAllClients() {
        List<Client> clients= clientRepository.findAll();
        List<ClientResponseDto> clientResponseDtos=new ArrayList<>();
        for(Client client:clients){
            clientResponseDtos.add(new ClientResponseDto(client));
        }
        return clientResponseDtos;
    }

    public ClientResponseDto createClient(ClientCreateDto clientCreateDto) {
        Client client = new Client(clientCreateDto);
        clientRepository.save(client);
        return new ClientResponseDto(client);
    }

    public ClientResponseDto findClientById(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new RuntimeException("Usuário não encontrado");
        return new ClientResponseDto(optionalClient.get());
    }

    public ClientResponseDto updateClient(Long id, ClientUpdateDto clientUpdateDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new RuntimeException("Usuário não encontrado");
        Client client = optionalClient.get();
        client.setName(ValidateFields.validateUpdateFields(clientUpdateDto.name()) ? clientUpdateDto.name() : client.getName());
        client.setAge(ValidateFields.validateUpdateFields(clientUpdateDto.age()) ? clientUpdateDto.age() : client.getAge());
        client.setDependents(ValidateFields.validateUpdateFields(clientUpdateDto.dependents()) ? clientUpdateDto.dependents() : client.getDependents());
        client.setIncome(ValidateFields.validateUpdateFields(clientUpdateDto.income()) ? clientUpdateDto.income() : client.getIncome());
        client.setMaritalStatus(ValidateFields.validateUpdateFields(clientUpdateDto.marital_status()) ? clientUpdateDto.marital_status() : client.getMaritalStatus());
        client.setUpdatedAt(new Date());
        clientRepository.save(client);
        return new ClientResponseDto(client);
    }
    public ClientResponseDto deleteClient(Long id) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new RuntimeException("Usuário não encontrado");
        Client client = optionalClient.get();
        for (House house : client.getHouses()) {
            houseRepository.deleteById(house.getId());
        }
        for (Vehicle vehicle : client.getVehicles()) {
            vehicleRepository.deleteById(vehicle.getId());
        }
        clientRepository.deleteById(id);
        return new ClientResponseDto(client);
    }
}