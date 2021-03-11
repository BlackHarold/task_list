package home.blackharold.tasklist.repo;

import home.blackharold.tasklist.entity.Stat;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface StatRepository extends CrudRepository<Stat, Long> {
    @Override
    Optional<Stat> findById(Long aLong);
}
