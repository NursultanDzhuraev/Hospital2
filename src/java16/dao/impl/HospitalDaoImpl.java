package java16.dao.impl;

import java16.dao.HospitalDao;
import java16.db.DataBase;
import java16.models.Hospital;
import java16.models.Patient;

import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        DataBase.hospitals.add(hospital);
        return "Successful!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return DataBase.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .orElseGet(() -> {
                    System.out.println(id + " мындай id де hospital жок!");
                    return null;
                });
    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return DataBase.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                .findFirst()
                .map(Hospital::getPatients)
                .orElseGet(() -> {
                    System.out.println(id + " мындай id де hospital жок!");
                    return null;
                });
    }

    @Override
    public String deleteHospitalById(Long id) {
        boolean b = DataBase.hospitals.removeIf(h -> h.getId().equals(id));
        if (b) {
            return "deleted!";
        }
        return "not fount!";

    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        Map<String, Hospital> collect = DataBase.hospitals.stream()
                .filter(h -> h.getAddress().equalsIgnoreCase(address))
                .collect(Collectors.toMap(Hospital::getHospitalName, h -> h));
        if (collect.isEmpty()) {
            System.out.println(address + "мындай адресте оорукана жок!");
            return null;
        }
        return collect;

    }

}