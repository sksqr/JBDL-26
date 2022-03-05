package com.geeksforgeeks.minor.l13_visitor_app.service;

import com.geeksforgeeks.minor.l13_visitor_app.domain.Flat;
import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l13_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitEvent;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitStatus;
import com.geeksforgeeks.minor.l13_visitor_app.repos.FlatsRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@Service
public class GateKeeperPanelService {


    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private FlatsRepository flatsRepository;

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

    public List<User> getUsersByFlatNo(String flatNo){
        Flat flat = flatsRepository.findByNumber(flatNo);
        List<User> result = new ArrayList<>();
        if(flat != null){
            result.addAll(flat.getUsers());
        }
        return result;
    }



}
