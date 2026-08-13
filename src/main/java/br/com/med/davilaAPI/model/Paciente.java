package br.com.med.davilaAPI.model;

import br.com.med.davilaAPI.dto.DadosAtualizarPaciente;
import br.com.med.davilaAPI.dto.DadosCadastroPaciente;
import br.com.med.davilaAPI.dto.DadosEndereco;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "pacientes")
@NoArgsConstructor
@AllArgsConstructor
@EqualsAndHashCode(of = "id")
@Getter
public class Paciente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String cpf;

    private Boolean ativo = true;

    @Embedded
    private Endereco endereco;


    public Paciente(DadosCadastroPaciente dto){
        this.nome = dto.nome();
        this.cpf = dto.cpf();
        this.email = dto.email();
        this.telefone = dto.telefone();
        this.endereco = new Endereco(dto.endereco());
        this.ativo = true;
    }

    public void atualizarPaciente(DadosAtualizarPaciente dto){
        if (dto.nome() != null){
            this.nome = dto.nome();
        }

        if (dto.telefone() != null){
            this.telefone = dto.telefone();
        }

        if (dto.endereco() != null){
            endereco.atualizarEndereco(dto.endereco());
        }

    }

    public void deletarPaciente(){
        this.ativo = false;
    }

}
