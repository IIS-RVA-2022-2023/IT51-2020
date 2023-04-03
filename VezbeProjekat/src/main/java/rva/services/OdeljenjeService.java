package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import rva.models.Odeljenje;
import rva.repository.OdeljenjeRepository;

@Service
public class OdeljenjeService {
	
	@Autowired
	private OdeljenjeRepository repo;
	
	public List<Odeljenje> getAll(){
		return repo.findAll();
	}
	
	public Optional<Odeljenje> getById(Long id){
		return repo.findById(id);
	}
	
	public Optional<List<Odeljenje>> getByNaziv(String naziv){
		Optional<List<Odeljenje>> odeljenja = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return odeljenja;
	}

	public Odeljenje save(Odeljenje odeljenje) {
		return repo.save(odeljenje);
	}
	
	public boolean existsById(Long id) {
		return repo.existsById(id);
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
}
