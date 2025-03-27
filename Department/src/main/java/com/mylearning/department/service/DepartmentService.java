package com.mylearning.department.service;

import com.mylearning.department.model.Department;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class DepartmentService {

    private static final List<Department> DEPARTMENTS = new ArrayList<>();

    static {
        DEPARTMENTS.add(new Department(1, "Information Technology", "Handles software development, IT infrastructure, and cybersecurity."));
        DEPARTMENTS.add(new Department(2, "Human Resources", "Manages employee relations, recruitment, and workplace policies."));
        DEPARTMENTS.add(new Department(3, "Finance", "Oversees budgeting, accounting, and financial planning."));
        DEPARTMENTS.add(new Department(4, "Sales & Marketing", "Drives business growth through sales and customer engagement."));
        DEPARTMENTS.add(new Department(5, "Research & Development", "Focuses on innovation, product development, and new technologies."));
        DEPARTMENTS.add(new Department(6, "Customer Support", "Handles client queries, technical support, and service issues."));
        DEPARTMENTS.add(new Department(7, "Logistics & Supply Chain", "Manages transportation, warehousing, and inventory control."));
        DEPARTMENTS.add(new Department(8, "Quality Assurance", "Ensures product quality, compliance, and testing procedures."));
        DEPARTMENTS.add(new Department(9, "Legal & Compliance", "Handles legal matters, regulatory compliance, and risk management."));
        DEPARTMENTS.add(new Department(10, "Public Relations", "Manages media relations, corporate communication, and branding."));
    }


    public void addDepartment(Department department) {
        DEPARTMENTS.add(department);
    }

    public List<Department> getDepartments() {
        return DEPARTMENTS;
    }

    public Department getDepartment(int id) {
        return DEPARTMENTS.stream()
                .filter(department -> department.getDepartmentId() == id)
                .findFirst()
                .orElse(null);
    }

    public void deleteDepartment(int id) {
        DEPARTMENTS.removeIf(department -> department.getDepartmentId() == id);
    }

    public Department update(int id, Department department) {
        for (int i = 0; i < DEPARTMENTS.size(); i++) {
            if (DEPARTMENTS.get(i).getDepartmentId() == id) {
                DEPARTMENTS.set(i, department);
                return DEPARTMENTS.get(i);
            }
        }
        return null;
    }

}
