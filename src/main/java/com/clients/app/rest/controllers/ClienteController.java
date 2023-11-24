package com.clients.app.rest.controllers;

import com.clients.app.rest.request.ClienteRequest;
import com.clients.app.rest.response.ClienteResponse;
import com.clients.domain.service.ClientService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@Validated
@RestController
@RequestMapping(path = "/cliente")
public class ClienteController {
    @Autowired
    private ClientService service;

    @PostMapping(path = "", produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<ClienteResponse> crea(
            @Valid @RequestBody ClienteRequest request
    ){
        return new ResponseEntity<>(service.crea(request), HttpStatus.CREATED);
    }
}
