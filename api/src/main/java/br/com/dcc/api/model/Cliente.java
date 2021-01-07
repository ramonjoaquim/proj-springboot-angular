package br.com.dcc.api.model;

import lombok.Data;
import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Data
@Table(name = "cliente")
public class Cliente implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @Column(nullable = false)
    private String nome;

    @Column(unique = true, nullable = false)
    private String cpfCnpj;

    @OneToMany(mappedBy = "cliente_endereco", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Endereco> enderecos = new ArrayList<>();

    @OneToMany(mappedBy = "cliente_contato", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Contato> contatos = new ArrayList<>();

    private void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getCpfCnpj() {
        return cpfCnpj.replaceAll("[^[0-9]*$]", "");
    }
}
