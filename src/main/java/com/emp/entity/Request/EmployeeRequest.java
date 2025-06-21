package com.emp.entity.Request;

import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class EmployeeRequest {


    private int id;

    @NotBlank(message = "First name is madatory")
    @Size(min=5,max=30, message = "firstName should be between 5-30")
    private String first_name;

    @NotBlank(message = "Last name is madatory")
    @Size(min=5,max=30,message = "LastName should be between 5-30")
    private String last_name;

    @NotBlank(message = "Email is madatory")
    @Email(message = "Please provide valid Email Address")
    private String email;

    public EmployeeRequest(int id, String first_name, String last_name, String email) {
        this.id = id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
