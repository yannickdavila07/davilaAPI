package br.com.med.davilaAPI.repository;

import br.com.med.davilaAPI.model.Consulta;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository
public interface ConsultaRepository extends JpaRepository<Consulta, Long> {

    boolean existsByPacienteIdAndDataBetween(Long id, LocalDateTime primeiraData, LocalDateTime segundaData);

    boolean existsByMedicoIdAndData (Long id, LocalDateTime data);
}
