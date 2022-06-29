package com.siemens.library.api.controller;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateMember;
import com.siemens.library.api.service.MembershipService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/v1")
@RequiredArgsConstructor
@Slf4j
public class MembershipController {

    private final MembershipService membershipService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/members",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewMember(@RequestBody RequestBodyToCreateMember member) {
        try {
            LibraryResponse response = membershipService.createNewMember(member);
            if (response.getError() != null) {
                return new ResponseEntity<>(response.getError(), response.getStatus());
            }
            return new ResponseEntity<>(response.getData(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @DeleteMapping(value = "/members/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteMembers(@PathVariable String id ) {
        try {
            LibraryResponse response = membershipService.deleteBookById(id);
            return new ResponseEntity<>(response.getData(), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/members/{id}",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getMembersById(@PathVariable String id ) {
        try {
            LibraryResponse response = membershipService.findMemberById(id);
            if (response.getError() != null) {
                return new ResponseEntity<>(response.getError(), response.getStatus());
            }
            return new ResponseEntity<>(response.getData(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @CrossOrigin(origins = "*")
    @GetMapping(value = "/members", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryMembers(@RequestParam(value = "name", defaultValue = "") String name,
                                        @RequestParam(value = "surname", defaultValue = "") String surname,
                                        @RequestParam(value = "email", defaultValue = "") String email,
                                        @RequestParam(value = "phoneNumber", defaultValue = "") String phoneNumber){
        try {
            LibraryResponse response = membershipService.queryBooks(name,surname,email,phoneNumber);
            if (response.getError() != null) {
                return new ResponseEntity<>(response.getError(), response.getStatus());
            }
            return new ResponseEntity<>(response.getData(), HttpStatus.OK);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
