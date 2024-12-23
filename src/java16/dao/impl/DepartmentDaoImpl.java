package java16.dao.impl;

import java16.dao.DepartmentDao;
import java16.dao.GenericDao;
import java16.db.DataBase;
import java16.models.Department;
import java16.models.Hospital;

import java.util.List;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public String add(Long hospitalId, Department department) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(hospitalId)){
                    hospital.getDepartments().add(department);
                    return "Saved!";
            }
        }
        return "no save";
    }

    @Override
    public void removeById(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getId().equals(id)){
                    hospital.getDepartments().remove(id);
                    System.out.println("removed!");
                }
            }
        }
    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department hospitalDepartment : hospital.getDepartments()) {
                if (hospitalDepartment.getId().equals(id)){
                    hospitalDepartment.setDepartmentName(department.getDepartmentName());
                    hospitalDepartment.setDoctors(department.getDoctors());
                    hospitalDepartment.setId(department.getId());
                    return "Successful!";
                }
            }
        }
        return "not found!";
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        for (Hospital hospital : DataBase.hospitals) {
            if (hospital.getId().equals(id)){
                return hospital.getDepartments();
            }
        }
        return null;
    }

    @Override
    public Department findDepartmentByName(String name) {
        for (Hospital hospital : DataBase.hospitals) {
            for (Department department : hospital.getDepartments()) {
                if (department.getDepartmentName().equalsIgnoreCase(name)){
                    return department;
                }
            }
        }
        return null;
    }
}
