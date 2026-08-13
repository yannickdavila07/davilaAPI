package br.com.med.davilaAPI.model;

import br.com.med.davilaAPI.dto.DadosAtualizarMedico;
import br.com.med.davilaAPI.dto.DadosCadastroMedico;
import br.com.med.davilaAPI.dto.DadosEndereco;
import jakarta.persistence.*;
import lombok.*;

@Table(name = "medicos")
@Entity
@Getter
@Setter
@EqualsAndHashCode(of = "id")
@NoArgsConstructor
@AllArgsConstructor
public class Medico {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String nome;

    private String email;

    private String telefone;

    private String crm;

    private Boolean ativo = true;

    @Enumerated(EnumType.STRING)
    private Especialidade especialidade;

    @Embedded
    private Endereco endereco;

    public Medico(DadosCadastroMedico dto){
        this.nome = dto.nome();
        this.crm = dto.crm();
        this.telefone = dto.telefone();
        this.email = dto.email();
        this.especialidade = dto.especialidade();
        this.endereco = new Endereco(dto.endereco());
        this.ativo = true;
    }


    public void atualizarMedico(DadosAtualizarMedico dto){
        if (dto.nome() != null){
            this.nome = dto.nome();
        }
        if (dto.telefone() != null){
            this.telefone = dto.nome();
        }
        if (dto.endereco() != null){
            this.endereco.atualizarEndereco(dto.endereco());
        }
    }

    public void excluirMedico(){
        this.ativo = false;
    }

}

