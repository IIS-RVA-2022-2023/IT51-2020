package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Dijagnoza implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator (name = "DIJAGNOZA_ID_GENERATOR", sequenceName = "DIJAGNOZA_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "DIJAGNOZA_ID_GENERATOR")
	
	private long ID;
	
	private String naziv;
	private String opis;
	private String oznaka;
	
	@JsonIgnore
	@OneToMany (mappedBy = "dijagnoza", cascade = CascadeType.REMOVE)
	
	private List<Pacijent> pacijent;
	
	private List<Pacijent> getPacijent() {		
		return pacijent;		
	}
	
	private void setPacijent(List<Pacijent> pacijent) {
		this.pacijent = pacijent;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Dijagnoza() {}

	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getNaziv() {
		return naziv;
	}

	public void setNaziv(String naziv) {
		this.naziv = naziv;
	}

	public String getOpis() {
		return opis;
	}

	public void setOpis(String opis) {
		this.opis = opis;
	}

	public String getOznaka() {
		return oznaka;
	}

	public void setOznaka(String oznaka) {
		this.oznaka = oznaka;
	}
	
	

	
}
