package pl.kl.weatherservice.location;

import org.springframework.data.jpa.repository.JpaRepository;

interface LocationsRepository extends JpaRepository<Location, Long> {
}
