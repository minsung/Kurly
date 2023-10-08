package ms.study.kurly.feature.user;

import ms.study.kurly.feature.user.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Long> {

}
