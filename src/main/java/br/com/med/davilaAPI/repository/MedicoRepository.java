package br.com.med.davilaAPI.repository;

import br.com.med.davilaAPI.dto.DadosAgendamentoConsulta;
import br.com.med.davilaAPI.model.Especialidade;
import br.com.med.davilaAPI.model.Medico;
import jakarta.validation.constraints.Future;
import jakarta.validation.constraints.NotNull;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface MedicoRepository extends JpaRepository<Medico, Long> {
    Page<Medico> findAllByAtivoTrue(Pageable paginacao);

    Medico findAllByAtivoTrue(Long id);

    @Query(value = "SELECT m FROM Medico m WHERE m.ativo = true and m.especialidade = :especialidade and m.id not in (select c.medico.id from Consulta c WHERE c.data = :data) order by random() limit 1")
    Medico escolherMedicoAleatorioLivreNaData(Especialidade especialidade, @NotNull @Future LocalDateTime data);
}
