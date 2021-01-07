package br.com.dcc.api.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Data
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
}
