package project.backend.mini_ecommerce.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import project.backend.mini_ecommerce.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {

}
