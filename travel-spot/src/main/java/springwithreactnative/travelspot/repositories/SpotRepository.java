package springwithreactnative.travelspot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import springwithreactnative.travelspot.entities.SpotEntity;

@Repository
public interface SpotRepository extends JpaRepository<SpotEntity, String> {
}
