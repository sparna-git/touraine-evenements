package fr.sparna.touraine.evenements;

import java.util.Arrays;
import java.util.List;

public class EvenementSourceDescriptionLimit {
	
	protected String description;
	
	protected String source;
	
	protected Integer sourceSize;

	public EvenementSourceDescriptionLimit(String description, String source) {
		super();
		this.description = description;
		this.source = source;
	}

	public String getDescription() {
		if(description.length()>400){
			return description.substring(0, 400);
		}else{
			return description.substring(0, description.length());
		}
		
	}

	public List<String> getSource() {
			this.sourceSize= Arrays.asList(source.split("[ ]")).size();
			return Arrays.asList(source.split("[ ]"));
			
	}
	
	public Integer getSourceSize(){
		return sourceSize;
	}
	
}
