package pl.kl.weatherservice.security;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import pl.kl.weatherservice.location.LocationTestHelper;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class BasicSecurityIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    ObjectMapper objectMapper;

    @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
    @Test
    void accessEndpointWithUserWithRequiredAuthorities_thenReturn201() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LocationTestHelper.provideLocationRequest()));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @WithMockUser(username = "user", password = "user1", roles = {"USER"})
    @Test
    void accessEndpointWithUserWithoutRequiredAuthorities_thenReturn403() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LocationTestHelper.provideLocationRequest()));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.FORBIDDEN.value());
    }

    @Test
    void accessEndpointWithoutAuthentication_thenReturn401() throws Exception {
        //given
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(LocationTestHelper.provideLocationRequest()));
        //when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        //then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }
}
