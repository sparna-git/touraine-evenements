package fr.sparna.touraine.evenements;

import java.util.List;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;

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
	public List<Event> getEvenementList(FormPost object, Integer offset,Repository repository);
	/**
	 * renvoi un string représentant le filter des évènements choisis
	 * ex : FILTER(?type IN(<http://schema.org/MusicEvent>))
	 * @param listEvenement
	 * @return
	 */
	public String filterEvenementList(List<String> listEvenement);
	
	/**
	 * retourne la taille dans la base pour le type dévènement demandé 
	 * @param request
	 * @param repositoryConnection
	 * @return
	 */
	public Integer getSizeOfEvents(String request,RepositoryConnection repositoryConnection);
	/**
	 * retourne le nombre de chaque type d'évènement présent dans la base
	 * @param req
	 * @param repositoryConnection
	 * @return
	 */
		
	public List<TraitementOfTypes>getNumberOfEachEventType(String req, RepositoryConnection repositoryConnection);
	

}
