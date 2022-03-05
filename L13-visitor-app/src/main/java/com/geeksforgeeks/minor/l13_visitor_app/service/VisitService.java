package com.geeksforgeeks.minor.l13_visitor_app.service;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Flat;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Visitor;
import com.geeksforgeeks.minor.l13_visitor_app.model.CreateVisitRequestDto;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitStatus;
import com.geeksforgeeks.minor.l13_visitor_app.repos.FlatsRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.VisitRepository;

import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import com.geeksforgeeks.minor.l13_visitor_app.repos.VisitorRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VisitService {

    private final VisitRepository visitRepository;
    private final FlatsRepository flatsRepository;
    private final VisitorRepository visitorRepository;

    public VisitService(final VisitRepository visitRepository,
            final FlatsRepository flatsRepository, final VisitorRepository visitorRepository) {
        this.visitRepository = visitRepository;
        this.flatsRepository = flatsRepository;
        this.visitorRepository = visitorRepository;
    }

    public List<VisitDTO> findAll() {
        return visitRepository.findAll()
                .stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .collect(Collectors.toList());
    }

    public VisitDTO get(final Long id) {
        return visitRepository.findById(id)
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VisitDTO visitDTO) {
        final Visit visit = new Visit();
        mapToEntity(visitDTO, visit);
        return visitRepository.save(visit).getId();
    }

    public Long create(final CreateVisitRequestDto createVisitRequestDto) {
        final Visit visit = new Visit();
        visit.setVisitor(visitorRepository.findById(createVisitRequestDto.getVisitorId()).get());
        visit.setFlat(flatsRepository.findById(createVisitRequestDto.getFlat()).get());
        visit.setStatus(VisitStatus.PENDING);
        visit.setPurpose(createVisitRequestDto.getPurpose());
        visit.setImageUrl(createVisitRequestDto.getImageUrl());
        return visitRepository.save(visit).getId();
    }


    public void update(final Long id, final VisitDTO visitDTO) {
        final Visit visit = visitRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(visitDTO, visit);
        visitRepository.save(visit);
    }

    public void delete(final Long id) {
        visitRepository.deleteById(id);
    }

    private VisitDTO mapToDTO(final Visit visit, final VisitDTO visitDTO) {
        visitDTO.setId(visit.getId());
        visitDTO.setStatus(visit.getStatus());
        visitDTO.setInTime(visit.getInTime());
        visitDTO.setOutTime(visit.getOutTime());
        visitDTO.setPurpose(visit.getPurpose());
        visitDTO.setNoOfPeople(visit.getNoOfPeople());
        visitDTO.setImageUrl(visit.getImageUrl());
        visitDTO.setFlat(visit.getFlat() == null ? null : visit.getFlat().getId());
        visitDTO.setVisitorId(visit.getVisitor() == null ? null : visit.getVisitor().getId());
        return visitDTO;
    }



    private Visit mapToEntity(final VisitDTO visitDTO, final Visit visit) {
        visit.setStatus(visitDTO.getStatus());
        visit.setInTime(visitDTO.getInTime());
        visit.setOutTime(visitDTO.getOutTime());
        visit.setPurpose(visitDTO.getPurpose());
        visit.setNoOfPeople(visitDTO.getNoOfPeople());
        visit.setImageUrl(visitDTO.getImageUrl());
        if (visitDTO.getFlat() != null && (visit.getFlat() == null || !visit.getFlat().getId().equals(visitDTO.getFlat()))) {
            final Flat flat = flatsRepository.findById(visitDTO.getFlat())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flat not found"));
            visit.setFlat(flat);
        }
        if (visitDTO.getVisitorId() != null && (visit.getVisitor() == null || !visit.getVisitor().getId().equals(visitDTO.getVisitorId()))) {
            final Visitor visitor = visitorRepository.findById(visitDTO.getFlat())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flat not found"));
            visit.setVisitor(visitor);
        }
        return visit;
    }

    public List<VisitDTO> getVisitsByFlatAndStatus(Flat flat, VisitStatus status){
        return visitRepository.findByflatAndStatus(flat, status).stream()
                .map(visit -> mapToDTO(visit, new VisitDTO()))
                .collect(Collectors.toList());

    }

}
