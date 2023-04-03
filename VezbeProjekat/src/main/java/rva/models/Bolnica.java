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
public class Bolnica implements Serializable {
	
	private static final long serialVersionUID = 1L;

	
	@Id
	@SequenceGenerator (name = "BOLNICA_ID_GENERATOR", sequenceName = "BOLNICA_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "BOLNICA_ID_GENERATOR")
	
	private long ID;
	
	private String naziv;
	private String adresa;
	private long budzet;
	
	@JsonIgnore
	@OneToMany(mappedBy = "bolnica", cascade = CascadeType.REMOVE)
	
	private List<Odeljenje> odeljenje;
	
	private List<Odeljenje> getOdeljenje() {
		return odeljenje;
	}
	
	private void setOdeljenje( List<Odeljenje> odeljenje) {
		this.odeljenje = odeljenje;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	public Bolnica() {}

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

	public String getAdresa() {
		return adresa;
	}

	public void setAdresa(String adresa) {
		this.adresa = adresa;
	}

	public long getBudzet() {
		return budzet;
	}

	public void setBudzet(long budzet) {
		this.budzet = budzet;
	}
	
}
