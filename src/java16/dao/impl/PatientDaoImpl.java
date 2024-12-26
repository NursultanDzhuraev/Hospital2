package java16.dao.impl;

import java16.dao.GenericDao;
import java16.dao.PatientDao;
import java16.db.DataBase;
import java16.models.Hospital;
import java16.models.Patient;

import java.util.*;
import java.util.stream.Stream;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    private final HospitalDaoImpl hospitalDao = new HospitalDaoImpl();
    @Override
    public String add(Long hospitalId, Patient patient) {

        Hospital hospitalById = hospitalDao.findHospitalById(hospitalId);
        if (hospitalById.getPatients()==null){
            hospitalById.setPatients(new ArrayList<>());
            hospitalById.getPatients().add(patient);
            return "Successful";
        }else {hospitalById.getPatients().add(patient);
            return "Successful";
        }
    }

    @Override
    public void removeById(Long id) {
        DataBase.hospitals.forEach(h -> h.getPatients().removeIf(p -> p.getId().equals(id)));

    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : DataBase.hospitals) {
            hospital.getPatients().stream()
                    .filter(p->p.getId().equals(id))
                    .forEach(p->{
                        p.setGender(patient.getGender());
                        p.setLastName(patient.getLastName());
                        p.setFirstName(patient.getFirstName());
                        p.setAge(patient.getAge());
                    });
            return "Successful!";
        }
        return "not fount!";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        boolean b = hospitalDao.findHospitalById(id).getPatients().addAll(patients);
       if (b){
           return "add all patients!";
       }
        return "not fount!";
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
          return   hospital.getPatients().stream()
                    .filter(p->p.getId().equals(id))
                    .findFirst()
                    .orElse(null);
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientMap = new HashMap<>();
        List<Patient> list = DataBase.hospitals.stream()
                .flatMap(h -> h.getPatients().stream())
                .toList();
        DataBase.hospitals.stream()
                .flatMap(h->h.getPatients().stream())
                .toList()
                .forEach(p->{
                    List<Patient> list1 = list.stream().filter(x -> x.getAge() == p.getAge()).toList();
                    patientMap.put(p.getAge(),list1);
                });

        return patientMap;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
      return    DataBase.hospitals.stream()
                .flatMap(h -> h.getPatients().stream())
                .sorted((x, y) -> {
                    if (ascOrDesc.equalsIgnoreCase("asc")) {
                        return x.getAge() - y.getAge();
                    }
                    return y.getAge() - x.getAge();

                }).toList();
    }
}
