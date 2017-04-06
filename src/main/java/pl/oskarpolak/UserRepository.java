package pl.oskarpolak;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;
import pl.oskarpolak.model.User;

import java.util.List;

/**
 * Created by OskarPraca on 2017-02-28.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
      User findByUsername(String user);
      User findById(int id);
      List<User> findByUsernameContaining(String value);
      Page<User> findByIdGreaterThan(int value, Pageable pageable);
}
