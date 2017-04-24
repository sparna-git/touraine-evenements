package fr.sparna.touraine.evenements;

public class TraitementDesTypes {
	
	protected String mapType;
	
	protected Integer nombre;
	
	protected String nom;
	

	public String getMapType() {
		return mapType;
	}

	public void setMapType(String mapType) {
		if(mapType.equals("http://schema.org/MusicEvent")){
			 mapType="MusicEvent";
			 this.nom="Musique";
		 }
		if(mapType.equals("http://schema.org/DanceEvent")){
			 mapType="DanceEvent";
			 this.nom="Dance";
		 }
		 
		 if(mapType.equals("http://schema.org/ChildrensEvent")){
			 mapType="ChildrensEvent";
			 this.nom="Enfants";
		 }
		 if(mapType.equals("http://schema.org/Festival")){
			 mapType="Festival";
			 this.nom="Festival";
		 }
		 if(mapType.equals("http://schema.org/TheaterEvent")){
			 mapType="TheaterEvent";
			 this.nom="Théâtre";
		 }
		 if(mapType.equals("http://schema.org/ComedyEvent")){
			 
			 mapType="ComedyEvent";
			 this.nom="Comédie";
		 }
		 if(mapType.equals("http://schema.org/SportsEvent")){
			 mapType="SportsEvent";
			 this.nom="Sport";
		 }
		 if(mapType.equals("http://schema.org/SocialEvent")){
			 mapType="SocialEvent";
			 this.nom="Social";
		 }
		 if(mapType.equals("http://schema.org/LiteraryEvent")){
			 mapType="LiteraryEvent";
			 this.nom="Littérature";
		 }
		 if(mapType.equals("http://schema.org/BusinessEvent")){
			 mapType="BusinessEvent";
			 this.nom="Commerce";
		 }
		 if(mapType.equals("http://schema.org/DeliveryEvent")){
			 mapType="DeliveryEvent";
			 this.nom="Livraison";
		 }
		 if(mapType.equals("http://schema.org/EducationEvent")){
			 mapType="EducationEvent";
			 this.nom="Education";
		 }
		 if(mapType.equals("http://schema.org/ExhibitionEvent")){
			 mapType="ExhibitionEvent";
			 this.nom="Exposition";
		 }
		 if(mapType.equals("http://schema.org/SaleEvent")){
			 mapType="SaleEvent";
			 this.nom="vente";
		 }
		 if(mapType.equals("http://schema.org/FoodEvent")){
			 mapType="FoodEvent";
			 this.nom="Alimentaire";
		 }
		 if(mapType.equals("http://schema.org/PublicationEvent")){
			 mapType="PublicationEvent";
			 this.nom="Publication";
		 }
		 if(mapType.equals("http://schema.org/VisualArtsEvent")){
			 mapType="VisualArtsEvent";
			 this.nom="Art Visuel";
		 }
		this.mapType = mapType;
	}

	public Integer getNombre() {
		return nombre;
	}

	public void setNombre(Integer nombre) {
		this.nombre = nombre;
	}

	public String getNom() {
		return nom;
	}

	public void setNom(String nom) {
		this.nom = nom;
	}
	
	

}
