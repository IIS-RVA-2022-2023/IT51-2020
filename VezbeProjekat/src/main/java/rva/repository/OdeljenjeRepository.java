package rva.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import rva.models.Odeljenje;

public interface OdeljenjeRepository extends JpaRepository<Odeljenje, Long> {

	public abstract List<Odeljenje> findByNazivContainingIgnoreCase(String naziv);
	
}
