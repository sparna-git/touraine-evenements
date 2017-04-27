package fr.sparna.touraine.evenements;

import java.util.Arrays;
import java.util.List;
/**
 * cette classe permet de tronquer la description si trop longue
 * elle permet aussi récupérer les sources dans une liste de String 
 * @author clarvie
 *
 */
public class EvenementSourceDescriptionLimit {
	
	protected String description;
	
	protected String source;
	
	protected Integer sourceSize;

	public EvenementSourceDescriptionLimit(String description, String source) {
		super();
		this.description = description;
		this.source = source;
	}
	/**
	 * renvoi une description tronquée d'un événement si trop longue
	 * @return
	 */
	public String getDescription() {
		if(description.length()>400){
			return description.substring(0, 400);
		}else{
			return description;
		}
		
	}
	/**
	 * renvoi une liste de String représentant les sources d'un événement
	 * @return
	 */
	public List<String> getSource() {
			this.sourceSize= Arrays.asList(source.split("[ ]")).size();
			return Arrays.asList(source.split("[ ]"));
			
	}
	
	public Integer getSourceSize(){
		return sourceSize;
	}
	
}
