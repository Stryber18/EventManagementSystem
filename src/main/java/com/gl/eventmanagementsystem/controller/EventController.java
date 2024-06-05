package com.gl.eventmanagementsystem.controller;

/*
    Add the required annotations to make this class a REST Controller
    for handling the event related requests.
 */

import com.gl.eventmanagementsystem.dto.EventDto;
import com.gl.eventmanagementsystem.service.EventService;
import jakarta.validation.Valid;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    // Add an event
    @PostMapping
    public EventDto createEvent(@Valid @RequestBody EventDto eventDto) {
        return eventService.createEvent(eventDto);
    }

    // Get all events
    @GetMapping("/{eventId}")
    public EventDto getEvent(@PathVariable Long eventId) {
        return eventService.getEvent(eventId);
    }

    // Get an event by ID
    @GetMapping
    public List<EventDto> getAllEvents() {
        return eventService.getAllEvents();
    }

    // Cancel an event
    @DeleteMapping("/{eventId}")
    public String cancelEvent(@PathVariable Long eventId) {
        eventService.cancelEvent(eventId);
        return "Event canceled successfully";
    }

}
