package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Dijagnoza;
import rva.repository.DijagnozaRepository;

@Service
public class DijagnozaService {

	@Autowired
	private DijagnozaRepository repo;
	
	public List<Dijagnoza> getAll(){
		return repo.findAll();
	}
	
	public Optional<Dijagnoza> getById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Dijagnoza>> getByNaziv(String naziv){
		Optional<List<Dijagnoza>> dijagnoze = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return dijagnoze;
	}
	
	public List<Dijagnoza> getOznakaBySearch(String search) {
		String searchEntry = search.toLowerCase();
		return repo.getBySearch(searchEntry);
	}
	
	public Dijagnoza save(Dijagnoza dijagnoza) {
		return repo.save(dijagnoza);
	}
	
	public boolean existsById(long id) {
		return repo.existsById(id);
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
