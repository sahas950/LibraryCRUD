package com.library.crud.LibraryCrudM.controllers;

import java.util.*;

import javax.management.AttributeNotFoundException;
import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.library.crud.LibraryCrudM.bean.author;
import com.library.crud.LibraryCrudM.dao.AuthorDao;


@RestController
@RequestMapping("/api/authors")
public class AuthorController 
{
    @Autowired
    private AuthorDao authorRepository;

    @GetMapping
    public List<author> getAllAuthors() {
        return authorRepository.findAll();
    }

    @GetMapping("/{id}")
    public ResponseEntity<author> getAuthorById(@PathVariable(value = "id") Long authorId)
            throws AttributeNotFoundException {
        author author = authorRepository.findById(authorId);
        return ResponseEntity.ok().body(author);
    }

    @PostMapping
    public author createAuthor(@Valid @RequestBody author author) {
        return authorRepository.save(author);
    }

    @PutMapping("/{id}")
    public ResponseEntity<author> updateAuthor(@PathVariable(value = "id") Long authorId,
    		@Valid @RequestBody author authorDetails) throws AttributeNotFoundException {
        
    	author author = authorRepository.findById(authorId);
        author.setName(authorDetails.getName());
        author.setEmail(authorDetails.getEmail());
        final author updatedAuthor = authorRepository.save(author);
        return ResponseEntity.ok(updatedAuthor);
        
    }

    @DeleteMapping("/{id}")
    public Map<String, Boolean> deleteAuthor(@PathVariable(value = "id") Long authorId)
            throws AttributeNotFoundException {
    	
        author author = authorRepository.findById(authorId);
        authorRepository.delete(author);
        Map<String, Boolean> response = new HashMap<>();
        response.put("deleted", Boolean.TRUE);
        return response;
        
    }
}