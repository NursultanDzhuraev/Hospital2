package java16.service.impl;

import java16.dao.impl.PatientDaoImpl;
import java16.models.Patient;
import java16.service.GenericService;
import java16.service.PatientService;

import java.util.List;
import java.util.Map;

public class PatientServiceImpl implements PatientService, GenericService<Patient> {
    private final PatientDaoImpl patientDao = new PatientDaoImpl();

    @Override
    public String add(Long hospitalId, Patient patient) {
        patientDao.add(hospitalId, patient);
        return "Successful!";
    }

    @Override
    public void removeById(Long id) {
        patientDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Patient patient) {
        patientDao.updateById(id,patient);
        return "Updated!";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        patientDao.addPatientsToHospital(id,patients);
        return "Saved!";
    }

    @Override
    public Patient getPatientById(Long id) {
        return patientDao.getPatientById(id);
    }

    @Override
    public Map<Integer,List<Patient> > getPatientByAge() {
        return patientDao.getPatientByAge();
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        return patientDao.sortPatientsByAge(ascOrDesc);
    }
}
