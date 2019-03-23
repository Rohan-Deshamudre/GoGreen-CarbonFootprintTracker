package repository;

import model.CO2EnergySolarPanels;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2EnergySolarPanelsRepository extends CrudRepository<CO2EnergySolarPanels, Long> {  }
