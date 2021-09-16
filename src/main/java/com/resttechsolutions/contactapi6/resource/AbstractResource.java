package com.resttechsolutions.contactapi6.resource;

import com.resttechsolutions.contactapi6.resource.dto.Response;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

public abstract class AbstractResource <T, P>{

    @PostMapping(value = {"", "/"})
    public abstract ResponseEntity<Response> create(@RequestBody T t, @Autowired Response response);

    @GetMapping({"/{id}"})
    public abstract ResponseEntity<Response> findById(@PathVariable("id") P p, @Autowired Response response);

    @GetMapping({"","/"})
    public abstract ResponseEntity<Response> findAll(@Autowired Response response);

    @PutMapping({"","/"})
    public abstract ResponseEntity<Response> update(@RequestBody T t, @Autowired Response response);

    @DeleteMapping({"","{/id}"})
    public abstract ResponseEntity<Response> delete(@PathVariable("id") P p, @Autowired Response response);
}
