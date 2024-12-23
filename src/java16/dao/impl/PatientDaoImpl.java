package java16.dao.impl;

import java16.dao.GenericDao;
import java16.dao.PatientDao;
import java16.db.DataBase;
import java16.models.Hospital;
import java16.models.Patient;

import java.util.*;

public class PatientDaoImpl implements PatientDao, GenericDao<Patient> {
    @Override
    public String add(Long hospitalId, Patient patient) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(hospitalId)) {
                hospital.getPatients().add(patient);
                return "Successful";
            }
        }
        return "not fount";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    hospital.getPatients().remove(patient);
                    System.out.println(patient + "очурулду!");
                }
            }
        }
    }

    @Override
    public String updateById(Long id, Patient patient) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Patient hospitalPatient : hospital.getPatients()) {
                if (hospitalPatient.getId().equals(id)) {
                    hospitalPatient.setLastName(patient.getLastName());
                    hospitalPatient.setFirstName(patient.getFirstName());
                    hospitalPatient.setAge(patient.getAge());
                    hospitalPatient.setGender(patient.getGender());
                    hospitalPatient.setId(patient.getId());
                    return "Successful!";
                }
            }
        }
        return "not found!";
    }

    @Override
    public String addPatientsToHospital(Long id, List<Patient> patients) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)) {
                hospital.getPatients().addAll(patients);
                return "add all patients!";
            }
        }
        return "not fount!";
    }

    @Override
    public Patient getPatientById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                if (patient.getId().equals(id)) {
                    return patient;
                }
            }
        }
        return null;
    }

    @Override
    public Map<Integer, List<Patient>> getPatientByAge() {
        Map<Integer, List<Patient>> patientMap = new HashMap<>();


        Set<Integer> age = new LinkedHashSet<>();


        for (Hospital hospital : DataBase.hospitals) {
            for (Patient patient : hospital.getPatients()) {
                age.add(patient.getAge());
            }
        }

        for (Integer i : age) {

            List<Patient> patients = new ArrayList<>();

            for (Hospital hospital : DataBase.hospitals) {
                for (Patient patient : hospital.getPatients()) {
                    if (i == patient.getAge()) patients.add(patient);
                }
            }

            patientMap.put(i,patients);

        }

        return patientMap;


//        for (Hospital hospital : DataBase.hospitals) {
//            for (Patient patient : hospital.getPatients()) {
//                patientMap.put(patient.getAge(), patient);
//                for (Map.Entry<Integer, Patient> integerPatientEntry : patientMap.entrySet()) {
//                    if (integerPatientEntry.getKey().equals(patient.getAge())) {
//                        System.out.println(integerPatientEntry.getValue());
//                    }
//                }
//
//            }
//        }
//        return null;
    }

    @Override
    public List<Patient> sortPatientsByAge(String ascOrDesc) {
        for (Hospital hospital : DataBase.hospitals) {
            hospital.getPatients().sort((o1, o2) -> {
                if (ascOrDesc.equalsIgnoreCase("asc")) return o1.getAge() - o2.getAge();
                else return o2.getAge() - o1.getAge();
            });
            return hospital.getPatients();
        }
        return null;
    }
}
