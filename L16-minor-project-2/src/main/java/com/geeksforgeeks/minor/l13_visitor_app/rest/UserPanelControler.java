package com.geeksforgeeks.minor.l13_visitor_app.rest;

import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l13_visitor_app.service.UserPanelService;
import com.geeksforgeeks.minor.l13_visitor_app.service.UsersService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequestMapping("/api/user-panel")
public class UserPanelControler {

    private static Logger logger = LoggerFactory.getLogger(UserPanelControler.class);

    @Autowired
    private UserPanelService userPanelService;

    @Autowired
    private UsersService usersService;




    @PutMapping("/approve-visit/{id}")
    public ResponseEntity<Void> updateVisit(@PathVariable final Long id) {
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        logger.info("Request to approve  visit. userId: {} visitId:{}",userId,id);
        userPanelService.approveVisit(userId,id);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/reject-visit/{id}")
    public ResponseEntity<Void> rejectVisit(@PathVariable final Long id,
                                            @RequestHeader("userId") final Long userId) {
        userPanelService.rejectVisit(userId,id);
        return ResponseEntity.ok().build();
    }

    @GetMapping("/pending-visit")
    public  ResponseEntity<List<VisitDTO>> getPendingVisit() {
    /* userID related to request.
     http-thread which is executing this code.
    */
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        long userId = user.getId();
        logger.info("Request to fetch pending visits. userId {}",userId);

        List<VisitDTO> visitDTOList = userPanelService.getPendingVisits(userId);

        return ResponseEntity.ok(visitDTOList);
    }

    @GetMapping("/completed-visits")
    public  ResponseEntity<List<VisitDTO>> getAllVisit(@RequestHeader("userId") final Long userId) {
        logger.info("Request to fetch completed visits. userId {}",userId);
        return ResponseEntity.ok(userPanelService.getCompletedVisits(userId));
    }


    @GetMapping("/profile")
    public  ResponseEntity<UsersDTO> getProfile(@RequestHeader("userId") final Long userId) {
        logger.info("Request to get profile. userId {}",userId);
        return ResponseEntity.ok(usersService.get(userId));
    }


}
