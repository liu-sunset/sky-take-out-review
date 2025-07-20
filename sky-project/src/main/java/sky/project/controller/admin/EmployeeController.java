package sky.project.controller.admin;

import dto.EditPasswordDTO;
import dto.EmpDTO;
import dto.EmpPageDTO;
import dto.EmployeeLoginDTO;
import entity.Employee;
import lombok.extern.slf4j.Slf4j;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import result.Result;
import sky.project.service.EmployeeService;
import vo.EmpPageVO;
import vo.EmployeeLoginVO;


@RestController
@RequestMapping("/admin/employee")
public class EmployeeController {
    private static final Logger log = LoggerFactory.getLogger(EmployeeController.class);
    @Autowired
    private EmployeeService employeeService;

    @PostMapping("/login")
    public Result empLoginController(@RequestBody EmployeeLoginDTO employeeLoginDTO){
        log.info("员工登录信息:{}",employeeLoginDTO);
        EmployeeLoginVO employeeLoginVO = employeeService.empLoginService(employeeLoginDTO);
        return Result.success(employeeLoginVO);
    }

    @PostMapping
    public Result addEmpController(@RequestBody EmpDTO empDTO){
        log.info("新增员工：{}",empDTO);
        employeeService.addEmpService(empDTO);
        return Result.success();
    }

    @GetMapping("/page")
    public Result empPageController(EmpPageDTO empPageDTO){
        log.info("员工分页查询:{}",empPageDTO);
        EmpPageVO empPageVO = employeeService.empPageService(empPageDTO);
        return Result.success(empPageVO);
    }

    @PostMapping("/status/{status}")
    public Result empStatusModifyController(@PathVariable Integer status,long id){
        log.info("员工账号id{}状态修改为{}",status,id);
        employeeService.empStatusModifyService(status,id);
        return Result.success();
    }

    @GetMapping("/{id}")
    public Result selectEmpByIdController(@PathVariable long id){
        log.info("根据ID查询员工:{}",id);
        Employee employee = employeeService.selectEmpByIdService(id);
        return Result.success(employee);
    }

    @PutMapping
    public Result modifyEmpController(@RequestBody EmpDTO empDTO){
        log.info("修改员工信息为:{}",empDTO);
        employeeService.modifyEmpService(empDTO);
        return Result.success();
    }

    @PutMapping("/editPassword")
    public Result editEmpPasswordController(@RequestBody EditPasswordDTO editPasswordDTO){
        log.info("修改员工密码:{}",editPasswordDTO);
        employeeService.edieEmpPasswordService(editPasswordDTO);
        return Result.success();
    }

    @PostMapping("/logout")
    public Result empLogoutController(){
        log.info("员工登出");
        return Result.success();
    }
}
