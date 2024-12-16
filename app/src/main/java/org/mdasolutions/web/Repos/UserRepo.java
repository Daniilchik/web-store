package org.mdasolutions.web.Repos;

import org.mdasolutions.web.Entities.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User, Long> {
    User findByLoginAndPassword(String login, String password);
    boolean existsByLogin(String login);
    boolean existsByEmail(String email);
}
