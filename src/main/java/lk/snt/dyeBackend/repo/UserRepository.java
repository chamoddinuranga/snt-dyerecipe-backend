package lk.snt.dyeBackend.repo;
import lk.snt.dyeBackend.entity.User;
import org.springframework.data.jpa.repository.*;
import org.springframework.data.repository.query.Param;

import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Long> {
    boolean existsByUserName(String userName);
    Optional<User> findByUserName(String userName);
    void deleteByUserName(String userName);
}
