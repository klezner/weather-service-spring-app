package pl.kl.weatherservice.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.ArgumentCaptor;
import org.mockito.Captor;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
public class LocationServiceTest {

    @Mock
    LocationRepository locationRepository;
    @InjectMocks
    LocationService locationService;
    @Captor
    ArgumentCaptor<Location> locationArgumentCaptor;

    @Test
    void createLocation_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(LocationTestHelper.provideLocationToCreate());
        // when
        locationService.createLocation("Gdansk", "Pomeranian", "Poland", 54.3, 18.6);
        // then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        Location location = locationArgumentCaptor.getValue();
        assertThat(location.getCity()).isEqualTo("Gdansk");
        assertThat(location.getRegion()).isEqualTo("Pomeranian");
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.3);
        assertThat(location.getLongitude()).isEqualTo(18.6);
    }

    @Test
    void createLocation_whenRegionIsEmpty_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(LocationTestHelper.provideLocationToCreateWithEmptyRegion());
        // when
        locationService.createLocation("Gdansk", "", "Poland", 54.3, 18.6);
        // then
        verify(locationRepository).save(locationArgumentCaptor.capture());
        Location location = locationArgumentCaptor.getValue();
        assertThat(location.getCity()).isEqualTo("Gdansk");
        assertThat(location.getRegion()).isNull();
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.3);
        assertThat(location.getLongitude()).isEqualTo(18.6);
    }
}
