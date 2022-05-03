package dreamteam.hotelchainproject.controllers;

import java.util.List;

import javax.validation.Valid;

import dreamteam.hotelchainproject.models.Employee;
import dreamteam.hotelchainproject.models.User;
import dreamteam.hotelchainproject.repositories.EmployeeRespository;
import dreamteam.hotelchainproject.repositories.UserRepository;
import dreamteam.hotelchainproject.security.jwt.JwtUtils;
import dreamteam.hotelchainproject.security.jwt.LoginRequest;
import dreamteam.hotelchainproject.security.jwt.SignupRequest;
import dreamteam.hotelchainproject.security.jwt.response.JwtResponse;
import dreamteam.hotelchainproject.security.jwt.response.MessageResponse;
import dreamteam.hotelchainproject.security.services.UserDetailsImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.validation.Errors;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

@CrossOrigin(origins = "*", maxAge = 3600)
@RestController
@RequestMapping("/api/auth")
public class AuthController {
    @Autowired
    AuthenticationManager authenticationManager;

    @Autowired
    UserRepository userRepository;

    @Autowired
    PasswordEncoder encoder;

    @Autowired
    JwtUtils jwtUtils;
    @Autowired
    private EmployeeRespository employeeRespository;

    @PostMapping("/signin")
    public ResponseEntity<?> authenticateUser(@Valid @RequestBody LoginRequest loginRequest) {

        Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(loginRequest.getUsername(), loginRequest.getPassword()));

        SecurityContextHolder.getContext().setAuthentication(authentication);
        String jwt = jwtUtils.generateJwtToken(authentication);

        UserDetailsImpl userDetails = (UserDetailsImpl) authentication.getPrincipal();
        Employee employee = employeeRespository.findById(userDetails.getUsername()).orElse(null);
        String role = "";
        if(employee != null) role = employee.getType();
//        List<String> roles = userDetails.getAuthorities().stream()
//                .map(item -> item.getAuthority())
//                .collect(Collectors.toList());

        return ResponseEntity.ok(new JwtResponse(jwt,
                userDetails.getUsername(), role));
    }

    @PostMapping("/signup")
    @ResponseBody
    public ResponseEntity<?> registerUser(@Valid @RequestBody SignupRequest signUpRequest, Errors errors) {
        if (errors.hasErrors()) {
            String response = "";
            List<ObjectError> allErrors = errors.getAllErrors();
            for(ObjectError err: allErrors) {
                String errString = err.getDefaultMessage();
                response += errString + "\n";
            }
            return new ResponseEntity(new MessageResponse(response), HttpStatus.BAD_REQUEST);
        }
        if (userRepository.existsByUsername(signUpRequest.getUsername())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Username is already taken!"));
        }

        if (userRepository.existsByMobilePhone(signUpRequest.getMobilePhone())) {
            return ResponseEntity
                    .badRequest()
                    .body(new MessageResponse("Error: Phone number is already in use!"));
        }

        // Create new user's account
        User user = new User(signUpRequest);

        userRepository.save(user);

        return ResponseEntity.ok(new MessageResponse("User registered successfully!"));
    }
}
