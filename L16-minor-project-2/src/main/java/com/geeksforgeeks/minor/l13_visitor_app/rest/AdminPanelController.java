package com.geeksforgeeks.minor.l13_visitor_app.rest;

import com.geeksforgeeks.minor.l13_visitor_app.model.FlatsDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.UsersDTO;
import com.geeksforgeeks.minor.l13_visitor_app.model.VisitDTO;
import com.geeksforgeeks.minor.l13_visitor_app.service.FlatsService;
import com.geeksforgeeks.minor.l13_visitor_app.service.UsersService;
import org.apache.commons.csv.CSVFormat;
import org.apache.commons.csv.CSVParser;
import org.apache.commons.csv.CSVRecord;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.Valid;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/api/admin-panel")
public class AdminPanelController {

    @Autowired
    private UsersService usersService;


    @Autowired
    private FlatsService flatsService;

    private static Logger LOGGER = LoggerFactory.getLogger(AdminPanelController.class);

    static final String basePath = "/tmp";
    static final String relativePath = "/vms/";

    @PostMapping("/createUser")
    public ResponseEntity<Long> createUser(@RequestBody @Valid final UsersDTO usersDTO) {
        return new ResponseEntity<>(usersService.create(usersDTO), HttpStatus.CREATED);
    }

    @PostMapping("/createFlat")
    public ResponseEntity<Long> createFlat(@RequestBody @Valid final FlatsDTO flatsDTO) {
        return new ResponseEntity<>(flatsService.create(flatsDTO), HttpStatus.CREATED);
    }


    @GetMapping("/daily-report")
    public ResponseEntity<List<VisitDTO>> getAllVisitOfToday(){
        LOGGER.info("Received request to get all today's visitors");
        return ResponseEntity.status(HttpStatus.OK).build();
    }

    @PostMapping("/csv/upload")
    public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file, @RequestHeader Long userId) {
        //TODO: validation
        LOGGER.info("Received request to upload file:{}",file.getOriginalFilename());
        String message = "";
        try {
            BufferedReader fileReader = new BufferedReader(new InputStreamReader(file.getInputStream(), "UTF-8"));
            CSVParser csvParser = new CSVParser(fileReader,
                    CSVFormat.DEFAULT.withFirstRecordAsHeader().withIgnoreHeaderCase().withTrim());
            List<UsersDTO> usersDTOList = new ArrayList();
            Iterable<CSVRecord> csvRecords = csvParser.getRecords();
            for (CSVRecord csvRecord : csvRecords) {
                UsersDTO usersDTO = UsersDTO.builder().id(Long.parseLong(csvRecord.get("id")))
                        .name(csvRecord.get("name"))
                        .email(csvRecord.get("email"))
                        .phone(csvRecord.get("phone"))
                        .flat(Long.parseLong(csvRecord.get("flat")))
                        .address(Long.parseLong(csvRecord.get("address")))
                        .userRole(Long.parseLong(csvRecord.get("userRole"))).build();
                usersDTOList.add(usersDTO);
            }
            message = "Uploaded the file successfully: " + file.getOriginalFilename();

            return ResponseEntity.status(HttpStatus.OK).body(message);
        } catch (Exception e) {
            message = "Could not upload the file: " + file.getOriginalFilename() + "!";
            return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(message);
        }

    }
}
