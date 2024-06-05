package com.gl.eventmanagementsystem.service.impl;


import com.gl.eventmanagementsystem.dto.EventDto;
import com.gl.eventmanagementsystem.entity.Event;
import com.gl.eventmanagementsystem.exception.ResourceNotFoundException;
import com.gl.eventmanagementsystem.repository.EventRepository;
import com.gl.eventmanagementsystem.service.EventService;
import com.gl.eventmanagementsystem.utils.Mapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class EventServiceImpl implements EventService {

    private final EventRepository eventRepository;

    public EventServiceImpl(EventRepository eventRepository) {
        this.eventRepository = eventRepository;
    }

    @Override
    public EventDto createEvent(EventDto eventDto) {
        Event event = Mapper.mapToEvent(eventDto);
        Event savedEvent = eventRepository.save(event);
        return Mapper.mapToEventDto(savedEvent);
    }

    @Override
    public EventDto getEvent(Long eventId) {
        Optional<Event> optionalEvent = eventRepository.findById(eventId);
        if (optionalEvent.isPresent()) {
            return Mapper.mapToEventDto(optionalEvent.get());
        } else {
            throw new ResourceNotFoundException("Event",  "id", eventId );
        }
    }

    @Override
    public List<EventDto> getAllEvents() {
        List<Event> events = eventRepository.findAll();
        return events.stream().map(Mapper::mapToEventDto).collect(Collectors.toList());
    }

    @Override
    public void cancelEvent(Long eventId) {
        if (eventRepository.existsById(eventId)) {
            eventRepository.deleteById(eventId);
        } else {
            throw new ResourceNotFoundException("Event", "id", eventId);
        }
    }
}
