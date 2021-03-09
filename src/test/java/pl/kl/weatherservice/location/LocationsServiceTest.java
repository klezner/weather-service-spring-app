package pl.kl.weatherservice.location;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import pl.kl.weatherservice.exceptions.EmptyInputException;
import pl.kl.weatherservice.exceptions.InputOutOfRangeException;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.catchThrowable;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class LocationsServiceTest {

    @Mock
    LocationsRepository locationsRepository;
    @InjectMocks
    LocationsService locationsService;

    @Test
    void createLocation_createsNewLocation() {
        // given
        when(locationsRepository.save(any())).thenReturn(LocationTestHelper.provideLocationGdansk());
        // when
        Location location = locationsService.createLocation("Gdansk", "Pomeranian", "Poland", 54.3, 18.6);
        //then
        assertThat(location.getCity()).isEqualTo("Gdansk");
        assertThat(location.getRegion()).isEqualTo("Pomeranian");
        assertThat(location.getCountry()).isEqualTo("Poland");
        assertThat(location.getLatitude()).isEqualTo(54.3);
        assertThat(location.getLongitude()).isEqualTo(18.6);
        verify(locationsRepository).save(any());
    }

    @Test
    void createLocation_whenCityIsEmpty_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("", "Pomeranian", "Poland", 54.3, 18.6));
        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCityIsNull_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation(null, "Pomeranian", "Poland", 54.3, 18.6));
        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCountryIsEmpty_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", "", 54.3, 18.6));
        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenCountryIsNull_throwsEmptyInputException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", null, 54.3, 18.6));
        // then
        assertThat(exception).isInstanceOf(EmptyInputException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLatitudeIsMoreThanLimit_throwsInputOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", "Poland", 150.0, 18.6));
        // then
        assertThat(exception).isInstanceOf(InputOutOfRangeException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLatitudeIsLessThanLimit_createsNewLocation() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", "Poland", -150.0, 18.6));
        // then
        assertThat(exception).isInstanceOf(InputOutOfRangeException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLongitudeIsMoreThanLimit_throwsInputOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", "Poland", 54.3, 200.0));
        // then
        assertThat(exception).isInstanceOf(InputOutOfRangeException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenLongitudeIsLessThanLimit_throwsInputOutOfRangeException() {
        // when
        Throwable exception = catchThrowable(() -> locationsService.createLocation("Gdansk", "Pomeranian", "Poland", 54.3, -200.0));
        // then
        assertThat(exception).isInstanceOf(InputOutOfRangeException.class);
        verify(locationsRepository, times(0)).save(any());
    }

    @Test
    void createLocation_whenRegionIsEmpty_createsNewLocation() {
        // given
        when(locationsRepository.save(any())).thenReturn(LocationTestHelper.provideLocationCochabamba());
        // when
        Location location = locationsService.createLocation("Cochabamba", " ", "Bolivia", -17.4, -66.1);
        //then
        assertThat(location.getCity()).isEqualTo("Cochabamba");
        assertThat(location.getRegion()).isEqualTo(null);
        assertThat(location.getCountry()).isEqualTo("Bolivia");
        assertThat(location.getLatitude()).isEqualTo(-17.4);
        assertThat(location.getLongitude()).isEqualTo(-66.1);
        verify(locationsRepository).save(any());
    }
}
