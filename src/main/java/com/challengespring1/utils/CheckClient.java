package com.challengespring1.utils;

import com.challengespring1.entities.Client;
import com.challengespring1.infra.exceptions.ClientNotFoundException;

import java.util.Optional;

public class CheckClient {
    public static void clientExists(Optional<Client> client) {
        if (client.isEmpty()) throw new ClientNotFoundException();
    }
}
