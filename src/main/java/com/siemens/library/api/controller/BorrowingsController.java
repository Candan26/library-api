package com.siemens.library.api.controller;

import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToBorrowBook;
import com.siemens.library.api.model.RequestBodyToCreateBook;
import com.siemens.library.api.model.RequestBodyToReturnBook;
import com.siemens.library.api.service.BookManagementService;
import com.siemens.library.api.service.BorrowBookService;
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
public class BorrowingsController {

    private final BorrowBookService borrowBookService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/borrowings",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBorrowing(@RequestBody RequestBodyToBorrowBook request) {
        try {
            LibraryResponse response = borrowBookService.createNewBorrowing(request);
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
    @PostMapping(value = "/borrowings/{id}/return",consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createBorrowing(@PathVariable String id, RequestBodyToReturnBook request) {
        try {
            LibraryResponse response = borrowBookService.returnBookInformation(id,request);
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
