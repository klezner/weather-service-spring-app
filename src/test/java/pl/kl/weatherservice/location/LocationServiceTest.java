package pl.kl.weatherservice.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
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

    @Test
    void createLocation_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(LocationTestHelper.provideLocation());
        // when
        Location location = locationService.createLocation("Gdansk", "Pomeranian", "Poland", 54.3, 18.6);
        //then
        assertThat(location.getCity()).isEqualTo("Gdansk");
        assertThat(location.getRegion()).isEqualTo("Pomeranian");
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.3);
        assertThat(location.getLongitude()).isEqualTo(18.6);
        verify(locationRepository).save(any());
    }

    @Test
    void createLocation_whenRegionIsEmpty_thenCreatesNewLocation() {
        // given
        when(locationRepository.save(any())).thenReturn(LocationTestHelper.provideLocationWithEmptyRegion());
        // when
        Location location = locationService.createLocation("Gdansk", "", "Poland", 54.3, 18.6);
        //then
        assertThat(location.getCity()).isEqualTo("Gdansk");
        assertThat(location.getRegion()).isEqualTo(null);
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.3);
        assertThat(location.getLongitude()).isEqualTo(18.6);
        verify(locationRepository).save(any());
    }
}
