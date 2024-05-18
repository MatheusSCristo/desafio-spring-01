package com.challengespring1.utils;

import com.challengespring1.entities.House;
import com.challengespring1.infra.exceptions.HouseNotFoundException;

import java.util.Optional;

public class CheckHouse {

    public static void HouseExists(Optional<House> optionalHouse){
        if (optionalHouse.isEmpty()) throw new HouseNotFoundException();
    }
}
