package com.devsuperior.bds04.services;

import com.devsuperior.bds04.dto.EventDTO;
import com.devsuperior.bds04.entities.Event;
import com.devsuperior.bds04.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class EventService {

    @Autowired
    private EventRepository repository;

    @Transactional(readOnly = true)
    public Page<EventDTO> findAll(Pageable pageable) {
        return repository.findAll(pageable).map(EventDTO::new);
    }

    @Transactional
    public EventDTO insert(EventDTO dto) {
        Event obj = repository.save(fromDto(dto));

        return new EventDTO(obj);
    }

    private Event fromDto(EventDTO dto) {
        Event obj = new Event();
        obj.setName(dto.getName());

        return obj;
    }
}
