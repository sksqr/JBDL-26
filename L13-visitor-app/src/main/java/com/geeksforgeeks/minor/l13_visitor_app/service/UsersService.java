package com.geeksforgeeks.minor.l13_visitor_app.service;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Address;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Flat;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Role;
import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l13_visitor_app.repos.AddressRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.FlatsRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.RolesRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.UsersRepository;

import java.util.List;
import java.util.stream.Collectors;
import javax.annotation.PostConstruct;
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

    @PostConstruct
    public void init(){
        List<Role> roles = rolesRepository.findAll();
        if(roles.isEmpty()){
            Role admin = new Role();
            admin.setRole("ADMIN");
            rolesRepository.save(admin);

            Role resident = new Role();
            resident.setRole("RESIDENT");
            rolesRepository.save(resident);

            Role gateKeeper = new Role();
            gateKeeper.setRole("GATEKEEPER");
            rolesRepository.save(gateKeeper);

        }
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
        final User user = new User();
        mapToEntity(usersDTO, user);
        return usersRepository.save(user).getId();
    }

    public void update(final Long id, final UsersDTO usersDTO) {
        final User user = usersRepository.findById(id)
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND));
        mapToEntity(usersDTO, user);
        usersRepository.save(user);
    }

    public void delete(final Long id) {
        usersRepository.deleteById(id);
    }

    private UsersDTO mapToDTO(final User user, final UsersDTO usersDTO) {
        usersDTO.setId(user.getId());
        usersDTO.setName(user.getName());
        usersDTO.setEmail(user.getEmail());
        usersDTO.setPhone(user.getPhone());
        usersDTO.setUserRole(user.getUserRole() == null ? null : user.getUserRole().getId());
        usersDTO.setFlat(user.getFlat() == null ? null : user.getFlat().getId());
        usersDTO.setAddress(user.getAddress() == null ? null : user.getAddress().getId());
        return usersDTO;
    }

    private User mapToEntity(final UsersDTO usersDTO, final User user) {
        user.setName(usersDTO.getName());
        user.setEmail(usersDTO.getEmail());
        user.setPhone(usersDTO.getPhone());
        if (usersDTO.getUserRole() != null && (user.getUserRole() == null || !user.getUserRole().getId().equals(usersDTO.getUserRole()))) {
            final Role userRole = rolesRepository.findById(usersDTO.getUserRole())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "userRole not found"));
            user.setUserRole(userRole);
        }
        if (usersDTO.getFlat() != null && (user.getFlat() == null || !user.getFlat().getId().equals(usersDTO.getFlat()))) {
            final Flat flat = flatsRepository.findById(usersDTO.getFlat())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "flat not found"));
            user.setFlat(flat);
        }
        if (usersDTO.getAddress() != null && (user.getAddress() == null || !user.getAddress().getId().equals(usersDTO.getAddress()))) {
            final Address address = addressRepository.findById(usersDTO.getAddress())
                    .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "address not found"));
            user.setAddress(address);
        }
        return user;
    }

}
