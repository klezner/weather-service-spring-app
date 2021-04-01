package pl.kl.weatherservice;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.security.test.web.servlet.request.SecurityMockMvcRequestPostProcessors.user;

@SpringBootTest
@AutoConfigureMockMvc
public class DemoControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;

    @Test
    void accessEndpointWithUserWithRequiredAuthorities_thenReturn200() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/location")
                .with(user("admin").roles("ADMIN"));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    void accessEndpointWithUserWithoutRequiredAuthorities_thenReturn403() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/location")
                .with(user("user").roles("USER"));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void accessEndpointWithoutAuthentication_thenReturn401() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.get("/location");
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
