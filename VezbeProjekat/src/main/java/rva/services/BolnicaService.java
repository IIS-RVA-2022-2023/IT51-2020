package rva.services;

import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;

import rva.repository.BolnicaRepository;
import rva.models.Bolnica;

@Service
public class BolnicaService {

	@Autowired
	private BolnicaRepository repo;
	
	public List<Bolnica> getAll(){
		return repo.findAll();
	}
	
	public Optional<Bolnica> getById(long id) {
		return repo.findById(id);
	}
	
	public Optional<List<Bolnica>> getByNaziv(String naziv) {
		Optional<List<Bolnica>> bolnice = Optional.of(repo.findByNazivContainingIgnoreCase(naziv));
		return bolnice;
	}
	
	public List<Bolnica> getBolnicaBySearch(String search) {
		String searchEntry = search.toLowerCase();
		return repo.getBySearch(searchEntry);
	}
	
	public Bolnica save(Bolnica bolnica) {
		return repo.save(bolnica);
	}

	public boolean existsById(long id) {
		if(repo.existsById(id)) {
			return true;
		}else
			return false;
	}

	public void deleteById(long id) {
		repo.deleteById(id);
	}
}
