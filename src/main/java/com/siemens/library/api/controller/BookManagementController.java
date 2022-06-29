package com.siemens.library.api.controller;

import com.siemens.library.api.entity.ResourceBook;
import com.siemens.library.api.model.LibraryResponse;
import com.siemens.library.api.model.RequestBodyToCreateBook;
import com.siemens.library.api.service.BookManagementService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.kaczmarzyk.spring.data.jpa.domain.Equal;
import net.kaczmarzyk.spring.data.jpa.web.annotation.And;
import net.kaczmarzyk.spring.data.jpa.web.annotation.Spec;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/library/api/v1")
@RequiredArgsConstructor
@Slf4j
public class BookManagementController {


    private final BookManagementService bookManagementService;

    @CrossOrigin(origins = "*")
    @PostMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> createNewBook(@RequestBody RequestBodyToCreateBook book) {
        try {
            LibraryResponse response = bookManagementService.createNewBook(book);
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
    @PutMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> modifyBook(@PathVariable String id, @RequestBody RequestBodyToCreateBook book) {
        try {
            LibraryResponse response = bookManagementService.modifyBook(id, book);
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
    @GetMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> getBooksById(@PathVariable String id) {
        try {
            LibraryResponse response = bookManagementService.findBookById(id);
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
    @DeleteMapping(value = "/books/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> deleteBooks(@PathVariable String id) {
        try {
            LibraryResponse response = bookManagementService.deleteBookById(id);
            return new ResponseEntity<>(response.getData(), HttpStatus.NO_CONTENT);
        } catch (Exception ex) {
            log.error("Exception on ", ex);
            return new ResponseEntity<>("Service Error " + ex.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @CrossOrigin(origins = "*")
    @GetMapping(value = "/books", consumes = MediaType.APPLICATION_JSON_VALUE)
    public ResponseEntity<?> queryBooks(@RequestParam(value = "isbn", defaultValue = "") String isbn,
                                        @RequestParam(value = "title", defaultValue = "") String title,
                                        @RequestParam(value = "author", defaultValue = "") String author,
                                        @RequestParam(value = "category", defaultValue = "") String category,
                                        @RequestParam(value = "available", defaultValue = "false") boolean available) {
        try {
            LibraryResponse response = bookManagementService.queryBooks(isbn,title,author,category,available);
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
