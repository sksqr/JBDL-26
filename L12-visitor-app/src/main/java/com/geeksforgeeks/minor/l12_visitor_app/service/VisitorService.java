package com.geeksforgeeks.minor.l12_visitor_app.service;

import com.geeksforgeeks.minor.l12_visitor_app.domain.Address;
import com.geeksforgeeks.minor.l12_visitor_app.domain.Visitor;
import com.geeksforgeeks.minor.l12_visitor_app.model.VisitorDTO;
import com.geeksforgeeks.minor.l12_visitor_app.repos.AddressRepository;
import com.geeksforgeeks.minor.l12_visitor_app.repos.VisitRepository;
import com.geeksforgeeks.minor.l12_visitor_app.repos.VisitorRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class VisitorService {

    private final VisitorRepository visitorRepository;
    private final AddressRepository addressRepository;
    private final VisitRepository visitRepository;

    public VisitorService(final VisitorRepository visitorRepository,
            final AddressRepository addressRepository, final VisitRepository visitRepository) {
        this.visitorRepository = visitorRepository;
        this.addressRepository = addressRepository;
        this.visitRepository = visitRepository;
    }

    public List<VisitorDTO> findAll() {
        return visitorRepository.findAll()
                .stream()
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .collect(Collectors.toList());
    }

    public VisitorDTO get(final Long id) {
        return visitorRepository.findById(id)
                .map(visitor -> mapToDTO(visitor, new VisitorDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final VisitorDTO visitorDTO) {
        final Visitor visitor = new Visitor();
        mapToEntity(visitorDTO, visitor);
        return visitorRepository.save(visitor).getId();
    }

    public void update(final Long id, final VisitorDTO visitorDTO) {
        final Visitor visitor = visitorRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(visitorDTO, visitor);
        visitorRepository.save(visitor);
    }

    public void delete(final Long id) {
        visitorRepository.deleteById(id);
    }

    private VisitorDTO mapToDTO(final Visitor visitor, final VisitorDTO visitorDTO) {
        visitorDTO.setId(visitor.getId());
        visitorDTO.setName(visitor.getName());
        visitorDTO.setEmail(visitor.getEmail());
        visitorDTO.setPhone(visitor.getPhone());
        visitorDTO.setAddress(visitor.getAddress() == null ? null : visitor.getAddress().getId());
//        visitorDTO.setVisitor(visitor.getVisitor() == null ? null : visitor.getVisitor().getId());
        return visitorDTO;
    }

    private Visitor mapToEntity(final VisitorDTO visitorDTO, final Visitor visitor) {
        visitor.setName(visitorDTO.getName());
        visitor.setEmail(visitorDTO.getEmail());
        visitor.setPhone(visitorDTO.getPhone());
        if (visitorDTO.getAddress() != null && (visitor.getAddress() == null || !visitor.getAddress().getId().equals(visitorDTO.getAddress()))) {
            final Address address = addressRepository.findById(visitorDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            visitor.setAddress(address);
        }
//        if (visitorDTO.getVisitor() != null && (visitor.getVisitor() == null || !visitor.getVisitor().getId().equals(visitorDTO.getVisitor()))) {
//            final Visit visitor = visitRepository.findById(visitorDTO.getVisitor())
//                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "visitor not found"));
//            visitor.setVisitor(visitor);
//        }
        return visitor;
    }

}
