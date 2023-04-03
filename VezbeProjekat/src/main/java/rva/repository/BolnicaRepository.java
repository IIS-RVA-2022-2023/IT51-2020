package rva.repository;

import java.util.List;

import rva.models.Bolnica;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

public interface BolnicaRepository extends JpaRepository<Bolnica,Long>  {


		public abstract List<Bolnica> findByNazivContainingIgnoreCase(String naziv);
		
		@Query(value="select * from bolnica where lower(naziv) like %:search%", nativeQuery = true)
		List<Bolnica> getBySearch(@Param("search") String search);
	
}
