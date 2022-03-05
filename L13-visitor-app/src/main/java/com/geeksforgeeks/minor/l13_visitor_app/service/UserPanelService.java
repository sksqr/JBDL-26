package com.geeksforgeeks.minor.l13_visitor_app.service;

import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.domain.Visit;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitStatus;
import com.geeksforgeeks.minor.l13_visitor_app.repos.UsersRepository;
import com.geeksforgeeks.minor.l13_visitor_app.repos.VisitRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.InvalidParameterException;
import java.util.List;

@Service
public class UserPanelService {

    private static Logger LOGGER = LoggerFactory.getLogger(UserPanelService.class);

    @Autowired
    private VisitRepository visitRepository;

    @Autowired
    private UsersRepository usersRepository;

    @Autowired
    private VisitService visitService;

    public void rejectVisit(Long userId, Long visitId){
        if(!validateRequest(userId,visitId)){
            LOGGER.error("Invalid rejectVisit request userId: {} and visitId:{}",userId,visitId);
            throw new InvalidParameterException();
        }
        Visit visit = visitRepository.findById(visitId).get();
        visit.setStatus(VisitStatus.REJECTED);
        visitRepository.save(visit);
        LOGGER.error("visit {} rejected by user {}",visitId,userId);
    }

    public void approveVisit(Long userId, Long visitId){
        if(!validateRequest(userId,visitId)){
            LOGGER.error("Invalid approveVisit request userId: {} and visitId:{}",userId,visitId);
            throw new InvalidParameterException();
        }
        Visit visit = visitRepository.findById(visitId).get();
        visit.setStatus(VisitStatus.APPROVED);
        visitRepository.save(visit);
        LOGGER.error("visit {} accepted by user {}",visitId,userId);
    }

    public List<VisitDTO> getPendingVisits(Long userId){
        User user = usersRepository.findById(userId).get();
        return visitService.getVisitsByFlatAndStatus(user.getFlat(),VisitStatus.PENDING);

    }


    public List<VisitDTO> getCompletedVisits(Long userId){
        User user = usersRepository.findById(userId).get();
        return visitService.getVisitsByFlatAndStatus(user.getFlat(),VisitStatus.COMPLETED);
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
