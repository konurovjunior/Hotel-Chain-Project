package dreamteam.hotelchainproject.controllers;

import dreamteam.hotelchainproject.dto.UserInfoDto;
import dreamteam.hotelchainproject.dto.employee.EmployeeDto;
import dreamteam.hotelchainproject.dto.employee.EmployeeInfoDto;
import dreamteam.hotelchainproject.models.Employee;
import dreamteam.hotelchainproject.repositories.EmployeeRespository;
import dreamteam.hotelchainproject.services.UserService;
import dreamteam.hotelchainproject.servicesImpl.EmployeeServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api")
public class UserController {

    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    private UserService userService;
    @Autowired
    private EmployeeRespository employeeRespository;
    @Autowired
    private EmployeeServiceImpl employeeServiceImpl;

    @GetMapping("/user/profile/personal")
    public UserInfoDto getUserPersonalInfo(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        return userService.getUserInfo(username);
    }
    @GetMapping("/employee/list")
    public ResponseEntity<List<EmployeeDto>> getEmployeeList(){
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String username = authentication.getName();
        Employee employee = employeeRespository.findById(username).orElse(null);
        if(employee == null || !employee.getType().equals("manager")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        return ResponseEntity.ok(employeeServiceImpl.getEmployeeDtosByManagerEmail(username));
    }
    @PostMapping("/employee/update")
    public ResponseEntity<Employee> updateEmployeeData(@RequestParam(value = "username") String username, @RequestBody EmployeeInfoDto dto) {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        String authUsername = authentication.getName();
        Employee reqEmployee = employeeRespository.findById(authUsername).orElse(null);
        if(reqEmployee == null || !reqEmployee.getType().equals("manager")) return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(null);
        Employee employee = employeeRespository.findById(username).get();
        employee = employee.update(dto);
        employeeRespository.saveAndFlush(employee);
        return ResponseEntity.ok(employee);
    }

}
