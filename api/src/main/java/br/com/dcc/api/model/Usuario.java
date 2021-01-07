package br.com.dcc.api.model;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
@Table(name = "usuario")
public class Usuario implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    public Long id;

    @NotNull
    public String login;

    @NotNull
    public String senha;
}
