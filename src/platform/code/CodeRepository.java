package platform.code;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Repository
public interface CodeRepository extends JpaRepository<Code, Long> {

    List<Code> findAllByOrderByDateDesc();
}
