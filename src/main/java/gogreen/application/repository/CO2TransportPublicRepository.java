package gogreen.application.repository;

import gogreen.application.model.CO2TransportPublic;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CO2TransportPublicRepository extends CrudRepository<CO2TransportPublic, Long> {
}
