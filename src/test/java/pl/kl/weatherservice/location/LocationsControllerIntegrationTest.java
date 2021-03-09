package pl.kl.weatherservice.location;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.mock.web.MockHttpServletResponse;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationsControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationsService locationsService;
    @Autowired
    LocationsRepository locationsRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        locationsRepository.deleteAll();
    }

    @Test
    void postLocation_createsNewLocation() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestGdansk();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders.post("/location")
                .contentType(MediaType.APPLICATION_JSON).content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(locationsRepository.findAll()).singleElement().satisfies(location -> {
            assertThat(location.getId()).isInstanceOf(String.class);
            assertThat(location.getCity()).isEqualTo("Gdansk");
            assertThat(location.getRegion()).isEqualTo("Pomeranian");
            assertThat(location.getCountry()).isEqualTo("Poland");
            assertThat(location.getLatitude()).isEqualTo(54.3);
            assertThat(location.getLongitude()).isEqualTo(18.6);
        });
    }
}
