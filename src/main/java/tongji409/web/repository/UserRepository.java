package tongji409.web.repository;

/**
 * @author lijiechu
 * @create on 16/12/3
 * @description User类的基本数据库操作
 */
import org.springframework.data.repository.CrudRepository;
import tongji409.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {

    public User findByUsername(String username);
}
