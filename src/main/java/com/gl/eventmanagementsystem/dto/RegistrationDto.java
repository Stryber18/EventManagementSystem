package com.gl.eventmanagementsystem.dto;

import com.gl.eventmanagementsystem.entity.RegistrationStatus;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/*
    * Add the appropriate annotation for the fields to handle the validation
      using hibernate validator. Requirements are:
      * name should not be null and should have a minimum of 3 characters
      * email should not be null and should be a valid email
      * contact number should not be null and should have a minimum of 10 characters
      * event id should be positive
 */

@Setter
@Getter
@AllArgsConstructor
@NoArgsConstructor
public class RegistrationDto {
    private Long id;
    @NotEmpty(message = "registration name should not be null")
    @Size(min = 3, message = "name should have a minimum 3 characters")
    private String name;
    @NotEmpty(message = "email name should not be null")
    @Email(message = "Please provide a valid email")
    private String email;
//    @NotEmpty(message = "contact number should not be null")
    @NotEmpty(message = "must not be empty")
    @Size(min = 10, message = "contact number should have a minimum of 10 characters")
    private String contactNumber;
    private RegistrationStatus status;
    @Positive(message = "Event id should be positive")
    private Long eventId;
}
