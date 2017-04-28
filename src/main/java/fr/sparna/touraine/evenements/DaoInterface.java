package fr.sparna.touraine.evenements;

import java.util.List;

public interface DaoInterface {
	
	/**
	 * retourne la liste des évènements
	 * @param object
	 * oject contenant les critères de tri
	 * @param offset
	 * index à partir de laquelle les données seront récupérées de la base
	 * @param repository
	 * @return
	 */
	public List<Event> getEvenementList(FormPost object, Integer offset);
	/**
	 * renvoi un string représentant le filter des évènements choisis
	 * ex : FILTER(?type IN(<http://schema.org/MusicEvent>))
	 * @param listEvenement
	 * @return
	 */
	public String filterEvenementList(List<String> listEvenement);
	
	public Integer getResultLength();
	
	public List<TraitementOfTypes> getTypeNumberList();
	
	public Integer getSizetypeNumber();
	

}
