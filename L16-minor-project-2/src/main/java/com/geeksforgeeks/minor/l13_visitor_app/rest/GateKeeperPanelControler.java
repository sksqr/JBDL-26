package com.geeksforgeeks.minor.l13_visitor_app.rest;

import com.geeksforgeeks.minor.l13_visitor_app.domain.User;
import com.geeksforgeeks.minor.l13_visitor_app.model.*;
import com.geeksforgeeks.minor.l13_visitor_app.service.GateKeeperPanelService;
import com.geeksforgeeks.minor.l13_visitor_app.service.VisitService;
import com.geeksforgeeks.minor.l13_visitor_app.service.VisitorService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.File;
import java.util.List;

@RestController
@RequestMapping("/api/gatekeeper-panel/")
public class GateKeeperPanelControler {


    static final String basePath = "/tmp";
    static final String relativePath = "/vms/";

    @Autowired
    private GateKeeperPanelService gateKeeperPanelService;

    @Autowired
    private VisitorService visitorService;

    @Autowired
    private VisitService visitService;

    @PostMapping("/visit-event/{id}/{event}")
    public ResponseEntity<Void> updateVisit(@PathVariable final Long id,
                                            @RequestHeader("userId") final Long userId,@PathVariable VisitEvent event) {
        gateKeeperPanelService.recordVisitEvent(id,userId,event);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/image/upload")
    public ResponseEntity<String> uploadImage(@RequestParam("file") MultipartFile file) {

        String message = "";
        try {
            String path = relativePath+"testfile_"+System.currentTimeMillis()+"_"+file.getOriginalFilename();
            String uploadPath = basePath+path;
            file.transferTo(new File(uploadPath));
            message = "Image URL : " + path;
            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }

    @GetMapping("/get-visitor")
    public ResponseEntity<VisitorDTO> getVisitor(@RequestParam("phone") final String phone) {
        return ResponseEntity.ok(visitorService.getByPhone(phone));
    }

    @GetMapping("/get-users")
    public ResponseEntity<List<User>> getUsers(@RequestParam("flatNo") final String flatNo) {
        return ResponseEntity.ok(gateKeeperPanelService.getUsersByFlatNo(flatNo));
    }


    @PostMapping("/create-visitor")
    public ResponseEntity<Long> createVisitor(@RequestBody @Valid final VisitorDTO visitorDTO) {
        return new ResponseEntity<>(visitorService.create(visitorDTO), HttpStatus.CREATED);
    }

    @PutMapping("/update-visitor/{id}")
    public ResponseEntity<Void> updateVisitor(@PathVariable final Long id,
                                              @RequestBody @Valid final VisitorDTO visitorDTO) {
        visitorService.update(id, visitorDTO);
        return ResponseEntity.ok().build();
    }


    @PostMapping("/create-visit")
    public ResponseEntity<Long> createVisit(@RequestBody @Valid final CreateVisitRequestDto createVisitRequestDto) {

        return new ResponseEntity<>(visitService.create(createVisitRequestDto), HttpStatus.CREATED);
    }

}
