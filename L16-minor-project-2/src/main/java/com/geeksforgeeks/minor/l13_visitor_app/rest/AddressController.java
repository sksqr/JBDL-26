package com.geeksforgeeks.minor.l13_visitor_app.rest;

import com.geeksforgeeks.minor.l13_visitor_app.model.AddressDTO;
import com.geeksforgeeks.minor.l13_visitor_app.service.AddressService;
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
@RequestMapping(value = "/api/addresss", produces = MediaType.APPLICATION_JSON_VALUE)
public class AddressController {

    private final AddressService addressService;

    public AddressController(final AddressService addressService) {
        this.addressService = addressService;
    }

//    @GetMapping
    public ResponseEntity<List<AddressDTO>> getAllAddresss() {
        return ResponseEntity.ok(addressService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<AddressDTO> getAddress(@PathVariable final Long id) {
        return ResponseEntity.ok(addressService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createAddress(@RequestBody @Valid final AddressDTO addressDTO) {
        return new ResponseEntity<>(addressService.create(addressDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateAddress(@PathVariable final Long id,
            @RequestBody @Valid final AddressDTO addressDTO) {
        addressService.update(id, addressDTO);
        return ResponseEntity.ok().build();
    }

//    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable final Long id) {
        addressService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
