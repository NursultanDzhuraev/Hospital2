package java16.dao.impl;

import java16.dao.DoctorDao;
import java16.dao.GenericDao;
import java16.db.DataBase;
import java16.models.Department;
import java16.models.Doctor;
import java16.models.Hospital;

import java.util.ArrayList;
import java.util.List;

public class DoctorDaoImpl implements DoctorDao, GenericDao<Doctor> {
    private final HospitalDaoImpl hospitalDao =new HospitalDaoImpl();
    private final DepartmentDaoImpl departmentDao =new DepartmentDaoImpl();
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        Hospital hospital1 = DataBase.hospitals.stream()
                .filter(h -> h.getId().equals(hospitalId))
                .findFirst()
                .orElse(null);
        if (hospital1==null)return "not found";
        if (hospital1.getDoctors() != null) {
            hospital1.getDoctors().add(doctor);
            return "Successful!";
        } else if (hospital1.getDoctors() == null) {
            hospital1.setDoctors(new ArrayList<>());
            hospital1.getDoctors().add(doctor);
            return "Successful!";
        }
        return "not found";
    }

    @Override
    public void removeById(Long id) {
        DataBase.hospitals.forEach(hospital -> hospital.getDoctors().removeIf(d->d.getId().equals(id)));
        System.out.println("removed!");
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
        Doctor doctor2 = DataBase.hospitals.stream()
                .flatMap(h -> h.getDoctors().stream())
                .filter(doctor1 -> doctor1.getId().equals(id))
                .peek(d -> {
                    d.setLastName(doctor.getLastName());
                    d.setFirstName(doctor.getFirstName());
                    d.setGender(doctor.getGender());
                    d.setExperienceYear(doctor.getExperienceYear());

                })
                .findFirst()
                .orElseThrow(() -> new RuntimeException("error update!!"));
        if (doctor2!=null){
            return "updated!";
        }
        return "no updated!";
    }

    @Override
    public Doctor findDoctorById(Long id) {
     return    DataBase.hospitals.stream()
                .flatMap(h->h.getDoctors().stream())
                .filter(d->d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        Department department1 = DataBase.hospitals.stream()
                .flatMap(hospital -> hospital.getDepartments().stream())
                .filter(d -> d.getId().equals(departmentId))
                .findFirst()
                .orElse(null);
       List<Doctor> doctors =new ArrayList<>();
       doctorsId.forEach(dId->doctors.add(findDoctorById(dId)));
       if (department1==null){
           return "not fount";
       }
       if (department1.getDoctors()==null){
           department1.setDoctors(new ArrayList<>());
           department1.getDoctors().addAll(doctors);
           return "Successful";
       }else {
           department1.getDoctors().addAll(doctors);
           return "Successful";
       }
    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
       return hospitalDao.findHospitalById(id).getDoctors();
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
       return departmentDao.findDepartmentById(id).getDoctors();

    }
}
