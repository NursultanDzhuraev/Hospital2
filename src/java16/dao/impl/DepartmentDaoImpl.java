package java16.dao.impl;

import java16.dao.DepartmentDao;
import java16.dao.GenericDao;
import java16.db.DataBase;
import java16.models.Department;
import java16.models.Hospital;

import java.util.List;
import java.util.NoSuchElementException;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class DepartmentDaoImpl implements DepartmentDao, GenericDao<Department> {
    @Override
    public String add(Long hospitalId, Department department) {
        Boolean b = DataBase.hospitals.stream()
                .filter(hospital -> hospital.getId().equals(hospitalId))
                .findFirst()
                .map(hospital -> hospital.getDepartments().add(department))
                .orElse(false);
        return b ? "Saved!" : "no save";
    }

    @Override
    public void removeById(Long id) {
        DataBase.hospitals.forEach(h -> h.getDepartments().removeIf(d -> d.getId().equals(id)));
        System.out.println("removed!");

    }

    @Override
    public String updateById(Long id, Department department) {
        for (Hospital hospital : DataBase.hospitals) {
            Department department2 = hospital.getDepartments().stream()
                    .filter(d -> d.getId().equals(id))
                    .peek(department1 -> {
                        department1.setDepartmentName(department.getDepartmentName());
                    })
                    .findFirst()
                    .orElse(null);
            if (department2 != null) {
                System.out.println(department2);
                return "updated";
            }

        }
        return "not fount";
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return DataBase.hospitals.stream()
                .filter(h -> h.getId().equals(id))
                 .findFirst()
                 .orElseThrow(()->new NoSuchElementException("not found")).getDepartments();

    }

    @Override
    public Department findDepartmentByName(String name) {
        return DataBase.hospitals.stream()
                .flatMap(h -> h.getDepartments().stream())
                .filter(d -> d.getDepartmentName().equalsIgnoreCase(name))
                .findFirst()
                .orElse(null);
    }
    public Department findDepartmentById(Long id) {
        return DataBase.hospitals.stream()
                .flatMap(h -> h.getDepartments().stream())
                .filter(d -> d.getId().equals(id))
                .findFirst()
                .orElse(null);
    }
}
