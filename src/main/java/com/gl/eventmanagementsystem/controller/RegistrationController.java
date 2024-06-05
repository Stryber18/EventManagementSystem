package com.gl.eventmanagementsystem.controller;

import com.gl.eventmanagementsystem.dto.RegistrationDto;
import com.gl.eventmanagementsystem.service.RegistrationService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

/*
    Add the required annotations to make this class a REST Controller
    for handling the registration related requests.
 */

@RestController
@RequestMapping("/api/events")
public class RegistrationController {
    private final RegistrationService registrationService;

    public RegistrationController(RegistrationService registrationService) {
        this.registrationService = registrationService;
    }

    // Add a registration
    @PostMapping("/register")
    public RegistrationDto register(@Valid @RequestBody RegistrationDto registrationDto) {
        return registrationService.register(registrationDto);
    }

    // Get the registration status
    @GetMapping("/status/{eventId}/{regId}")
    public RegistrationDto getRegistrationStatus(@PathVariable Long eventId, @PathVariable Long regId) {
        return registrationService.getRegistrationStatus(eventId, regId);
    }

    // Delete a registration
    @DeleteMapping("/cancel/{eventId}/{regId}")
    public String deleteRegistration(@PathVariable Long eventId, @PathVariable Long regId) {
        registrationService.deleteRegistration(eventId, regId);
        return "Registration canceled successfully";
    }
}
