package com.geeksforgeeks.minor.l12_visitor_app.service;

import com.geeksforgeeks.minor.l12_visitor_app.domain.User;
import com.geeksforgeeks.minor.l12_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l12_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l12_visitor_app.model.VisitStatus;
import com.geeksforgeeks.minor.l12_visitor_app.repos.UsersRepository;
import com.geeksforgeeks.minor.l12_visitor_app.repos.VisitRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserPanelService {

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VisitService visitService;

    public void approveVisit(Long userId, Long visitId){
        if(!validateRequest(userId,visitId)){
            throw new InvalidParameterException();
        }
        Visit visit = visitRepository.findById(visitId).get();
        visit.setStatus(VisitStatus.APPROVED);
        visitRepository.save(visit);
    }

    public List<VisitDTO> getPendingVisits(Long userId){
        User user = usersRepository.findById(userId).get();
        return visitService.getVisitsByFlat(user.getFlat());
    }

    private boolean validateRequest(Long userId, Long visitId){
        Visit visit = visitRepository.findById(visitId).get();
        User user = usersRepository.findById(userId).get();
        if(user!=null && visit != null){
            if(user.getFlat().getId() == visit.getFlat().getId()){
                return true;
            }
            else {
                return false;
            }
        }
        return false;
    }



}
