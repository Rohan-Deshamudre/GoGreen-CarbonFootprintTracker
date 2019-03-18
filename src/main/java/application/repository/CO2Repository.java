package application.repository;

import application.model.CO2;
import org.springframework.data.repository.CrudRepository;

import java.util.List;


public interface CO2Repository extends CrudRepository<CO2, Long>  {
    List<CO2> findByCusername(String cusername);

}
