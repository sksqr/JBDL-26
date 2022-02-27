package com.geeksforgeeks.minor.l12_visitor_app.rest;

import com.geeksforgeeks.minor.l12_visitor_app.model.VisitEvent;
import com.geeksforgeeks.minor.l12_visitor_app.service.GateKeeperPanelService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;

@RestController
@RequestMapping("/api/gatekeeper-panel/")
public class GateKeeperPanelControler {


    static final String basePath = "/tmp";
    static final String relativePath = "/vms/";

    @Autowired
    private GateKeeperPanelService gateKeeperPanelService;

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

}
