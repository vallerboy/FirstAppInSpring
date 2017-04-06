package pl.oskarpolak;

import org.springframework.data.repository.CrudRepository;
import pl.oskarpolak.model.User;

/**
 * Created by OskarPraca on 2017-02-28.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
      User findByUsername(String user);
      User findById(int id);
}
