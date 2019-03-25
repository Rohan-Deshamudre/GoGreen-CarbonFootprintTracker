package gogreen.application.repository;

import gogreen.application.model.User;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends CrudRepository<User, Long>  {
    List<User> findByUsername(String username);

    List<User> findAll();

    List<User> findByUsernameAndPassword(String username, String password);



}
