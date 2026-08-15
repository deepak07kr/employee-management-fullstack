package com.deepak.employee_management.controller;
import java.util.List;
// import java.util.function.Function;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.deepak.employee_management.model.Employee;
import com.deepak.employee_management.repository.EmployeeRepository;
import jakarta.validation.Valid;


@RestController
@RequestMapping("/api")
@CrossOrigin(origins="*")


public class EmployeeController {
    private final EmployeeRepository repository;

    public EmployeeController (EmployeeRepository repository){
        this.repository=repository;
    }

    @GetMapping
    public List<Employee> getAll(){
        return repository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Employee> getById(@PathVariable Integer id){
        return repository.findById(id)
        .map(ResponseEntity::ok)
        .orElse(ResponseEntity.notFound().build());

    }




    @PostMapping
    public Employee create(@Valid @RequestBody Employee employee){
        return repository.save(employee);
    }


    @PutMapping("/{id}")
    public ResponseEntity<Employee> update( @PathVariable Integer id ,@Valid @RequestBody Employee input){
        return repository.findById(id).map( employee->{
            employee.setName(input.getName());
            employee.setDepartment(input.getDepartment());
            employee.setEmail(input.getEmail());
            employee.setRoll_no(input.getRoll_no());
            return ResponseEntity.ok(repository.save(employee));
            
        }).orElse(ResponseEntity.notFound().build());
    }


    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Integer id){
        if(!repository.existsById(id)){
            return ResponseEntity.notFound().build();
        }
            repository.deleteById(id);
            return ResponseEntity.noContent().build();
        
    }

    


}
