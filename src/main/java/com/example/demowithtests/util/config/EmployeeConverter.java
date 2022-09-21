package com.example.demowithtests.util.config;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import ma.glasnost.orika.MapperFacade;
import org.springframework.stereotype.Component;
import java.util.List;

@Component
public class EmployeeConverter {

    private final MapperFacade mapperFacade;

    public EmployeeConverter(MapperFacade mapperFacade) {
        this.mapperFacade = mapperFacade;
    }

    public MapperFacade getMapperFacade() {
        return mapperFacade;
    }

    public EmployeeDto toDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeDto.class);
    }

    public EmployeeReadDto toReadDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeReadDto.class);
    }

    public EmployeeDtoWithDate employeeDtoWithDate(Employee entity) {
        return mapperFacade.map(entity, EmployeeDtoWithDate.class);
    }

    public List<EmployeeReadTechDto> employeeReadTechDto(List<Employee> entityList) {
        return mapperFacade.mapAsList(entityList, EmployeeReadTechDto.class);
    }

    public List<EmployeeReadByCountryDto> employeeReadByCountryDto(List<Employee> entityList) {
        return mapperFacade.mapAsList(entityList, EmployeeReadByCountryDto.class);
    }

    public EmployeeUpdateDto employeeUpdateDto(Employee entity) {
        return mapperFacade.map(entity, EmployeeUpdateDto.class);
    }

    public EmployeeDeleteDto employeeDeleteDto (Employee entity){
        return mapperFacade.map(entity, EmployeeDeleteDto.class);
    }

    public EmployeeUpdatePhoneNumber employeeUpdatePhoneNumber (Employee entity){
        return mapperFacade.map(entity, EmployeeUpdatePhoneNumber.class);
    }

    public Employee fromDto(EmployeeDto dto) {
        return mapperFacade.map(dto, Employee.class);
    }
}
