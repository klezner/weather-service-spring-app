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
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@AutoConfigureMockMvc
public class LocationControllerIntegrationTest {

    @Autowired
    MockMvc mockMvc;
    @Autowired
    LocationService locationService;
    @Autowired
    LocationRepository locationRepository;
    @Autowired
    ObjectMapper objectMapper;

    @BeforeEach
    void setUp() {
        locationRepository.deleteAll();
    }

    @WithMockUser(username = "admin", password = "admin1", roles = {"ADMIN"})
    @Test
    void postLocation_thenReturns201AndSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequest();
        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
        assertThat(locationRepository.findAll()).hasOnlyOneElementSatisfying(location -> {
            assertThat(location.getId()).isInstanceOf(String.class);
            assertThat(location.getCity()).isEqualTo("Gdansk");
            assertThat(location.getRegion()).isEqualTo("Pomeranian");
            assertThat(location.getCountry()).isEqualTo("Poland");
            assertThat(location.getLatitude()).isEqualTo(54.3);
            assertThat(location.getLongitude()).isEqualTo(18.6);
        });
    }

    @Test
    void postLocation_whenCityIsEmpty_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithEmptyCity();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCityIsNull_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithNullCity();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCountryIsEmpty_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithEmptyCountry();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenCountryIsNull_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithNullCountry();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenLatitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithLatitudeOutOfRange();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }

    @Test
    void postLocation_whenLongitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
        // given
        CreateLocationRequest requestBody = LocationTestHelper.provideLocationRequestWithLongitudeOutOfRange();

        MockHttpServletRequestBuilder request = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(requestBody));
        // when
        MockHttpServletResponse response = mockMvc.perform(request)
                .andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
        assertThat(locationRepository.findAll()).isEmpty();
    }
}
