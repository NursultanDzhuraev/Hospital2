package java16.service.impl;

import java16.dao.impl.HospitalDaoImpl;
import java16.models.Hospital;
import java16.models.Patient;
import java16.service.HospitalService;

import java.util.List;
import java.util.Map;

public class HospitalServiceImpl implements HospitalService {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();
    @Override
    public String addHospital(Hospital hospital) {
        try {
            hospitalDao.addHospital(hospital);
        } catch (RuntimeException e) {
           throw new RuntimeException(e.getMessage());
        }
        return "Successful";
    }

    @Override
    public Hospital findHospitalById(Long id) {
        return hospitalDao.findHospitalById(id);
    }

    @Override
    public List<Hospital> getAllHospital() {
        return hospitalDao.getAllHospital();
    }

    @Override
    public List<Patient> getAllPatientFromHospital(Long id) {
        return hospitalDao.getAllPatientFromHospital(id);
    }

    @Override
    public String deleteHospitalById(Long id) {
        hospitalDao.deleteHospitalById(id);
        return "Deleted!";
    }

    @Override
    public Map<String, Hospital> getAllHospitalByAddress(String address) {
        return hospitalDao.getAllHospitalByAddress(address);
    }
}
