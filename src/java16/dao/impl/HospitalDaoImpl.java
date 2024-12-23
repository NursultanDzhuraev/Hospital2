package java16.dao.impl;

import java16.dao.HospitalDao;
import java16.db.DataBase;
import java16.models.Hospital;
import java16.models.Patient;

import java.util.List;
import java.util.Map;

public class HospitalDaoImpl implements HospitalDao {
    @Override
    public String addHospital(Hospital hospital) {
        for (Hospital hospital1 : DataBase.hospitals) {
            if (hospital.getHospitalName().equalsIgnoreCase(hospital1.getHospitalName())){
                throw new RuntimeException("оорукана аты окшош болбосун: ");
            }
        }
        DataBase.hospitals.add(hospital);
        return "Successful!";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital;
            }
        }
        System.out.println(id + "мындай id де hospital жок!");
        return null;
    }

    @Override
    public List<Hospital> getAllHospital() {
        return DataBase.hospitals;
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital.getPatients();
            }
        }
        System.out.println(id + "hospital дын patient жок!");
        return null;
    }

    @Override
    public String deleteHospitalById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)) {
                DataBase.hospitals.remove(hospital);
                System.out.println(hospital);
                return "deleted!";
            }
        }
        return "not fount!";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getAddress().equalsIgnoreCase(address)) {
                System.out.println(hospital);
            }
        }
        System.out.println(address + "мындай адресте оорукана жок!");
        return null;
    }

}

