package br.com.dcc.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Data
@Table(name = "contato")
public class Contato implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(name = "telefone")
    private String telefone;

    @JsonBackReference
    @ManyToOne
    @JoinColumn(name = "cliente_contato", nullable = false)
    @org.hibernate.annotations.ForeignKey(name = "cliente_contato_fk")
    private Cliente cliente_contato = new Cliente();
}
