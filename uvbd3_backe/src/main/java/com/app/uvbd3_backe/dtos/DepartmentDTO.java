package com.app.uvbd3_backe.dtos;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class DepartmentDTO {
    private Long id;
    private String departmentName;
    private String departmentDescription;
}
