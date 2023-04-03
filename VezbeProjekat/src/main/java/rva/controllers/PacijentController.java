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

import rva.models.Pacijent;
import rva.services.PacijentService;

@RestController
public class PacijentController {


	@Autowired
	private PacijentService service;
	
	@GetMapping("/pacijent")
	public ResponseEntity<List<Pacijent>> getAllPacijent(){
		List<Pacijent> pacijent = service.getAll();
		return new ResponseEntity<>(pacijent, HttpStatus.OK);
	}
	
	@GetMapping("/pacijent/{id}")
	public ResponseEntity<?> getPacijentById(@PathVariable long id){
		
		if(service.existsById(id)) {
			
			return ResponseEntity.ok(service.getById(id));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pacijent with requested id does not exist!");
			
		}
		
	}
	
	@GetMapping("/pacijent/ime/{ime}")
	public ResponseEntity<?> getPacijentByIme(@PathVariable String ime) {
		
		if(!service.getByIme(ime).isEmpty()) {
			
			return ResponseEntity.ok(service.getByIme(ime));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Pacijent with requested naziv ( " + ime + " ) does not exist!");
			
		}
		
	}
	
	@PostMapping("/pacijent")
	public ResponseEntity<?> createPacijent(@RequestBody Pacijent pacijent){
		Pacijent savedPacijent;
		if(!service.existsById(pacijent.getID())) {
			
			savedPacijent = service.save(pacijent);
			
		} else {
			
			List<Pacijent> lista = service.getAll();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getID()) {
					najvecaVrednost = lista.get(i).getID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			pacijent.setID(najvecaVrednost);
			savedPacijent = service.save(pacijent);
			
		}
		
		URI uri = URI.create("/pacijent/" + savedPacijent.getID());
		return ResponseEntity.created(uri).body(savedPacijent);
		
	}
	
	@PutMapping("/pacijent/{id}")
	public ResponseEntity<?> updatePacijent(@RequestBody Pacijent pacijent, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			pacijent.setID(id);
			Pacijent updatePacijent = service.save(pacijent);
			
			return ResponseEntity.ok(updatePacijent);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/pacijent/{id}")
	public ResponseEntity<?> deletePacijent(@PathVariable long id) {
		
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
