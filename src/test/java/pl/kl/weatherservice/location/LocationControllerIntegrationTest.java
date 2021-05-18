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

import java.util.UUID;

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequest();
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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithEmptyCity();

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithNullCity();

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithEmptyCountry();

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithNullCountry();

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithLatitudeOutOfRange();

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
        CreateLocationRequest requestBody = LocationTestHelper.provideCreateLocationRequestWithLongitudeOutOfRange();

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
        CreateLocationRequest createRequestBody = LocationTestHelper.provideCreateLocationRequest();
        MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody));

        mockMvc.perform(createRequest)
                .andReturn().getResponse();

        String idOfCreatedLocation = locationRepository.findAll().stream()
                .findFirst().orElse(new Location()).getId();

        UpdateLocationRequest updateRequestBody = LocationTestHelper.provideUpdateLocationRequest(idOfCreatedLocation);
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
        assertThat(response.getHeader("ETag")).isEqualTo("\"0\"");
    }

    @Test
    void updateLocation_whenLocationIsNotFoundById_thenReturns404andDoesntUpdateInDb() throws Exception {
        // given
        CreateLocationRequest createRequestBody = LocationTestHelper.provideCreateLocationRequest();
        MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody));

        mockMvc.perform(createRequest)
                .andReturn().getResponse();

        String idOfCreatedLocation = locationRepository.findAll().stream()
                .findFirst().orElse(new Location()).getId();

        String randomId = UUID.randomUUID().toString();

        UpdateLocationRequest updateRequestBody = LocationTestHelper.provideUpdateLocationRequest(randomId);
        MockHttpServletRequestBuilder updateRequest = MockMvcRequestBuilders
                .put("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestBody))
                .header("If-Match", 0L);
        // when
        MockHttpServletResponse response = mockMvc.perform(updateRequest).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.NOT_FOUND.value());
        assertThat(locationRepository.findAll()).hasOnlyOneElementSatisfying(location -> {
            assertThat(location.getId()).isEqualTo(idOfCreatedLocation);
            assertThat(location.getCity()).isEqualTo("Gdansk");
            assertThat(location.getRegion()).isEqualTo("Pomeranian");
            assertThat(location.getCountry()).isEqualTo("Poland");
            assertThat(location.getLatitude()).isEqualTo(54.3);
            assertThat(location.getLongitude()).isEqualTo(18.6);
            assertThat(location.getVersion()).isEqualTo(0L);
        });
        assertThat(response.getHeader("ETag")).isEqualTo(null);
    }

    @Test
    void updateLocation_whenIfMatchIsIncorrect_thenReturns409andDoesntUpdateInDb() throws Exception {
        // given
        CreateLocationRequest createRequestBody = LocationTestHelper.provideCreateLocationRequest();
        MockHttpServletRequestBuilder createRequest = MockMvcRequestBuilders
                .post("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(createRequestBody));

        mockMvc.perform(createRequest)
                .andReturn().getResponse();

        String idOfCreatedLocation = locationRepository.findAll().stream()
                .findFirst().orElse(new Location()).getId();

        UpdateLocationRequest updateRequestBody = LocationTestHelper.provideUpdateLocationRequest(idOfCreatedLocation);
        MockHttpServletRequestBuilder updateRequest = MockMvcRequestBuilders
                .put("/location")
                .contentType(MediaType.APPLICATION_JSON)
                .content(objectMapper.writeValueAsString(updateRequestBody))
                .header("If-Match", 10L);
        // when
        MockHttpServletResponse response = mockMvc.perform(updateRequest).andReturn().getResponse();
        // then
        assertThat(response.getStatus()).isEqualTo(HttpStatus.CONFLICT.value());
        assertThat(locationRepository.findAll()).hasOnlyOneElementSatisfying(location -> {
            assertThat(location.getId()).isEqualTo(idOfCreatedLocation);
            assertThat(location.getCity()).isEqualTo("Gdansk");
            assertThat(location.getRegion()).isEqualTo("Pomeranian");
            assertThat(location.getCountry()).isEqualTo("Poland");
            assertThat(location.getLatitude()).isEqualTo(54.3);
            assertThat(location.getLongitude()).isEqualTo(18.6);
            assertThat(location.getVersion()).isEqualTo(0L);
        });
        assertThat(response.getHeader("ETag")).isEqualTo(null);
    }
}
