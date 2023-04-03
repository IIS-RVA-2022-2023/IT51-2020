package rva.services;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import rva.models.Pacijent;

import rva.repository.PacijentRepository;

@Service
public class PacijentService {

	@Autowired
	private PacijentRepository repo;
	
	public List<Pacijent> getAll(){
		return repo.findAll();
	}
	
	public Optional<Pacijent> getById(long id){
		return repo.findById(id);
	}
	
	public Optional<List<Pacijent>> getByIme(String ime){
		Optional<List<Pacijent>> pacijenti = Optional.of(repo.findByImeContainingIgnoreCase(ime));
		return pacijenti;
	}
	
	public Pacijent save(Pacijent pacijent) {
		return repo.save(pacijent);
	}
	
	public boolean existsById(long id) {
		return repo.existsById(id);
	}
	
	public void deleteById(long id) {
		repo.deleteById(id);
	}
	
}
