package com.geeksforgeeks.minor.l11_visitor_app.rest;

import com.geeksforgeeks.minor.l11_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l11_visitor_app.service.UsersService;
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
@RequestMapping(value = "/api/userss", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsersController {

    private final UsersService usersService;

    public UsersController(final UsersService usersService) {
        this.usersService = usersService;
    }

    @GetMapping
    public ResponseEntity<List<UsersDTO>> getAllUserss() {
        return ResponseEntity.ok(usersService.findAll());
    }

    @GetMapping("/{id}")
    public ResponseEntity<UsersDTO> getUsers(@PathVariable final Long id) {
        return ResponseEntity.ok(usersService.get(id));
    }

    @PostMapping
    public ResponseEntity<Long> createUsers(@RequestBody @Valid final UsersDTO usersDTO) {
        return new ResponseEntity<>(usersService.create(usersDTO), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateUsers(@PathVariable final Long id,
            @RequestBody @Valid final UsersDTO usersDTO) {
        usersService.update(id, usersDTO);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUsers(@PathVariable final Long id) {
        usersService.delete(id);
        return ResponseEntity.noContent().build();
    }

}
