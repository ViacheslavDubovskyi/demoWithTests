package com.example.demowithtests.web;

import com.example.demowithtests.domain.Employee;
import com.example.demowithtests.dto.*;
import com.example.demowithtests.service.Service;
import com.example.demowithtests.util.config.EmployeeConverter;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.AllArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@AllArgsConstructor
@RequestMapping(value = "/api", produces = MediaType.APPLICATION_JSON_VALUE)
@Tag(name = "Employee", description = "Employee API")
public class Controller {

    private final Service service;
    private final EmployeeConverter converter;

    @PostMapping("/users")
    @ResponseStatus(HttpStatus.CREATED)
    @Operation(summary = "This is endpoint to add a new employee.", description = "Create request to add a new employee.", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "CREATED. The new employee is successfully created and added to database."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeDto saveEmployee(@RequestBody @Valid EmployeeDto requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.toDto(service.create(employee));

        return dto;
    }

    @PostMapping("/users/save_date")
    @ResponseStatus(HttpStatus.CREATED)
    public EmployeeDtoWithDate saveEmployee(@RequestBody @Valid EmployeeDtoWithDate requestForSave) {

        var employee = converter.getMapperFacade().map(requestForSave, Employee.class);
        var dto = converter.employeeDtoWithDate(service.create(employee));

        return dto;
    }

    @GetMapping("/users/tech")
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadTechDto> getAllUsersTech() {
        var employeeList = service.getAll();
        var dto = converter.employeeReadTechDto(employeeList);
        return dto;
    }

    @GetMapping("/users/")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllUsers() {

        return service.getAll();
    }

    //Получения юзера по id
    @GetMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    @Operation(summary = "This is endpoint returned a employee by his id.", description = "Create request to read a employee by id", tags = {"Employee"})
    @ApiResponses(value = {
            @ApiResponse(responseCode = "201", description = "OK. pam pam param."),
            @ApiResponse(responseCode = "400", description = "Invalid input"),
            @ApiResponse(responseCode = "404", description = "NOT FOUND. Specified employee request not found."),
            @ApiResponse(responseCode = "409", description = "Employee already exists")})
    public EmployeeReadDto getEmployeeById(@PathVariable Integer id) {
        //log.debug("getEmployeeById() Controller - start: id = {}", id);
        var employee = service.getById(id);
        //log.debug("getById() Controller - to dto start: id = {}", id);
        var dto = converter.toReadDto(employee);
        //log.debug("getEmployeeById() Controller - end: name = {}", dto.name);
        return dto;
    }

    @PutMapping("/users/{id}")
    @ResponseStatus(HttpStatus.OK)
    public Employee refreshEmployee(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        return service.updateById(id, employee);
    }

    @PutMapping("/users/{id}/update_dto")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdateDto refreshEmployeeDto(@PathVariable("id") Integer id, @RequestBody Employee employee) {
        var user =  service.updateById(id, employee);
        var dto = converter.employeeUpdateDto(employee);
        return dto;
    }

    @PatchMapping("/users/{id}/update_phone_number")
    @ResponseStatus(HttpStatus.OK)
    public EmployeeUpdatePhoneNumber refreshDtoPhoneNumber(@PathVariable("id") Integer id,
                                                           @RequestParam(value = "phoneNumber") Integer phoneNumber) {
        var employee =  service.updatePhoneById(id, phoneNumber);
        var dto = converter.employeeUpdatePhoneNumber(employee);
        return dto;
    }

    @PatchMapping("/users/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeEmployeeById(@PathVariable Integer id) {
        service.removeById(id);
    }

    @PatchMapping("/users/{id}/delete_dto")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeByIdDto (@PathVariable Integer id) {
        var dto = converter.employeeDeleteDto(service.getById(id));
        service.removeById(id);
    }

    @DeleteMapping("/users")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void removeAllUsers() {
        service.removeAll();
    }

    @GetMapping(value = "/users", params = {"name"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getAllByName(@RequestParam(value = "name") String name) {
        return service.findAllByName(name);
    }

    @GetMapping(value = "/users/phone_number")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getUsersWithPhoneNumber() {
        return service.findUsersWithPhoneNumber();
    }

    @GetMapping("/users/no_email")
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> generateEmail() {
        return service.findRecordsWhereEmailNull();
    }

    @GetMapping(value = "/users", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    public List<Employee> getUsersByCountry(@RequestParam(value = "country") String country) {
        return service.findEmployeesByCountry(country);
    }

    @GetMapping(value = "/users/country_dto", params = {"country"})
    @ResponseStatus(HttpStatus.OK)
    public List<EmployeeReadByCountryDto> getUsersByCountryDto(@RequestParam(value = "country") String country) {
        var employeeList = service.findEmployeesByCountry(country);
        var dto = converter.employeeReadByCountryDto(employeeList);
        return dto;
    }
}
