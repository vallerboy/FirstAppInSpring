package pl.oskarpolak;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import pl.oskarpolak.model.User;

import java.util.List;

/**
 * Created by OskarPraca on 2017-02-28.
 */
public interface UserRepository extends CrudRepository<User, Integer>{
     User findByUsername(String username);
     User findByUsernameAndRole(String username, String role);
     List<User> findByIdGreaterThan(int value);
     List<User> findByUsernameContaining(String value);

     @Query(value = "SELECT * FROM user WHERE username = ?1", nativeQuery = true)
     User findByMoj(String username);

     Page<User> findByIdGreaterThan(int value, Pageable pageable);

     // SELECT * FROM users WHERE username LIKE '%value%';
     // SELECT * FROM users WHERE username = 'asd' AND role = 'asdsd';
}
