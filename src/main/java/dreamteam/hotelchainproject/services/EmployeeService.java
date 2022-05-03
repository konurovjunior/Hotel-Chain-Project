package dreamteam.hotelchainproject.services;

import dreamteam.hotelchainproject.dto.employee.EmployeeDto;

import java.util.List;

public interface EmployeeService {
    List<EmployeeDto> getEmployeeDtosByManagerEmail(String email);
}
