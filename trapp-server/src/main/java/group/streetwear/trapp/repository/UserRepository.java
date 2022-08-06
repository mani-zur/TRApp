package group.streetwear.trapp.repository;

import group.streetwear.trapp.model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends CrudRepository<User, Long> {
    User findByActiveDirectoryGUID(String GUID);
}
