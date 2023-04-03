package rva.repository;

import java.util.List;
import rva.models.Pacijent;

import org.springframework.data.jpa.repository.JpaRepository;


public interface PacijentRepository extends JpaRepository<Pacijent, Long> {

	public abstract List<Pacijent> findByImeContainingIgnoreCase(String ime);
	
}
