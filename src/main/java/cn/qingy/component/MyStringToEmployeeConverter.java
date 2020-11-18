package cn.qingy.component;

import cn.qingy.dao.DepartmentDao;
import cn.qingy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.convert.converter.Converter;

public class MyStringToEmployeeConverter implements Converter<String, Employee> {

    @Autowired
    private DepartmentDao departmentDao;

    @Override
    public Employee convert(String s) {
        System.out.println("要转换的字符串：" + s);
        Employee employee = new Employee();
        if (s.contains("-")) {
            String[] split = s.split("-");
            employee.setLastName(split[0]);
            employee.setEmail(split[1]);
            employee.setGender(Integer.parseInt(split[2]));
            employee.setDepartment(departmentDao.getDepartment(Integer.parseInt(split[3])));
        }
        return employee;
    }
}
