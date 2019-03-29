package gogreen.application.repository;

import gogreen.application.model.FriendRequest;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface FriendRequestRepository extends CrudRepository<FriendRequest, Long> {

    List<FriendRequest> findAll();
    List<FriendRequest> findByRequestTo(String friendUsername);
}
