package com.geeksforgeeks.minor.l12_visitor_app.rest;

import com.geeksforgeeks.minor.l12_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l12_visitor_app.service.UserPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/api/user-panel")
public class UserPanelControler {

    @Autowired
    private UserPanelService userPanelService;


    @PutMapping("/approve-visit/{id}")
    public ResponseEntity<Void> updateVisit(@PathVariable final Long id,
                                            @RequestHeader("userId") final Long userId) {
        userPanelService.approveVisit(userId,id);
        return ResponseEntity.ok().build();
    }


    @GetMapping("/pending-visit")
    public  ResponseEntity<List<VisitDTO>> getPendingVisit(@RequestHeader("userId") final Long userId) {
        return ResponseEntity.ok(userPanelService.getPendingVisits(userId));
    }

}
