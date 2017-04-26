package fr.sparna.touraine.evenements;

import java.util.List;

public class Event {
	
	protected String nom;
	
	protected String type;
	
	protected String datedeb;
	
	protected String datefin;
	
	protected String location;
	
	protected String adresse;
	
	protected String imagePath;
	
	protected String description;
	
	protected String latitude;
	
	protected String longitude;
	
	protected Integer sourcesize;
	
	protected List<String> sources;
	
	
	
	public Integer getSourcesize() {
		return sourcesize;
	}

	public void setSourcesize(Integer sourcesize) {
		this.sourcesize = sourcesize;
	}

	public String getNom() {
		return nom;
	}
	
	public void setNom(String nom) {
		this.nom = nom;
	}
	
	public String getType() {
		return type;
	}
	
	public void setType(String type) {
		this.type = type;
	}
	
	public String getDatedeb() {
		return datedeb;
	}
	
	public void setDatedeb(String datedeb) {
		this.datedeb = datedeb;
	}
	
	public String getDatefin() {
		return datefin;
	}
	
	public void setDatefin(String datefin) {
		this.datefin = datefin;
	}
	public String getLocation() {
		return location;
	}
	
	public void setLocation(String location) {
		this.location = location;
	}
	
	public String getAdresse() {
		return adresse;
	}
	
	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}
	public String getImagePath() {
		return imagePath;
	}
	
	public void setImagePath(String imagePath) {
		this.imagePath = imagePath;
	}
	
	public String getDescription() {
		return description;
	}
	
	public void setDescription(String description) {
		this.description = description;
	}
	
	public String getLatitude() {
		return latitude;
	}
	
	public void setLatitude(String latitude) {
		this.latitude = latitude;
	}
	
	public String getLongitude() {
		return longitude;
	}
	
	public void setLongitude(String longitude) {
		this.longitude = longitude;
	}
	
	public List<String> getSources() {
		return sources;
	}
	
	public void setSources(List<String> sources) {
		this.sources = sources;
	}
	
/*	
	@Override
	public String toString() {
		return "Evenement [nom=" + nom + "\n, type=" + type + "\n, datedeb=" + datedeb + "\n, datefin=" + datefin
				+ "\n, location=" + location + "\n, adresse=" + adresse + "\n, imagePath=" + imagePath + "\n, description="
				+ description + "\n, latitude=" + latitude + "\n, longitude=" + longitude + "\n, sources=" + sources + "]";
	}
	*/
	
}
