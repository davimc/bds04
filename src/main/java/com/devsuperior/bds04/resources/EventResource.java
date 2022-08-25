package com.devsuperior.bds04.resources;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.services.EventService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import javax.validation.Valid;
import java.net.URI;

@RestController
@RequestMapping(value = "events")
public class EventResource {

    @Autowired
    private EventService service;

    @GetMapping
    public ResponseEntity<Page<EventDTO>> findAll(Pageable pageable) {
        return ResponseEntity.ok()
                        .body(service.findAll(pageable));
    }

    @PostMapping
    public ResponseEntity<EventDTO> insert(@RequestBody @Valid EventDTO dto) {
        dto = service.insert(dto);
        URI uri = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}")
                .buildAndExpand(dto.getId()).toUri();
        return ResponseEntity.created(uri).body(dto);
    }
}
