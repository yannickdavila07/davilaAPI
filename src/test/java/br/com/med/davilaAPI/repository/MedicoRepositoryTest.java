package br.com.med.davilaAPI.repository;

import br.com.med.davilaAPI.dto.DadosCadastroMedico;
import br.com.med.davilaAPI.dto.DadosCadastroPaciente;
import br.com.med.davilaAPI.dto.DadosEndereco;
import br.com.med.davilaAPI.model.Consulta;
import br.com.med.davilaAPI.model.Especialidade;
import br.com.med.davilaAPI.model.Medico;
import br.com.med.davilaAPI.model.Paciente;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.data.jpa.test.autoconfigure.DataJpaTest;
import org.springframework.boot.jdbc.test.autoconfigure.AutoConfigureTestDatabase;
import org.springframework.boot.jpa.test.autoconfigure.TestEntityManager;
import org.springframework.test.context.ActiveProfiles;

import java.time.DayOfWeek;
import java.time.LocalDateTime;
import java.time.temporal.TemporalAdjuster;
import java.time.temporal.TemporalAdjusters;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class MedicoRepositoryTest {

    @Autowired
    private MedicoRepository repository;

    @Autowired
    private TestEntityManager em;


    @Test
    @DisplayName("Deveria devolver null quando o unico medico esta disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario1() {
        //GIVER OU ARRANGE
        var proximaSegundaAs10 = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        var paciente = cadastrarPaciente("Paciente", "paciente@gmail.com", "2221112222999");
        cadastrarConsulta(medico, paciente, proximaSegundaAs10);

        //WHEN OU ACT
        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //THEN OU ASSERT
        assertThat(medicoLivre).isNull();
    }

    @Test
    @DisplayName("Deveria devolver medico quando ele estiver disponivel na data")
    void escolherMedicoAleatorioLivreNaDataCenario2() {
        //GIVER OU ARRANGE
        var proximaSegundaAs10 = LocalDateTime.now().with(TemporalAdjusters.next(DayOfWeek.MONDAY)).toLocalDate().atTime(10, 0);
        var medico = cadastrarMedico("Medico", "medico@gmail.com", "123456", Especialidade.CARDIOLOGIA);
        //WHEN OU ACT

        var medicoLivre = repository.escolherMedicoAleatorioLivreNaData(Especialidade.CARDIOLOGIA, proximaSegundaAs10);

        //THEN OU ASSERT
        assertThat(medicoLivre).isEqualTo(medico);
    }


    private void cadastrarConsulta(Medico medico, Paciente paciente, LocalDateTime data) {
        em.persist(new Consulta(null, medico, paciente, data));
    }

    private Medico cadastrarMedico(String nome, String email, String crm, Especialidade especialidade) {
        var medico = new Medico(dadosMedico(nome, email, crm, especialidade));
        em.persist(medico);
        return medico;
    }

    private Paciente cadastrarPaciente(String nome, String email, String cpf) {
        var paciente = new Paciente(dadosPaciente(nome, email, cpf));
        em.persist(paciente);
        return paciente;
    }

    private DadosCadastroMedico dadosMedico(String nome, String email, String crm, Especialidade especialidade) {
        return new DadosCadastroMedico(
                nome,
                email,
                "61999999999",
                crm,
                especialidade,
                dadosEndereco()
        );
    }

    private DadosCadastroPaciente dadosPaciente(String nome, String email, String cpf) {
        return new DadosCadastroPaciente(
                nome,
                email,
                "61999999999",
                cpf,
                dadosEndereco()
        );
    }

    private DadosEndereco dadosEndereco() {
        return new DadosEndereco(
                "rua xpto",
                "bairro",
                "00000000",
                "Brasilia",
                "DF",
                null,
                null
        );
    }



}