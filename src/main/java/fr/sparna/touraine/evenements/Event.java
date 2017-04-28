package fr.sparna.touraine.evenements;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Arrays;
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
	
	public String getDatefinDisplay() {
		return getDateDisplay(datefin);
	}
	
	public String getDatedebDisplay() {
		return getDateDisplay(datedeb);
	}
	
	private String getDateDisplay(String date) {
		// le format de date cible
		SimpleDateFormat targetFormat = new SimpleDateFormat("dd/MM/yyyy");
		// la liste des formats que l'on teste
		List<SimpleDateFormat> formatters = Arrays.asList(new SimpleDateFormat[] {
				 new SimpleDateFormat("yyyy-MM-dd'T'hh:mm"),
				 new SimpleDateFormat("yyyy-MM-dd"),
				 new SimpleDateFormat("yyyy-MM-dd'T'hh:mm:ss'Z'"),
		});
		for (SimpleDateFormat aFormatter : formatters) {
			try {
				return targetFormat.format(aFormatter.parse(date));
			} catch (ParseException e) {
				// ignore silently
			}
		}
		// tout a échoué, on retourne la chaine de caractères de départ
		return date;
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
