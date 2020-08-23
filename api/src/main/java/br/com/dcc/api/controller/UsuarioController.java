package br.com.dcc.api.controller;

import br.com.dcc.api.model.Usuario;
import br.com.dcc.api.service.UsuarioService;
import br.com.dcc.api.utils.ValidationResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/usuario", produces = MediaType.APPLICATION_JSON_VALUE)
public class UsuarioController {

    @Autowired
    UsuarioService service;

    @PostMapping("/registrar")
    public ResponseEntity addUsuario(@RequestBody Usuario usuario) {
        ValidationResult response = service.add(usuario);
        if(!response.getError())
            return ResponseEntity
                    .status(HttpStatus.CREATED)
                    .build();
        else
            return ResponseEntity
                    .status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body(response);
    }
}