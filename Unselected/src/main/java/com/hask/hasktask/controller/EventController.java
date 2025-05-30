package com.hask.hasktask.controller;

import com.hask.hasktask.model.Event;
import com.hask.hasktask.service.EventService;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/v1/events")
@Tag(name = "Event Management")
public class EventController {

    final private EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }

    @PostMapping
    public ResponseEntity<?> createEvent(@RequestBody Event body) {
        // Save event to the database
        eventService.createEvent(body);

        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Endpoint to delete an event
    @DeleteMapping("/{eventId}")
    public ResponseEntity<Void> deleteEvent(@PathVariable Long eventId) {
        // Delete event from the database
        eventService.deleteEvent(eventId);

        return ResponseEntity.noContent().build();
    }


    @GetMapping("/{userId}/user")
    public ResponseEntity<List<Event>> getEventsByUserId(@PathVariable Long userId) {
        return ResponseEntity.ok(eventService.getEventsByUserId(userId));
    }

    @GetMapping("/{eventId}")
    public ResponseEntity<Event> getEvent(@PathVariable Long eventId) {
        return ResponseEntity.ok(eventService.getEventById(eventId));
    }

    @PutMapping("/{eventId}")
    public ResponseEntity<Event> updateEvent(@PathVariable Long eventId, @RequestBody Event event) {
        return ResponseEntity.ok(eventService.updateEvent(eventId, event));
    }

}

