package com.geeksforgeeks.minor.l11_visitor_app.service;

import com.geeksforgeeks.minor.l11_visitor_app.domain.Address;
import com.geeksforgeeks.minor.l11_visitor_app.domain.Flats;
import com.geeksforgeeks.minor.l11_visitor_app.domain.Roles;
import com.geeksforgeeks.minor.l11_visitor_app.domain.Users;
import com.geeksforgeeks.minor.l11_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l11_visitor_app.repos.AddressRepository;
import com.geeksforgeeks.minor.l11_visitor_app.repos.FlatsRepository;
import com.geeksforgeeks.minor.l11_visitor_app.repos.RolesRepository;
import com.geeksforgeeks.minor.l11_visitor_app.repos.UsersRepository;
import java.util.List;
import java.util.stream.Collectors;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;


@Service
public class UsersService {

    private final UsersRepository usersRepository;
    private final RolesRepository rolesRepository;
    private final FlatsRepository flatsRepository;
    private final AddressRepository addressRepository;

    public UsersService(final UsersRepository usersRepository,
            final RolesRepository rolesRepository, final FlatsRepository flatsRepository,
            final AddressRepository addressRepository) {
        this.usersRepository = usersRepository;
        this.rolesRepository = rolesRepository;
        this.flatsRepository = flatsRepository;
        this.addressRepository = addressRepository;
    }

    public List<UsersDTO> findAll() {
        return usersRepository.findAll()
                .stream()
                .map(users -> mapToDTO(users, new UsersDTO()))
                .collect(Collectors.toList());
    }

    public UsersDTO get(final Long id) {
        return usersRepository.findById(id)
                .map(users -> mapToDTO(users, new UsersDTO()))
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
    }

    public Long create(final UsersDTO usersDTO) {
        final Users users = new Users();
        mapToEntity(usersDTO, users);
        return usersRepository.save(users).getId();
    }

    public void update(final Long id, final UsersDTO usersDTO) {
        final Users users = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(usersDTO, users);
        usersRepository.save(users);
    }

    public void delete(final Long id) {
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDTO(final Users users, final UsersDTO usersDTO) {
        usersDTO.setId(users.getId());
        usersDTO.setName(users.getName());
        usersDTO.setEmail(users.getEmail());
        usersDTO.setPhone(users.getPhone());
        usersDTO.setUserRole(users.getUserRole() == null ? null : users.getUserRole().getId());
        usersDTO.setFlat(users.getFlat() == null ? null : users.getFlat().getId());
        usersDTO.setAddress(users.getAddress() == null ? null : users.getAddress().getId());
        return usersDTO;
    }

    private Users mapToEntity(final UsersDTO usersDTO, final Users users) {
        users.setName(usersDTO.getName());
        users.setEmail(usersDTO.getEmail());
        users.setPhone(usersDTO.getPhone());
        if (usersDTO.getUserRole() != null && (users.getUserRole() == null || !users.getUserRole().getId().equals(usersDTO.getUserRole()))) {
            final Roles userRole = rolesRepository.findById(usersDTO.getUserRole())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userRole not found"));
            users.setUserRole(userRole);
        }
        if (usersDTO.getFlat() != null && (users.getFlat() == null || !users.getFlat().getId().equals(usersDTO.getFlat()))) {
            final Flats flat = flatsRepository.findById(usersDTO.getFlat())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flat not found"));
            users.setFlat(flat);
        }
        if (usersDTO.getAddress() != null && (users.getAddress() == null || !users.getAddress().getId().equals(usersDTO.getAddress()))) {
            final Address address = addressRepository.findById(usersDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            users.setAddress(address);
        }
        return users;
    }

}
