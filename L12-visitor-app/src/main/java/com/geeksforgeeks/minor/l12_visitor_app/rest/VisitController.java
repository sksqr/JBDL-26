package com.geeksforgeeks.minor.l12_visitor_app.rest;

import com.geeksforgeeks.minor.l12_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l12_visitor_app.service.VisitService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(value = "/api/visits", produces = MediaType.APPLICATION_JSON_VALUE)
public class VisitController {

    private final VisitService visitService;

    public VisitController(final VisitService visitService) {
        this.visitService = visitService;
    }

    @GetMapping
    public ResponseEntity<List<VisitDTO>> getAllVisits() {
        return ResponseEntity.ok(visitService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<VisitDTO> getVisit(@PathVariable final Long id) {
        return ResponseEntity.ok(visitService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createVisit(@RequestBody @Valid final VisitDTO visitDTO, @RequestHeader("userId") final Long userId) {
        return new ResponseEntity<>(visitService.create(visitDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateVisit(@PathVariable final Long id,
            @RequestHeader("userId") final Long userId,
            @RequestBody @Valid final VisitDTO visitDTO) {
        visitService.update(id, visitDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteVisit(@PathVariable final Long id) {
        visitService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
