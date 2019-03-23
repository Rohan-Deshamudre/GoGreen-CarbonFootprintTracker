package repository;

import model.CO2TransportPublic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2TransportPublicRepository extends CrudRepository<CO2TransportPublic, Long> {  }
