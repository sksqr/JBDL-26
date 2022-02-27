package com.geeksforgeeks.minor.l12_visitor_app.service;

import com.geeksforgeeks.minor.l12_visitor_app.domain.Flat;
import com.geeksforgeeks.minor.l12_visitor_app.model.FlatsDTO;
import com.geeksforgeeks.minor.l12_visitor_app.repos.FlatsRepository;
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
        final Flat flat = new Flat();
        mapToEntity(flatsDTO, flat);
        return flatsRepository.save(flat).getId();
    }

    public void update(final Long id, final FlatsDTO flatsDTO) {
        final Flat flat = flatsRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(flatsDTO, flat);
        flatsRepository.save(flat);
    }

    public void delete(final Long id) {
        flatsRepository.deleteById(id);
    }

    private FlatsDTO mapToDTO(final Flat flat, final FlatsDTO flatsDTO) {
        flatsDTO.setId(flat.getId());
        flatsDTO.setNumber(flat.getNumber());
        return flatsDTO;
    }

    private Flat mapToEntity(final FlatsDTO flatsDTO, final Flat flat) {
        flat.setNumber(flatsDTO.getNumber());
        return flat;
    }

}
