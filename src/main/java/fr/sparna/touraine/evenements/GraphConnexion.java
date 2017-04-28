package fr.sparna.touraine.evenements;

import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.manager.RemoteRepositoryManager;
import org.eclipse.rdf4j.repository.manager.RepositoryManager;

public class GraphConnexion {
	
//	private static final String URLS="http://172.17.2.139:7200";
//
//	private static final String REPOSITORY="touraine-evenements";

	private RepositoryManager repositoryManager;
	private Repository repository;
	
	private String repositoryManagerUrl;
	private String repositoryName;
	
	public GraphConnexion(String repositoryManagerUrl, String repositoryName) {
		super();
		this.repositoryManagerUrl = repositoryManagerUrl;
		this.repositoryName = repositoryName;
	}

	/**
	 * Etablis la connexion avec le repository dans graphdb
	 */
	public Repository createRepository(){
		//repository manager
		this.repositoryManager = new RemoteRepositoryManager(repositoryManagerUrl);
		repositoryManager.initialize();
		// Open a connection to this repository
		this.repository = repositoryManager.getRepository(repositoryName);
		
		return repository;
	}

	public String getRepositoryManagerUrl() {
		return repositoryManagerUrl;
	}

	public void setRepositoryManagerUrl(String repositoryManagerUrl) {
		this.repositoryManagerUrl = repositoryManagerUrl;
	}

	public String getRepositoryName() {
		return repositoryName;
	}

	public void setRepositoryName(String repositoryName) {
		this.repositoryName = repositoryName;
	}

}
