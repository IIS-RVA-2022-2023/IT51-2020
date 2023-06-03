package rva.controllers;

import java.net.URI;
import java.util.List;
import java.util.Optional;

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

import rva.models.Bolnica;
import rva.models.Odeljenje;
import rva.services.BolnicaService;
import rva.services.OdeljenjeService;

@RestController
public class OdeljenjeController {

	@Autowired
	private OdeljenjeService service;
	@Autowired
	private BolnicaService bolnicaService;
	
	@GetMapping("/odeljenje")
	public ResponseEntity<List<Odeljenje>> getAllOdeljenje(){
		List<Odeljenje> odeljenja = service.getAll();
		return new ResponseEntity<>(odeljenja, HttpStatus.OK);
	}
	
	@GetMapping("/odeljenje/{id}")
	public ResponseEntity<?> getOdeljenjeById(@PathVariable long id){
		
		if(service.existsById(id)) {
			
			return ResponseEntity.ok(service.getById(id));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Odeljenje with requested id does not exist!");
			
		}
		
	}
	
	@GetMapping("/odeljenje/naziv/{naziv}")
	public ResponseEntity<?> getOdeljenjeByNaziv(@PathVariable String naziv) {
		
		if(!service.getByNaziv(naziv).isEmpty()) {
			
			return ResponseEntity.ok(service.getByNaziv(naziv));
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Odeljenje with requested naziv ( " + naziv + " ) does not exist!");
			
		}
		
	}
	
	@GetMapping("odeljenja/{id}")
	public ResponseEntity<?> getAllForBolnica(@PathVariable("id") int id) {
		
		Optional<Bolnica> bolnicaOpt = bolnicaService.getById(id);
		
		if(bolnicaOpt.isPresent()) {
			
			List<Odeljenje> odeljenja = service.findByBolnica(bolnicaOpt.get());
			
			if(odeljenja.isEmpty())
				return new ResponseEntity<>("Odeljenja nisu pronadjena.", HttpStatus.NOT_FOUND);
			
			return new ResponseEntity<>(odeljenja, HttpStatus.OK);
		}
		
		return new ResponseEntity ("Bolnica nije pronadjena", HttpStatus.NOT_FOUND);
		
	}
	
	@PostMapping("/odeljenje")
	public ResponseEntity<?> createOdeljenje(@RequestBody Odeljenje odeljenje){
		Odeljenje savedOdeljenje;
		if(!service.existsById(odeljenje.getID())) {
			
			savedOdeljenje = service.save(odeljenje);
			
		} else {
			
			List<Odeljenje> lista = service.getAll();
			long najvecaVrednost = 1;
			
			for (int i = 0; i < lista.size(); i++) {
				
				if (najvecaVrednost <= lista.get(i).getID()) {
					najvecaVrednost = lista.get(i).getID();
				}
				
				if (i == lista.size() - 1) {
					najvecaVrednost++;
				}
				
			}
			
			odeljenje.setID(najvecaVrednost);
			savedOdeljenje = service.save(odeljenje);
			
		}
		
		URI uri = URI.create("/odeljenje/" + savedOdeljenje.getID());
		return ResponseEntity.created(uri).body(savedOdeljenje);
		
	}
	
	@PutMapping("/odeljenje/{id}")
	public ResponseEntity<?> updateOdeljenje(@RequestBody Odeljenje odeljenje, @PathVariable long id){
		
		if(service.existsById(id)) {
			
			odeljenje.setID(id);
			Odeljenje updateOdeljenje = service.save(odeljenje);
			
			return ResponseEntity.ok(updateOdeljenje);
			
		} else {
			
			return ResponseEntity.status(HttpStatus.NOT_FOUND)
					.body("Resource with requested id: " + id + " has not been found");
			
		}
	}
	
	@DeleteMapping("/odeljenje/{id}")
	public ResponseEntity<?> deleteOdeljenje(@PathVariable long id) {
		
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
