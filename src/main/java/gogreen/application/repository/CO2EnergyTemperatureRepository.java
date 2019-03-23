package gogreen.application.repository;

import gogreen.application.model.CO2EnergyTemperature;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2EnergyTemperatureRepository extends CrudRepository<CO2EnergyTemperature, Long> {  }
