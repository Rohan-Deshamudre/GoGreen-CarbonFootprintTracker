package repository;

import model.CO2FoodMeal;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

//import java.util.List;

@Repository
public interface CO2FoodMealRepository extends CrudRepository<CO2FoodMeal, Long> {  }
