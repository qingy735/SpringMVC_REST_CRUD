package cn.qingy.controller;

import cn.qingy.dao.DepartmentDao;
import cn.qingy.dao.EmployeeDao;
import cn.qingy.entities.Department;
import cn.qingy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
// 这是修改之后的项目
@Controller
public class EmployeeController {

    @Autowired
    private EmployeeDao employeeDao;

    @Autowired
    private DepartmentDao departmentDao;

    /**
     * 查询所有员工
     *
     * @return
     */
    @RequestMapping("/emps")
    public String getEmps(Model model) {
        Collection<Employee> employees = employeeDao.getAll();
        model.addAttribute("employees", employees);
        return "list";
    }

    /**
     * 保存员工
     *
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp", method = RequestMethod.POST)
    public String addEmp(Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        // 返回列表页面，重定向到查询所有员工的请求
        return "redirect:/emps";
    }

    /**
     * 查询员工，来到修改页面回显
     *
     * @param id
     * @param model
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.GET)
    public String getEmp(@PathVariable("id") Integer id, Model model) {
        // 查询出员工信息
        Employee employee = employeeDao.get(id);
        // 继续查出部门信息放入隐含模型中
        Collection<Department> departments = departmentDao.getDepartments();
        // 放在请求域中
        model.addAttribute("employee", employee);
        model.addAttribute("departments", departments);
        return "edit";
    }

    /**
     * 更新员工信息
     *
     * @param employee
     * @return
     */
    @RequestMapping(value = "/emp/{id}", method = RequestMethod.PUT)
    public String updateEmp(@ModelAttribute("employee") Employee employee) {
        System.out.println("要修改的员工：" + employee);
        employeeDao.save(employee);
        // 修改完成后来到列表页面
        return "redirect:/emps";
    }

    @RequestMapping(value = "/emp/{id}", method = RequestMethod.DELETE)
    public String deleteEmp(@PathVariable("id") Integer id) {
        employeeDao.delete(id);
        return "redirect:/emps";
    }

    @ModelAttribute
    public void myModelAttribute(@RequestParam(value = "id", required = false) Integer id, Model model) {
        if (id != null) {
            Employee employee = employeeDao.get(id);
            model.addAttribute("employee", employee);
        }
    }

    /**
     * 去员工添加页面，去之前要查出所有部门信息
     *
     * @param model
     * @return
     */
    @RequestMapping(value = "/toaddpage")
    public String toAddPage(Model model) {
        // 先查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
        return "add";
    }

    @RequestMapping("/quickadd")
    public String quickAdd(@RequestParam("empInfo") Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }
    
    /**
     * 这是一个新加的方法1
     * @return
     */
    public String success1() {
        return "success";
    }

    /**
     * 这是一个新加的方法2
     * @return
     */
    public String success2() {
        return "success";
    }

}
