package cn.qingy.controller;

import cn.qingy.dao.DepartmentDao;
import cn.qingy.dao.EmployeeDao;
import cn.qingy.entities.Department;
import cn.qingy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Collection;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author Qing_Y
 */
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
    public String addEmp(@Validated Employee employee, BindingResult bindingResult, Model model) {
        System.out.println(employee);
        Map<String, Object> errorsMap = new HashMap<String, Object>();
        boolean hasErrors = bindingResult.hasErrors();
        if (hasErrors) {
            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            for (FieldError error : fieldErrors) {
                System.out.println("错误消息提示：" + error.getDefaultMessage());
                System.out.println("错误的字段是：" + error.getField());
                System.out.println(error);
                System.out.println("--------------------");
                errorsMap.put(error.getField(), error.getDefaultMessage());
            }
            model.addAttribute("errorInfo", errorsMap);
            System.out.println("有检验错误...");
            return "add";
        } else {
            employeeDao.save(employee);
            // 返回列表页面，重定向到查询所有员工的请求
            return "redirect:/emps";
        }
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

    /**
     * 根据id删除员工
     *
     * @param id
     * @return
     */
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
        // 先查出所有部门
        Collection<Department> departments = departmentDao.getDepartments();
        model.addAttribute("departments", departments);
        model.addAttribute("employee", new Employee());
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

    /**
     * 快速添加一条员工信息，用于自定义转换器测试
     *
     * @param employee
     * @return
     */
    @RequestMapping("/quickadd")
    public String quickAdd(@RequestParam("empInfo") Employee employee) {
        System.out.println(employee);
        employeeDao.save(employee);
        return "redirect:/emps";
    }

    /**
     * 测试GitHub更新
     *
     * @return
     */
    public String success() {
        System.out.println("success");
        return "success";
    }

}
