package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import rva.models.Dijagnoza;

public interface DijagnozaRepository extends JpaRepository <Dijagnoza, Long> {

	public abstract List<Dijagnoza> findByNazivContainingIgnoreCase(String naziv);
	
	@Query(value="select * from dijagnoza where lower(oznaka) like %:search%", nativeQuery = true)
	List<Dijagnoza> getBySearch(@Param("search") String search);
	
}
