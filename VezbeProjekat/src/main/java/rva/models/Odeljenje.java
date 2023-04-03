package rva.models;

import java.io.Serializable;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Odeljenje implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator (name = "ODELJENJE_ID_GENERATOR", sequenceName = "ODELJENJE_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator = "ODELJENJE_ID_GENERATOR")
	
	private long ID;
	private String naziv;
	private String lokacija;
	
	@JsonIgnore
	@OneToMany ( mappedBy = "odeljenje", cascade = CascadeType.REMOVE)
	
	private List<Pacijent> pacijent;
	
	private List<Pacijent> getPacijent() {
		return pacijent;
	}
	
	private void setPacijent (List<Pacijent> pacijent) {
		this.pacijent = pacijent;
	}
	
	public static long getSerialversionuid() {
		return serialVersionUID;
	}
	
	public Odeljenje() {}

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

	public String getLokacija() {
		return lokacija;
	}

	public void setLokacija(String lokacija) {
		this.lokacija = lokacija;
	}

	@ManyToOne
	@JoinColumn(name = "bolnica")
	
	private Bolnica bolnica;
	
	public Bolnica getBolnica() {
		return bolnica;
	}

	public void setBolnica(Bolnica bolnica) {
		this.bolnica = bolnica;
	}
	
	
	
	
}
