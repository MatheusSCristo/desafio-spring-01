package com.challengespring1.controller;

import com.challengespring1.dto.Client.ClientCreateDto;
import com.challengespring1.dto.Client.ClientUpdateDto;
import com.challengespring1.entities.Client;
import com.challengespring1.services.ClientService;
import jakarta.persistence.GeneratedValue;
import jakarta.validation.Valid;
import lombok.Getter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Set;

@RestController
@RequestMapping("clients")
public class ClientController {

    @Autowired
    private ClientService clientService;


    @GetMapping
    public ResponseEntity<List<Client>> getAllClients() {
        return ResponseEntity.ok().body(clientService.getAllClients());
    }

    @GetMapping("{id}")
    public ResponseEntity<Client> getClientById(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.findClientById(id));
    }


    @PostMapping
    public ResponseEntity<Client> createClient(@RequestBody @Valid ClientCreateDto clientCreateDto) {
        return ResponseEntity.ok().body(clientService.createClient(clientCreateDto));
    }

    @PutMapping("{id}")
    public ResponseEntity<Client> updateClient(@PathVariable Long id,@RequestBody @Valid ClientUpdateDto updateClientDto) {
        return ResponseEntity.ok().body(clientService.updateClient(id,updateClientDto));
    }

    @DeleteMapping("{id}")
    public ResponseEntity<Client> deleteClient(@PathVariable Long id) {
        return ResponseEntity.ok().body(clientService.deleteClient(id));
    }
}
