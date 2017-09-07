package netgloo.models;

import javax.transaction.Transactional;

import org.springframework.data.repository.CrudRepository;

@Transactional
public interface UserDao extends CrudRepository<User, Long> {

    User findByEmail(String email);

    User findById(String id);

}
