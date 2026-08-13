package br.com.med.davilaAPI.controller;

import br.com.med.davilaAPI.dto.DadosCadastroMedico;
import br.com.med.davilaAPI.dto.DadosDetalhamentoMedico;
import br.com.med.davilaAPI.dto.DadosEndereco;
import br.com.med.davilaAPI.model.Endereco;
import br.com.med.davilaAPI.model.Especialidade;
import br.com.med.davilaAPI.model.Medico;
import br.com.med.davilaAPI.service.MedicoService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.webmvc.test.autoconfigure.AutoConfigureMockMvc;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.context.bean.override.mockito.MockitoBean;
import org.springframework.test.web.servlet.MockMvc;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureJsonTesters
@AutoConfigureMockMvc
class MedicoControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<DadosCadastroMedico> dadosCadastroMedicoJson;

    @Autowired
    private JacksonTester<DadosDetalhamentoMedico> dadosDetalhamentoMedicoJason;

    @MockitoBean
    private MedicoService medicoService;



    @Test
    @DisplayName("Deve devolver codigo 400 quando informacoes estao invalidas")
    @WithMockUser
    void cadastrarMedico_cenario1() throws Exception{

        var response = mvc.perform(post("/medicos")).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria retoranar codigo 201 CREATED quando informacoes validas")
    @WithMockUser
    void cadastrarMedico_cenario2() throws Exception{
        var especialidade = Especialidade.CARDIOLOGIA;
        var endereco = enderecoBase();
        var dadosRetorno = new Medico(null, "Medico", "medico@gmail.com", "(13) 98821-2514", "123456", true, especialidade, new Endereco("rua aaa", "123", "aaa", "a", "a", "a","12345678"));
        var dadosDetalhamento = new DadosDetalhamentoMedico(null, true, "Medico", "medico@gmail.com", "(13) 98821-2514", "123456", especialidade, new Endereco("rua aaa", "123", "aaa", "a", "a", "a","12345678"));

        when(medicoService.cadastrarMedico(any())).thenReturn(dadosRetorno);

        var response =mvc.perform(post("/medicos").contentType(MediaType.APPLICATION_JSON)
                .content(dadosCadastroMedicoJson.write(new DadosCadastroMedico("Medico",
                        "medico@gmail.com", "(13) 98821-2514", "123456",
                        especialidade, endereco)
                ).getJson()
        )).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosDetalhamentoMedicoJason.write(dadosDetalhamento).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);

    }

    private DadosEndereco enderecoBase() {
        var endereco = new Endereco("rua aaa", "123", "aaa", "a", "a", "a","12345678");
        return new DadosEndereco(endereco);
    }


}