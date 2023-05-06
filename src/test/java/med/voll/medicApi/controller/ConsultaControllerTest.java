package med.voll.medicApi.controller;

import med.voll.medicApi.domain.consulta.AgendamentoService;
import med.voll.medicApi.domain.consulta.ConsultaDtoCreate;
import med.voll.medicApi.domain.consulta.ConsultaDtoGetAll;
import med.voll.medicApi.domain.medico.Especialidade;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class ConsultaControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private JacksonTester<ConsultaDtoCreate> dadosCreateJson;

    @Autowired
    private JacksonTester<ConsultaDtoGetAll> dadosResponseJson;

    @MockBean
    private AgendamentoService agendamento;

    @Test
    @DisplayName("Deveria devolver codigo http 400 quando informacoes estao invalidas")
    @WithMockUser
    void agendar_cenario1() throws Exception {
        var response = mvc.perform(post("/consultas"))
            .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Deveria devolver codigo http 200 quando informacoes estao validas")
    @WithMockUser
    void agendar_cenario2() throws Exception {

        var agendamentoReturn = new ConsultaDtoGetAll(null, 2l, 5l, LocalDateTime.now().plusHours(1));

        when(agendamento.agendar(any())).thenReturn(agendamentoReturn);

        var response = mvc
                .perform(
                        post("/consultas")
                                .contentType(MediaType.APPLICATION_JSON)
                                .content(dadosCreateJson.write(
                                        new ConsultaDtoCreate(2l, 5l, LocalDateTime.now().plusHours(1), Especialidade.CARDIOLOGIA)
                                ).getJson())
                )
                .andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());

        var jsonEsperado = dadosResponseJson.write(agendamentoReturn).getJson();

        assertThat(response.getContentAsString()).isEqualTo(jsonEsperado);
    }

}