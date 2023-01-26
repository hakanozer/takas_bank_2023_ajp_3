package com.works.restcontrollers;

import com.works.entities.Note;
import com.works.repositories.NoteRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

@RestController
@RequestMapping("/note")
@RequiredArgsConstructor
public class NoteRestController {

    final NoteRepository repository;

    @PostMapping("/save")
    public ResponseEntity save(@RequestBody Note note) {
        Map hm = new LinkedHashMap();
        repository.save(note);
        hm.put("status", true);
        hm.put("result", note);
        return new ResponseEntity(hm, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity list() {
        Map hm = new LinkedHashMap();
        hm.put("status", true);
        hm.put("result", repository.findAll());
        return new ResponseEntity(hm, HttpStatus.OK);
    }

}
