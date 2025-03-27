package com.mylearning.departmentclientservicecircuitbreaker.Controller;

import com.mylearning.departmentclientservicecircuitbreaker.model.DepartmentDTO;
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

    @GetMapping
    public ResponseEntity<Object> getAllDepartments() {
        return ResponseEntity.ok(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8082/departments", List.class)));
    }

    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> getDepartmentById(@PathVariable int id) {
        return ResponseEntity.ok(Objects.requireNonNull(restTemplate.getForObject("http://localhost:8082/departments/" + id, DepartmentDTO.class)));
    }

}
