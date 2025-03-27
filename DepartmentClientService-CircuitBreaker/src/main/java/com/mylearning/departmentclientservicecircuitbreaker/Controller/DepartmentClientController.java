package com.mylearning.departmentclientservicecircuitbreaker.Controller;

import com.mylearning.departmentclientservicecircuitbreaker.model.DepartmentDTO;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/details")
public class DepartmentClientController {

    @Autowired
    private RestTemplate restTemplate;

    private static final String DEPARTMENT_SERVICE = "department_circuit_breaker";

    @GetMapping
    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackGetAllDepartments")
    public ResponseEntity<Object> getAllDepartments() {
        List<?> departments = restTemplate.getForObject("http://localhost:8082/departments", List.class);
        if (departments == null) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(departments);
    }

    @GetMapping("/{id}")
    @CircuitBreaker(name = DEPARTMENT_SERVICE, fallbackMethod = "fallbackGetDepartmentById")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable int id) {
        return ResponseEntity.ok(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8082/departments/" + id, DepartmentDTO.class)));
    }

    // The return type of the fallback method must match the return type of the original method.
    // Fallback for getAllDepartments()
    public ResponseEntity<Object> fallbackGetAllDepartments(Exception e) {
        return ResponseEntity.ok("Circuit Breaker Open! Service is down. Message: " + e.getMessage());
    }

    // The return type of the fallback method must match the return type of the original method.
    // Fallback for getDepartmentById()
    public ResponseEntity<DepartmentDTO> fallbackGetDepartmentById(int id, Exception e) {
        DepartmentDTO fallbackDept = new DepartmentDTO(id, "Fallback Department", "Service unavailable");
        return ResponseEntity.ok(fallbackDept);
    }
}
