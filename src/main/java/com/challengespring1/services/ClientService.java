package com.challengespring1.services;

import com.challengespring1.dto.Client.ClientCreateDto;
import com.challengespring1.dto.Client.ClientUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.repository.ClientRepository;
import com.challengespring1.repository.HouseRepository;
import com.challengespring1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

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

    public List<Client> getAllClients() {
        return clientRepository.findAll();
    }

    public Client createClient(ClientCreateDto clientCreateDto) {
        Client client = new Client(clientCreateDto);
        clientRepository.save(client);
        return client;
    }

    public Client findClientById(Long id) {
        return clientRepository.findById(id).orElseThrow();
    }

    public Client updateClient(Long id, ClientUpdateDto clientUpdateDto) {
        Optional<Client> optionalClient = clientRepository.findById(id);
        if (optionalClient.isEmpty()) throw new RuntimeException("Usuário não encontrado");
        Client client = optionalClient.get();
        client.setName(validateUpdateFields(clientUpdateDto.name()) ? clientUpdateDto.name() : client.getName());
        client.setAge(validateUpdateFields(clientUpdateDto.age()) ? clientUpdateDto.age() : client.getAge());
        client.setDependents(validateUpdateFields(clientUpdateDto.dependents()) ? clientUpdateDto.dependents() : client.getDependents());
        client.setIncome(validateUpdateFields(clientUpdateDto.income()) ? clientUpdateDto.income() : client.getIncome());
        client.setMaritalStatus(validateUpdateFields(clientUpdateDto.maritalStatus()) ? clientUpdateDto.maritalStatus() : client.getMaritalStatus());
        return clientRepository.save(client);

    }

    public Client deleteClient(Long id) {
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
        return client;
    }


    private boolean validateUpdateFields(Object object) {
        if (object == null) return false;
        else if ((object instanceof String))
            return !((String) object).isEmpty();
        return true;
    }

}
