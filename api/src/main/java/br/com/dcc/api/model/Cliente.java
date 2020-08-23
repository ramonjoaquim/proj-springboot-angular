package br.com.dcc.api.model;

import org.hibernate.annotations.Cascade;
import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
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
    private List<Endereco> enderecos;
    @OneToMany(mappedBy = "cliente_contato", orphanRemoval = true)
    @Cascade(org.hibernate.annotations.CascadeType.ALL)
    private List<Contato> contatos;

    public Cliente(){

    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    private void setNome(String nome) {
        this.nome = nome.toUpperCase();
    }

    public String getCpfCnpj() {
        String cpfFormat = cpfCnpj.replaceAll("[^[0-9]*$]", "");
        return cpfFormat;
    }

    private void setCpfCnpj(String cpfCnpj) {
        this.cpfCnpj = cpfCnpj;
    }

    public List<Endereco> getEnderecos() {
        if(enderecos == null)
            enderecos = new ArrayList<>();
        return enderecos;
    }

    public List<Contato> getContatos() {
        if(contatos == null)
            contatos = new ArrayList<>();
        return contatos;
    }

    public void setEnderecos(List<Endereco> enderecos) {
        this.enderecos = enderecos;
    }

    public void setContatos(List<Contato> contatos) {
        this.contatos = contatos;
    }

    public void addContato(Contato contato){
        getContatos().add(contato);
    }

    private void removeContato(Contato contato){
        getContatos().remove(contato);
    }

    private void addEndereco(Endereco endereco){
        getEnderecos().add(endereco);
    }

    private void removeEndereco(Endereco endereco){
        getEnderecos().remove(endereco);
    }
}
