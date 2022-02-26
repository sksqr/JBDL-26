package com.geeksforgeeks.minor.l11_visitor_app.service;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Flats;
import com.geeksforgeeks.minor.l11_visitor_app.model.FlatsDTO;
import com.geeksforgeeks.minor.l11_visitor_app.repos.FlatsRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class FlatsService {

    private final FlatsRepository flatsRepository;

    public FlatsService(final FlatsRepository flatsRepository) {
        this.flatsRepository = flatsRepository;
    }

    public List<FlatsDTO> findAll() {
        return flatsRepository.findAll()
                .stream()
                .map(flats -> mapToDTO(flats, new FlatsDTO()))
                .collect(Collectors.toList());
    }

    public FlatsDTO get(final Long id) {
        return flatsRepository.findById(id)
                .map(flats -> mapToDTO(flats, new FlatsDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final FlatsDTO flatsDTO) {
        final Flats flats = new Flats();
        mapToEntity(flatsDTO, flats);
        return flatsRepository.save(flats).getId();
    }

    public void update(final Long id, final FlatsDTO flatsDTO) {
        final Flats flats = flatsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(flatsDTO, flats);
        flatsRepository.save(flats);
    }

    public void delete(final Long id) {
        flatsRepository.deleteById(id);
    }

    private FlatsDTO mapToDTO(final Flats flats, final FlatsDTO flatsDTO) {
        flatsDTO.setId(flats.getId());
        flatsDTO.setNumber(flats.getNumber());
        return flatsDTO;
    }

    private Flats mapToEntity(final FlatsDTO flatsDTO, final Flats flats) {
        flats.setNumber(flatsDTO.getNumber());
        return flats;
    }

}
