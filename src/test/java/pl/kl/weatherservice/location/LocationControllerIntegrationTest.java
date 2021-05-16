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
    void createLocation_thenReturns201AndSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenCityIsEmpty_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenCityIsNull_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenCountryIsEmpty_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenCountryIsNull_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenLatitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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
    void createLocation_whenLongitudeIsOutOfRange_thenReturns400AndDoesntSaveNewLocationToDb() throws Exception {
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

    @Test
    void updateLocation_thenReturns200andUpdateInDb() throws Exception {
        // given
        CreateLocationRequest createRequestBody = LocationTestHelper.provideLocationRequest();
        MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody));

        mockMvc.perform(createRequest)
                .andReturn().getResponse();

        String idOfCreatedLocation = locationRepository.findAll().stream()
                .findFirst().orElse(new Location()).getId();

        UpdateLocationRequest updateRequestBody = LocationTestHelper.provideLocationToUpdateRequest(idOfCreatedLocation);
        MockHttpServletRequestBuilder updateRequest = MockMvcRequestBuilders
                .put("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestBody))
                .header("If-Match", 0L);
        // when
        MockHttpServletResponse response = mockMvc.perform(updateRequest).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
        assertThat(locationRepository.findAll()).hasOnlyOneElementSatisfying(location -> {
            assertThat(location.getId()).isEqualTo(idOfCreatedLocation);
            assertThat(location.getCity()).isEqualTo("Gdansk");
            assertThat(location.getRegion()).isEqualTo("Pomeranian");
            assertThat(location.getCountry()).isEqualTo("Poland");
            assertThat(location.getLatitude()).isEqualTo(54.35);
            assertThat(location.getLongitude()).isEqualTo(18.67);
            assertThat(location.getVersion()).isEqualTo(1L);
        });
        assertThat(response.getHeader("ETag").replace("\"", "")).isEqualTo("0");
    }
}
