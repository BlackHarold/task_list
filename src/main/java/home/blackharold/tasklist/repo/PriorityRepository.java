package home.blackharold.tasklist.repo;

import home.blackharold.tasklist.entity.Priority;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface PriorityRepository extends JpaRepository<Priority, Long> {

    @Query("select p from Priority p where (:color is null or :color='' or lower(p.color) like lower(concat('%', :color, '%'))) and (:title is null or :title='' or lower(p.title) like lower(concat('%', :title,'%'))) order by p.color asc")
    List<Priority> findByColor(@Param("title") String title, @Param("color") String color);
}
