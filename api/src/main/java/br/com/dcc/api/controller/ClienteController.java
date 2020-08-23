package br.com.dcc.api.controller;

import br.com.dcc.api.model.Cliente;
import br.com.dcc.api.service.ClienteService;
import br.com.dcc.api.utils.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(value="/api/cliente", produces = MediaType.APPLICATION_JSON_VALUE)
public class ClienteController{
    @Autowired
    ClienteService service;

    @GetMapping
    public List<Cliente> getClientes() {
        return service.getClientes();
    }

    @PostMapping
    public ResponseEntity AddCliente(@RequestBody Cliente cliente) {
        ValidationResult response = service.add(cliente);
            if(!response.getError())
                return ResponseEntity
                        .status(HttpStatus.CREATED)
                        .build();
            else
                return ResponseEntity
                        .status(HttpStatus.BAD_REQUEST)
                        .body(response);
    }

    @Transactional
    @PutMapping("/{id}")
    public ResponseEntity Update(@PathVariable(required = true) long id, @RequestBody Cliente cliente){
        ValidationResult response = service.update(id, cliente);
        if(!response.getError())
            return ResponseEntity
                    .ok()
                    .build();
        else
            return ResponseEntity
                    .status(HttpStatus.BAD_REQUEST)
                    .body(response);
    }

    @GetMapping("/{id}")
    public ResponseEntity getById(@PathVariable(required = true) long id) {
        ValidationResult response = service.getClienteById(id);
        if(!response.getError())
            return ResponseEntity
                    .status(HttpStatus.OK)
                    .body(response);
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();

    }

    @DeleteMapping("/{id}")
    public ResponseEntity delete(@PathVariable(required = true) long id) {
        ValidationResult response = service.delete(id);
        if(!response.getError())
            return ResponseEntity
                    .status(HttpStatus.NO_CONTENT)
                    .build();
        else
            return ResponseEntity
                    .status(HttpStatus.NOT_FOUND)
                    .build();
    }
}