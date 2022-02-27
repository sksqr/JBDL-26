package com.geeksforgeeks.minor.l12_visitor_app.rest;

import com.geeksforgeeks.minor.l12_visitor_app.model.FlatsDTO;
import com.geeksforgeeks.minor.l12_visitor_app.service.FlatsService;
import java.util.List;
import javax.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@RequestMapping(value = "/api/flatss", produces = MediaType.APPLICATION_JSON_VALUE)
public class FlatsController {

    private final FlatsService flatsService;

    public FlatsController(final FlatsService flatsService) {
        this.flatsService = flatsService;
    }

    @GetMapping
    public ResponseEntity<List<FlatsDTO>> getAllFlatss() {
        return ResponseEntity.ok(flatsService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlatsDTO> getFlats(@PathVariable final Long id) {
        return ResponseEntity.ok(flatsService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createFlats(@RequestBody @Valid final FlatsDTO flatsDTO) {
        return new ResponseEntity<>(flatsService.create(flatsDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFlats(@PathVariable final Long id,
            @RequestBody @Valid final FlatsDTO flatsDTO) {
        flatsService.update(id, flatsDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlats(@PathVariable final Long id) {
        flatsService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
