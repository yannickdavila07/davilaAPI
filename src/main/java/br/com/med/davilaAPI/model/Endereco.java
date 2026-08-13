package br.com.med.davilaAPI.model;

import br.com.med.davilaAPI.dto.DadosAtualizarMedico;
import br.com.med.davilaAPI.dto.DadosCadastroPaciente;
import br.com.med.davilaAPI.dto.DadosEndereco;
import jakarta.persistence.Embeddable;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Embeddable
@Getter
public class Endereco {

    private String logradouro;

    private String numero;

    private String complemento;

    private String bairro;

    private String cidade;

    private String uf;

    private String cep;

    public Endereco(DadosEndereco dto){
        this.logradouro = dto.logradouro();
        this.bairro = dto.bairro();
        this.numero = dto.numero();
        this.complemento = dto.complemento();
        this.cidade = dto.cidade();
        this.uf = dto.uf();
        this.cep = dto.cep();

    }

    public void atualizarEndereco(DadosEndereco dto){
        if (dto.logradouro() != null){
            this.logradouro = dto.logradouro();
        }

        if (dto.bairro() != null){
            this.bairro = dto.bairro();
        }

        if (dto.cep() != null){
            this.cep = dto.cep();
        }

        if (dto.cidade() != null){
            this.cidade = dto.cidade();
        }

        if (dto.complemento() != null){
            this.complemento = dto.complemento();
        }

        if (dto.uf() != null){
            this.uf = dto.uf();
        }

        if (dto.numero() != null){
            this.numero = dto.numero();
        }
    }


}
