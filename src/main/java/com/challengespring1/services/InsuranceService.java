package com.challengespring1.services;

import com.challengespring1.dto.Insurance.InsuranceCreateDto;
import com.challengespring1.dto.Insurance.InsuranceResponseDto;
import com.challengespring1.entities.Client;
import com.challengespring1.entities.House;
import com.challengespring1.entities.Insurance;
import com.challengespring1.entities.Vehicle;
import com.challengespring1.enums.Analyisis;
import com.challengespring1.enums.MaritalStatus;
import com.challengespring1.enums.OwnershipStatus;
import com.challengespring1.repository.ClientRepository;
import com.challengespring1.repository.HouseRepository;
import com.challengespring1.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfigureOrder;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpClientErrorException;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@Service
public class InsuranceService {

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private HouseRepository houseRepository;

    @Autowired
    private VehicleRepository vehicleRepository;

    public InsuranceResponseDto lifeInsurance(InsuranceCreateDto insuranceCreateDto) {
        Client client = getClient(insuranceCreateDto);
        Insurance insurance = new Insurance();
        isOld(client);
        int riskQuestionsRate = getBaseRiskQuestionsRate(insuranceCreateDto.risk_questions());
        riskQuestionsRate -= ageDiscount(client);
        riskQuestionsRate -= incomeDiscount(client);
        riskQuestionsRate += dependentsDiscount(client);
        riskQuestionsRate += marriedDiscount(client);
        return new InsuranceResponseDto(setInsuranceInfo(insurance, riskQuestionsRate, client, "life"));
    }

    public InsuranceResponseDto disabilityInsurance(InsuranceCreateDto insuranceCreateDto) {
        Client client = getClient(insuranceCreateDto);
        Insurance insurance = new Insurance();
        isOld(client);
        if (client.getIncome().isNaN() || client.getIncome() == 0)
            throw new RuntimeException("Client is not fit for this insurance");
        int riskQuestionsRate = getBaseRiskQuestionsRate(insuranceCreateDto.risk_questions());
        riskQuestionsRate = generalDiscounts(riskQuestionsRate, client);
        riskQuestionsRate += housesDiscount(client);
        riskQuestionsRate += dependentsDiscount(client);
        riskQuestionsRate -= marriedDiscount(client);
        return new InsuranceResponseDto(setInsuranceInfo(insurance, riskQuestionsRate, client, "disability"));
    }

    public InsuranceResponseDto homeInsurance(InsuranceCreateDto insuranceCreateDto) {
        Client client = getClient(insuranceCreateDto);
        Insurance insurance = new Insurance();
        Optional<House> optionalHouse = houseRepository.findById(insuranceCreateDto.house_id());
        if (optionalHouse.isEmpty()) throw new RuntimeException("House not found");
        isOld(client);
        if (client.getHouses().isEmpty())
            throw new RuntimeException("Client is not fit for this insurance");
        int riskQuestionsRate = getBaseRiskQuestionsRate(insuranceCreateDto.risk_questions());
        riskQuestionsRate = generalDiscounts(riskQuestionsRate, client);
        riskQuestionsRate += houseDiscount(optionalHouse.get());
        return new InsuranceResponseDto(setInsuranceInfo(insurance, riskQuestionsRate, client, "home"));
    }

    public InsuranceResponseDto vehicleInsurance(InsuranceCreateDto insuranceCreateDto) {
        Client client = getClient(insuranceCreateDto);
        Insurance insurance = new Insurance();
        Optional<Vehicle> optionalVehicle =vehicleRepository.findById(insuranceCreateDto.vehicle_id());
        if (optionalVehicle.isEmpty()) throw new RuntimeException("House not found");
        isOld(client);
        if (client.getVehicles().isEmpty())
            throw new RuntimeException("Client is not fit for this insurance");
        int riskQuestionsRate = getBaseRiskQuestionsRate(insuranceCreateDto.risk_questions());
        riskQuestionsRate = generalDiscounts(riskQuestionsRate, client);
        riskQuestionsRate += vehicleDiscount(optionalVehicle.get());
        return new InsuranceResponseDto(setInsuranceInfo(insurance, riskQuestionsRate, client, "home"));
    }



    private Integer generalDiscounts(Integer riskQuestionsRate, Client client) {
        riskQuestionsRate -= ageDiscount(client);
        riskQuestionsRate -= incomeDiscount(client);
        return riskQuestionsRate;
    }


    private Insurance setInsuranceInfo(Insurance insurance, int riskQuestionsRate, Client client, String type) {
        insurance.setRisk(riskQuestionsRate);
        insurance.setAnalysis(getAnalysis(riskQuestionsRate));
        insurance.setType(type);
        insurance.setClient(client);
        return insurance;
    }


    private Integer getBaseRiskQuestionsRate(List<Boolean> riskQuestions) {
        int sum = 0;
        for (Boolean question : riskQuestions) {
            sum += question ? 1 : 0;
        }
        return sum;
    }


    private void isOld(Client client) {
        if (client.getAge() > 60) throw new RuntimeException("Client is not fit for this insurance");
    }

    private Integer ageDiscount(Client client) {
        if (client.getAge() < 30) return 2;
        else if (client.getAge() > 30 && client.getAge() < 40) return 1;
        return 0;
    }

    private Integer incomeDiscount(Client client) {
        if (client.getIncome() > 200000) return 1;
        return 0;
    }

    private Integer houseDiscount(House house) {
        if (house.getOwnershipStatus() == OwnershipStatus.mortgaged) return 1;
        return 0;
    }

    private Integer housesDiscount(Client client) {
        int sum = 0;
        List<House> houses = client.getHouses();
        for (House house : houses) {
            if (house.getOwnershipStatus() == OwnershipStatus.mortgaged) {
                sum += 1;
            }
        }
        return sum;
    }

    private Integer dependentsDiscount(Client client) {
        if (client.getDependents() != 0) return 1;
        return 0;
    }

    private Integer marriedDiscount(Client client) {
        if (client.getMaritalStatus() == MaritalStatus.married) return 1;
        return 0;
    }

    private Integer vehicleDiscount(Vehicle vehicle) {
        if (LocalDate.now().getYear() - vehicle.getYear() <= 5) return 1;
        return 0;
    }

    private Analyisis getAnalysis(Integer riskRate) {
        if (riskRate <= 0) return Analyisis.economic;
        else if (riskRate == 1 || riskRate == 2) return Analyisis.regular;
        else return Analyisis.responsible;
    }

    private Client getClient(InsuranceCreateDto insuranceCreateDto) {
        Optional<Client> optionalClient = clientRepository.findById(insuranceCreateDto.client_id());
        if (optionalClient.isEmpty()) throw new RuntimeException("Client not found");
        return optionalClient.get();
    }

}
