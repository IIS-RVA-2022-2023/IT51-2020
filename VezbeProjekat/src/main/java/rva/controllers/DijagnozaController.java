package rva.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Dijagnoza;
import rva.services.DijagnozaService;

@RestController
public class DijagnozaController {

	@Autowired
	private DijagnozaService service;
	
	@GetMapping("/dijagnoza")
	public ResponseEntity<List<Dijagnoza>> getAllDijagnoza(){
		List<Dijagnoza> dijagnoze = service.getAll();
		return new ResponseEntity<>(dijagnoze, HttpStatus.OK);
	}
	
	@GetMapping("/dijagnoza/{id}")
	public ResponseEntity<?> getDijagnozaById(@PathVariable long id){
		
		if(service.existsById(id)) {
			
			return ResponseEntity.ok(service.getById(id));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Dijagnoza with requested id does not exist!");
			
		}
		
	}
	
	@GetMapping("/dijagnoza/naziv/{naziv}")
	public ResponseEntity<?> getDijagnozaByNaziv(@PathVariable String naziv) {
		
		if(!service.getByNaziv(naziv).isEmpty()) {
			
			return ResponseEntity.ok(service.getByNaziv(naziv));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Dijagnoza with requested naziv ( " + naziv + " ) does not exist!");
			
		}
		
	}
	
	
	@GetMapping("/dijagnoza/dijagnozaSearch/{search}")
	public  ResponseEntity<?> getBolnicaBySearch(@PathVariable("search") String search){
		List<Dijagnoza> dijagnoze = service.getOznakaBySearch(search);
		if(dijagnoze.isEmpty())
			return new ResponseEntity<>(
			          "Ni jedna slicna dijagnoza ne postoji", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(dijagnoze);
	}
	
	@PostMapping("/dijagnoza")
	public ResponseEntity<?> createDijagnoza(@RequestBody Dijagnoza dijagnoza){
		Dijagnoza savedDijagnoza;
		if(!service.existsById(dijagnoza.getID())) {
			
			savedDijagnoza = service.save(dijagnoza);
			
		} else {
			
			List<Dijagnoza> lista = service.getAll();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getID()) {
					najvecaVrednost = lista.get(i).getID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			dijagnoza.setID(najvecaVrednost);
			savedDijagnoza = service.save(dijagnoza);
			
		}
		
		URI uri = URI.create("/dijagnoza/" + savedDijagnoza.getID());
		return ResponseEntity.created(uri).body(savedDijagnoza);
		
	}
	
	@PutMapping("/dijagnoza/{id}")
	public ResponseEntity<?> updateDijagnoza(@RequestBody Dijagnoza dijagnoza, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			dijagnoza.setID(id);
			Dijagnoza updateDijagnoza = service.save(dijagnoza);
			
			return ResponseEntity.ok(updateDijagnoza);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/dijagnoza/{id}")
	public ResponseEntity<?> deleteDijagnoza(@PathVariable long id) {
		
		if(service.existsById(id)) {
			
			service.deleteById(id);
			
			return ResponseEntity.ok("Resource with requested id: " + id + " has been deleted");
			
		}
		else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
			.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	
}
