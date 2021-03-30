package pl.kl.weatherservice.location;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocationRepository extends JpaRepository<Location, Long> {
}
