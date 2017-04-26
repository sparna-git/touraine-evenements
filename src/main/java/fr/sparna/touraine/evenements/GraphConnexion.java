package fr.sparna.touraine.evenements;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;

public class GraphConnexion {
	
	private static final String URLS="http://172.17.2.139:7200";

	private static final String REPOSITORY="touraine-evenements";

	private RepositoryManager repositoryManager;

	private Repository repository;
	/**
	 * Etablis la connexion avec le repository dans graphdb
	 */
	public Repository  connexion(){
		//repository manager
		this.repositoryManager = new RemoteRepositoryManager(URLS);
		repositoryManager.initialize();
		// Open a connection to this repository
		this.repository = repositoryManager.getRepository(REPOSITORY);
		
		return repository;

	}

}
