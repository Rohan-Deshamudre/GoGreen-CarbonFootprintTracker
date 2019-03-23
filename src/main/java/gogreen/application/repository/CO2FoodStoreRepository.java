package gogreen.application.repository;

import gogreen.application.model.CO2FoodStore;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2FoodStoreRepository extends CrudRepository<CO2FoodStore, Long> {  }
