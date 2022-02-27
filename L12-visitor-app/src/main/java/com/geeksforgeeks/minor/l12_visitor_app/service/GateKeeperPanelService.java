package com.geeksforgeeks.minor.l12_visitor_app.service;

import com.geeksforgeeks.minor.l12_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l12_visitor_app.model.VisitEvent;
import com.geeksforgeeks.minor.l12_visitor_app.model.VisitStatus;
import com.geeksforgeeks.minor.l12_visitor_app.repos.VisitRepository;
import com.geeksforgeeks.minor.l12_visitor_app.repos.VisitorRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.time.LocalDateTime;

@Service
public class GateKeeperPanelService {


    @Autowired
    private VisitRepository visitRepository;

    public void recordVisitEvent(Long visitId, Long userId, VisitEvent visitEvent){
        // TODO: Validation
        Visit visit = visitRepository.findById(visitId).get();
        if(visitEvent.equals(VisitEvent.ENTRY)){
            visit.setInTime(LocalDateTime.now());
        }
        else {
            visit.setOutTime(LocalDateTime.now());
            visit.setStatus(VisitStatus.COMPLETED);
        }
        visitRepository.save(visit);
    }



}
