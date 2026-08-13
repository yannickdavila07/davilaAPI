package br.com.med.davilaAPI.service;

import br.com.med.davilaAPI.dto.DadosAtualizarMedico;
import br.com.med.davilaAPI.dto.DadosCadastroMedico;
import br.com.med.davilaAPI.dto.DadosDetalhamentoMedico;
import br.com.med.davilaAPI.dto.DadosListagemMedico;
import br.com.med.davilaAPI.model.Medico;
import br.com.med.davilaAPI.repository.MedicoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponentsBuilder;

@Service
public class MedicoService {
    @Autowired
    private MedicoRepository repository;

    public Medico cadastrarMedico(DadosCadastroMedico dto){
        var medico = new Medico(dto);
        repository.save(medico);
        return medico;

    }

    public Page<DadosListagemMedico> listarMedicos(Pageable paginacao) {
        return repository.findAllByAtivoTrue(paginacao).map(DadosListagemMedico::new);
    }

    public DadosDetalhamentoMedico atualizarMedico(DadosAtualizarMedico dto){
        var medico = repository.getReferenceById(dto.id());
        medico.atualizarMedico(dto);
        return new DadosDetalhamentoMedico(medico);

    }


    public DadosDetalhamentoMedico detalharMedico(Long id) {
        var medico = repository.findById(id).get();
        return new DadosDetalhamentoMedico(medico);
    }

    public void deletarMedico(Long id) {
        var medico = repository.getReferenceById(id);
        medico.excluirMedico();
    }
}
