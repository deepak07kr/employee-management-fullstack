package com.deepak.employee_management.model;
import jakarta.persistence.*;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
// import jakarta.persistence.Entity;
// import jakarta.persistence.Table;

@Entity
@Table( name="Employee")

public class Employee {
    
    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Integer id;

    @NotBlank 
    private String name;

    @NotBlank
    private String roll_no;

    @NotBlank
    private String department; 

    @Email
    @NotBlank
    @Column(unique=true)
    private String email;

    public Employee(){

    }
    public Employee(Integer id,String name,String roll_no,String department,String email){
        this.id=id;
        this.name=name;
        this.roll_no=roll_no;
        this.department=department;
        this.email=email;
    }

    //getters and setter
    public Integer getId(){
        return id;
    }
    public void setId(Integer id){
        this.id=id;
    } 


    public String getName(){
        return name;
    }
    public void setName(String name){
        this.name=name;
    }


    public String getRoll_no(){
        return roll_no;
    }
    public void setRoll_no(String roll_no){
        this.roll_no=roll_no;
    }


    public String  getDepartment(){
        return department;
    }
    public void setDepartment(String department){
        this.department=department;
    }



    public String getEmail(){
        return email;
    }
    public void setEmail(String email){
        this.email=email;
    }


}
