package application.repository;
import application.model.CO2;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CO2Repository extends CrudRepository<CO2, Long>  {
    List<CO2> findByCusername(String cusername);
    List<CO2> findAll();


}
