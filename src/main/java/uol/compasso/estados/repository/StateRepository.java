package uol.compasso.estados.repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import uol.compasso.estados.modelo.State;

public interface StateRepository extends JpaRepository<State, Long> {

	Page<State> findByName(String name, Pageable pageable);

	Object findByName(String name);
}
