package rva.models;

import java.io.Serializable;
import java.util.Date;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;

@Entity
public class Pacijent implements Serializable {

	private static final long serialVersionUID = 1L;
	
	@Id
	@SequenceGenerator ( name = "PACIJENT_ID_GENERATOR", sequenceName = "PACIJENT_SEQ", allocationSize = 1)
	@GeneratedValue (strategy = GenerationType.SEQUENCE, generator="PACIJENT_ID_GENERATOR")
	
	private long ID;
	
	private String ime;
	private String prezime;
	private boolean zdrOsiguranje;
	private Date datumRodjenja;
	
	public long getID() {
		return ID;
	}

	public void setID(long iD) {
		ID = iD;
	}

	public String getIme() {
		return ime;
	}

	public void setIme(String ime) {
		this.ime = ime;
	}

	public String getPrezime() {
		return prezime;
	}

	public void setPrezime(String prezime) {
		this.prezime = prezime;
	}

	public boolean isZdrOsiguranje() {
		return zdrOsiguranje;
	}

	public void setZdrOsiguranje(boolean zdrOsiguranje) {
		this.zdrOsiguranje = zdrOsiguranje;
	}

	public Date getDatumRodjenja() {
		return datumRodjenja;
	}

	public void setDatumRodjenja(Date datumRodjenja) {
		this.datumRodjenja = datumRodjenja;
	}

	public static long getSerialversionuid() {
		return serialVersionUID;
	}

	@ManyToOne
	@JoinColumn (name = "odeljenje")
	
	private Odeljenje odeljenje;

	public Odeljenje getOdeljenje() {
		return odeljenje;
	}

	public void setOdeljenje(Odeljenje odeljenje) {
		this.odeljenje = odeljenje;
	}
	
	@ManyToOne
	@JoinColumn (name = "dijagnoza")
	
	private Dijagnoza dijagnoza;

	public Dijagnoza getDijagnoza() {
		return dijagnoza;
	}

	public void setDijagnoza(Dijagnoza dijagnoza) {
		this.dijagnoza = dijagnoza;
	}
	
	
	
}
