package java16.service.impl;

import java16.dao.impl.DoctorDaoImpl;
import java16.models.Doctor;
import java16.service.DoctorService;
import java16.service.GenericService;

import java.util.List;

public class DoctorServiceImpl implements DoctorService, GenericService<Doctor> {
  private final   DoctorDaoImpl doctorDao = new DoctorDaoImpl();
    @Override
    public String add(Long hospitalId, Doctor doctor) {
        doctorDao.add(hospitalId,doctor);
        return "Successful!";
    }

    @Override
    public void removeById(Long id) {
doctorDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Doctor doctor) {
       return doctorDao.updateById(id,doctor);
    }


    @Override
    public Doctor findDoctorById(Long id) {
        return doctorDao.findDoctorById(id);
    }

    @Override
    public String assignDoctorToDepartment(Long departmentId, List<Long> doctorsId) {
        return doctorDao.assignDoctorToDepartment(departmentId,doctorsId);

    }

    @Override
    public List<Doctor> getAllDoctorsByHospitalId(Long id) {
        return doctorDao.getAllDoctorsByHospitalId(id);
    }

    @Override
    public List<Doctor> getAllDoctorsByDepartmentId(Long id) {
        return doctorDao.getAllDoctorsByDepartmentId(id);
    }
}
