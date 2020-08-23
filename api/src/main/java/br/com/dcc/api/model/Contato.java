package br.com.dcc.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String telefone;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_contato", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "cliente_contato_fk")
    private Cliente cliente_contato = new Cliente();

    public Contato(){

    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getTelefone() {
        return telefone;
    }

    private void setTelefone(String telefone) {
        this.telefone = telefone;
    }

    public Cliente getCliente_contato() {
        return cliente_contato;
    }

    public void setCliente_contato(Cliente cliente_contato) {
        this.cliente_contato = cliente_contato;
    }
}
