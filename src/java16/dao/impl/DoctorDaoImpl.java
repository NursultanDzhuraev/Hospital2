package java16.dao.impl;

import java16.dao.DoctorDao;
import java16.dao.GenericDao;
import java16.db.DataBase;
import java16.models.Department;
import java16.models.Doctor;
import java16.models.Hospital;

import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital != null) {
                if (hospital.getId().equals(hospitalId)) {
                    if (!hospital.getDoctors().contains(doctor)) {
                        hospital.getDoctors().add(doctor);
                        return "Successful!";
                    }
                }
            }
        }
        return "not found";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    hospital.getDoctors().remove(doctor);
                    System.out.println("Successful remove:");
                }
            }
        }

    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Doctor hospitalDoctor : hospital.getDoctors()) {
                if (hospitalDoctor.getId().equals(id)) {
                    hospitalDoctor.setLastName(doctor.getLastName());
                    hospitalDoctor.setFirstName(doctor.getFirstName());
                    hospitalDoctor.setExperienceYear(doctor.getExperienceYear());
                    hospitalDoctor.setId(doctor.getId());
                    hospitalDoctor.setGender(doctor.getGender());
                    System.out.println(doctor);
                    return "updated!";
                }
            }
        }
        return "no updated!";
    }

    @Override
    public Doctor findDoctorById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Doctor doctor : hospital.getDoctors()) {
                if (doctor.getId().equals(id)) {
                    return doctor;
                }
            }
        }
        return null;
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {

        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(departmentId)) {
                    for (Long doctorId : doctorsId) {
                        for (Doctor doctor : hospital.getDoctors()) {
                            if (doctor.getId().equals(doctorId)) {
                                if (!department.getDoctors().contains(doctor)) {
                                    department.getDoctors().add(doctor);
                                }
                            }
                        }
                    }
                    return "Successful!";
                }
            }
        }
        return "not found!";
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)) {
                return hospital.getDoctors();
            }
        }
        return null;
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)) {
                    return department.getDoctors();
                }
            }
        }
        return null;
    }
}
