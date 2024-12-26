package java16.service.impl;

import java16.dao.impl.DepartmentDaoImpl;
import java16.models.Department;
import java16.service.DepartmentService;
import java16.service.GenericService;

import java.util.List;

public class DepartmentServiceImpl implements DepartmentService, GenericService<Department> {
    private final DepartmentDaoImpl departmentDao = new DepartmentDaoImpl();
    @Override
    public String add(Long hospitalId, Department department) {
     return  departmentDao.add(hospitalId,department);
    }

    @Override
    public void removeById(Long id) {
departmentDao.removeById(id);
    }

    @Override
    public String updateById(Long id, Department department) {
      return   departmentDao.updateById(id,department);
    }

    @Override
    public List<Department> getAllDepartmentByHospital(Long id) {
        return departmentDao.getAllDepartmentByHospital(id);
    }

    @Override
    public Department findDepartmentByName(String name) {
        return departmentDao.findDepartmentByName(name);
    }
}
