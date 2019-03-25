package gogreen.application.repository;

import gogreen.application.model.CO2TransportBike;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2TransportBikeRepository extends CrudRepository<CO2TransportBike, Long> {  }
