package com.mylearning.departmentclientservicecircuitbreaker.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class DepartmentDTO {
    private Integer departmentId;
    private String departmentName;
    private String departmentCode;
}
