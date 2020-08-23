package br.com.dcc.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "endereco")
public class Endereco implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @NotNull
    private String rua;
    @NotNull
    private String bairro;
    private Number numero;
    @Column(name = "principal")
    private boolean isPrincipal;
    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_endereco", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "cliente_endereco_fk")
    private Cliente cliente_endereco;

    public Endereco(){

    }

    public Long getId() {
        return id;
    }

    private void setId(Long id) {
        this.id = id;
    }

    public String getRua() {
        return rua;
    }

    private void setRua(String rua) {
        this.rua = rua.toUpperCase();
    }

    public String getBairro() {
        return bairro;
    }

    private void setBairro(String bairro) {
        this.bairro = bairro.toUpperCase();
    }

    public Number getNumero() {
        return numero;
    }

    private void setNumero(Number numero) {
        this.numero = numero;
    }

    public boolean isPrincipal() {
        return isPrincipal;
    }

    private void setPrincipal(boolean principal) {
        isPrincipal = principal;
    }

    public Cliente getCliente_endereco() {
        return cliente_endereco;
    }

    public void setCliente_endereco(Cliente cliente_endereco) {
        this.cliente_endereco = cliente_endereco;
    }
}
