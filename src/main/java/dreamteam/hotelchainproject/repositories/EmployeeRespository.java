package dreamteam.hotelchainproject.repositories;

import dreamteam.hotelchainproject.models.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRespository extends JpaRepository<Employee,String> {
    List<Employee> getAllByHotelId(int hotelId);
    @Override
    Optional<Employee> findById(String s);
}