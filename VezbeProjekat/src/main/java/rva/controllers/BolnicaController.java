package rva.controllers;

import java.net.URI;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import rva.models.Bolnica;
import rva.services.BolnicaService;

@CrossOrigin
@RestController
public class BolnicaController {

	@Autowired
	private BolnicaService service;
	
	@GetMapping("/bolnica")
	public ResponseEntity<List<Bolnica>> getAllBolnica(){
		List<Bolnica> bolnice = service.getAll();
		return new ResponseEntity<>(bolnice, HttpStatus.OK);
	}
	
	@GetMapping("/bolnica/{id}")
	public ResponseEntity<?> getBolnicaById(@PathVariable long id){
		
		if(service.existsById(id)) {
			
			return ResponseEntity.ok(service.getById(id));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Bolnica with requested id does not exist!");
			
		}
		
	}
	
	@GetMapping("/bolnica/naziv/{naziv}")
	public ResponseEntity<?> getBolnicaByNaziv(@PathVariable String naziv) {
		
		if(!service.getByNaziv(naziv).isEmpty()) {
			
			return ResponseEntity.ok(service.getByNaziv(naziv));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Bolnica with requested naziv ( " + naziv + " ) does not exist!");
			
		}
		
	}
	
	@GetMapping("bolnica/bolnicaSearch/{search}")
	public  ResponseEntity<?> getBolnicaBySearch(@PathVariable("search") String search){
		List<Bolnica> bolnice = service.getBolnicaBySearch(search);
		if(bolnice.isEmpty())
			return new ResponseEntity<>(
			          "Ni jedna slicna bolnica ne postoji", 
			          HttpStatus.NOT_FOUND);
		return ResponseEntity.status(HttpStatus.OK)
		        .body(bolnice);
	}
	
	@PostMapping("/bolnica")
	public ResponseEntity<?> createBolnica(@RequestBody Bolnica bolnica){
		Bolnica savedBolnica;
		if(!service.existsById(bolnica.getID())) {
			
			savedBolnica = service.save(bolnica);
			
		} else {
			
			List<Bolnica> lista = service.getAll();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getID()) {
					najvecaVrednost = lista.get(i).getID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			bolnica.setID(najvecaVrednost);
			savedBolnica = service.save(bolnica);
			
		}
		
		URI uri = URI.create("/bolnica/" + savedBolnica.getID());
		return ResponseEntity.created(uri).body(savedBolnica);
		
	}
	
	@PutMapping("/bolnica/{id}")
	public ResponseEntity<?> updateBolnica(@RequestBody Bolnica bolnica, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			bolnica.setID(id);
			Bolnica updateBolnica = service.save(bolnica);
			
			return ResponseEntity.ok(updateBolnica);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/bolnica/{id}")
	public ResponseEntity<?> deleteBolnica(@PathVariable long id) {
		
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
