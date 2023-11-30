package com.clients.app.rest.controllers;

import com.clients.app.rest.request.ClienteRequest;
import com.clients.app.rest.response.ClienteResponse;
import com.clients.domain.service.ProduceMensaje;
import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Slf4j
@Validated
@RestController
@RequestMapping(path = "/kafka")
public class MensajeController {
    @Autowired
    private ProduceMensaje produceMensaje;

    @GetMapping(path = "/{mensaje}",  produces = {MediaType.APPLICATION_JSON_VALUE })
    public ResponseEntity<String> crea(@PathVariable() String mensaje){
        produceMensaje.envia(mensaje);
        return new ResponseEntity<>("Mensaje entregado", HttpStatus.OK);
    }

}
