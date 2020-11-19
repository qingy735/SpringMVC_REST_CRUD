package cn.qingy.controller;

import cn.qingy.dao.EmployeeDao;
import cn.qingy.entities.Employee;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.Collection;

/**
 * @author Qing_Y
 */
@Controller
public class AjaxController {

    @Autowired
    private EmployeeDao employeeDao;

    /**
     * _@RequestBody:请求体，获取一个请求的请求体
     *
     * @return
     */
    @RequestMapping("/testRequestBody")
    public String testRequestBody(@RequestBody Employee employee) {
        System.out.println("json转对象：" + employee);
        return "success";
    }

    /**
     * 将返回的数据放在响应体中，
     * 如果是对象，jackson包自动将对象转为json格式
     *
     * @return
     */
    @ResponseBody
    @RequestMapping("/ajaxgetall")
    public Collection<Employee> ajaxGetAll() {
        return employeeDao.getAll();
    }
}
