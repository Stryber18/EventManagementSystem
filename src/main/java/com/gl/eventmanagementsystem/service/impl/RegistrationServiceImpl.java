package com.gl.eventmanagementsystem.service.impl;

import com.gl.eventmanagementsystem.dto.RegistrationDto;
import com.gl.eventmanagementsystem.entity.Registration;
import com.gl.eventmanagementsystem.entity.RegistrationStatus;
import com.gl.eventmanagementsystem.exception.EMSApiException;
import com.gl.eventmanagementsystem.exception.ResourceNotFoundException;
import com.gl.eventmanagementsystem.repository.RegistrationRepository;
import com.gl.eventmanagementsystem.service.RegistrationService;
import com.gl.eventmanagementsystem.utils.Mapper;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class RegistrationServiceImpl implements RegistrationService{

    private final RegistrationRepository registrationRepository;

    public RegistrationServiceImpl(RegistrationRepository registrationRepository) {
        this.registrationRepository = registrationRepository;
    }

    @Override
    public RegistrationDto register(RegistrationDto registrationDto) {
        if (registrationRepository.existsByEmail(registrationDto.getEmail())) {
            throw new EMSApiException(HttpStatus.BAD_REQUEST, "User with email " + registrationDto.getEmail() + " already exists.");
        }
        registrationDto.setStatus(RegistrationStatus.CONFIRMED);
        Registration registration = Mapper.mapToRegistration(registrationDto);
        Registration savedRegistration = registrationRepository.save(registration);
        return Mapper.mapToRegistrationDto(savedRegistration);
    }

    @Override
    public RegistrationDto getRegistrationStatus(Long eventId, Long regId) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(regId);
        if (optionalRegistration.isPresent()) {
            Registration registration = optionalRegistration.get();
            if (registration.getEvent().getId().equals(eventId)) {
                return Mapper.mapToRegistrationDto(registration);
            } else {
                throw new EMSApiException(HttpStatus.BAD_REQUEST, "Registration with ID " + regId + " does not belong to event with ID " + eventId);
            }
        } else {
            throw new ResourceNotFoundException("Registration","id", regId);
        }
    }

    @Override
    public void deleteRegistration(Long eventId, Long regId) {
        Optional<Registration> optionalRegistration = registrationRepository.findById(regId);
        if (optionalRegistration.isPresent()) {
            Registration registration = optionalRegistration.get();
            if (registration.getEvent().getId().equals(eventId)) {
                registrationRepository.delete(registration);
            } else {
                throw new EMSApiException(HttpStatus.BAD_REQUEST, "Registration with ID " + regId + " does not belong to event with ID " + eventId);
            }
        } else {
            throw new ResourceNotFoundException("Registration","id", regId);
        }
    }
}
