package cn.qingy.dao;

import java.util.Collection;
import java.util.HashMap;
import java.util.Map;

import cn.qingy.entities.Department;
import org.springframework.stereotype.Repository;


/**
 * @author Qing_Y
 */
@Repository
public class DepartmentDao {

    private static Map<Integer, Department> departments = null;

    static {
        departments = new HashMap<Integer, Department>();

        departments.put(101, new Department(101, "D-AA"));
        departments.put(102, new Department(102, "D-BB"));
        departments.put(103, new Department(103, "D-CC"));
        departments.put(104, new Department(104, "D-DD"));
        departments.put(105, new Department(105, "D-EE"));
    }

    /**
     * 返回所有的部门
     *
     * @return
     */
    public Collection<Department> getDepartments() {
        return departments.values();
    }

    /**
     * 根据部门id查询
     *
     * @param id
     * @return
     */
    public Department getDepartment(Integer id) {
        return departments.get(id);
    }

}
