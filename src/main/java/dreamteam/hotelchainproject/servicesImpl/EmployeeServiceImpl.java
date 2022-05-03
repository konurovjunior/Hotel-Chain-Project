package dreamteam.hotelchainproject.servicesImpl;

import dreamteam.hotelchainproject.dto.employee.EmployeeDto;
import dreamteam.hotelchainproject.models.Employee;
import dreamteam.hotelchainproject.repositories.EmployeeRespository;
import dreamteam.hotelchainproject.repositories.UserRepository;
import dreamteam.hotelchainproject.services.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    @Autowired
    private EmployeeRespository employeeRespository;
    @Autowired
    private UserRepository userRepository;

    @Override
    public List<EmployeeDto> getEmployeeDtosByManagerEmail(String email) {
        List<EmployeeDto> result = new ArrayList<>();
        Employee employee = employeeRespository.findById(email).get();
        int hotelId = employee.getHotelId();
        List<Employee> employees = employeeRespository.getAllByHotelId(hotelId);
        for(Employee e: employees) {
            result.add(mapEmployeetoDto(e));
        }
        return result;
    }
    private EmployeeDto mapEmployeetoDto(Employee employee) {
        EmployeeDto dto = new EmployeeDto();
        dto.setUsername(employee.getEmail());
        dto.setType(employee.getType());
        dto.setHourlyRate(employee.getHourlyRate());
        dto.setWorkingDays(employee.getWorkingDays());
        dto.setWorkingFrom(employee.getFrom());
        dto.setWorkingTill(employee.getTill());
        return dto;
    }
}
