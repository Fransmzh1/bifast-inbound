package bifast.library.repository;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import bifast.library.model.FaultClass;

@Repository
public interface FaultClassRepository extends JpaRepository<FaultClass, Long> {

	Optional<FaultClass> findByExceptionClass (String exceptionClass);
	
}
