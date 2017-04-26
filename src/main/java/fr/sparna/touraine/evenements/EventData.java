package fr.sparna.touraine.evenements;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Iterator;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class EventData{
	public static final String KEY = EventData.class.getCanonicalName();

	protected List<TraitementOfTypes> mapType;
	
	protected List<String> evenementListName;
	
	protected List<Event> dataList;

	protected String evenement;

	protected String startDate;

	protected String endDate;

	protected Integer index;

	protected Integer resultLenght;
	
	protected String fullText;
	
	protected Integer eventTypeLength;
	
	protected List<String> listEventExtractWithSplit;
	

	
	public List<String> getEvenementListName() {
		return evenementListName;
	}

	public void setEvenementListName(List<String> evenementList) {
		this.evenementListName = evenementList;
	}
	
	public List<String> getListEventExtractWithSplit() {
		return listEventExtractWithSplit;
	}

	public void setListEventExtractWithSplit(List<String> listEventExtractWithSplit) {
		this.listEventExtractWithSplit = listEventExtractWithSplit;
	}

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public List<Event> getDataList() {
		return dataList;
	}

	public void setDataList(List<Event> dataList) {
		this.dataList = dataList;
		
	}

	public Integer getEventTypeLength() {
		return eventTypeLength;
	}
	
	public void setEventTypeLength(Integer eventTypeLength) {
		this.eventTypeLength = eventTypeLength;
	}

	public String getEvenement() {
		return evenement;
	}

	public Integer getResultLenght() {
		return resultLenght;
	}

	public void setResultLenght(Integer resultLenght) {
		this.resultLenght = resultLenght;
	}

	
	public List<TraitementOfTypes>  getMapType() {
		return mapType;
	}

	public void setMapType(List<TraitementOfTypes> listType) {
		   
		this.mapType = listType;
		
	}

	public void setEvenement(String evenement) {
		this.evenement = evenement;
	}

	public Integer getIndex() {
		return index;
	}

	public void setIndex(Integer index) {
		this.index = index;
	}

	public String getStartDate() {
		return startDate;
	}

	public void setStartDate(String startDate) {
		this.startDate = startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}

	public List<String> extraireListeEvenementSplit(String extraireevenement) {
		if(extraireevenement.equals("")){
			return null;
		}else{
			return Arrays.asList(extraireevenement.split("[-]"));
		}

	}
	
	public List<String> getEventName(List<String> eventList) {
		
		this.listEventExtractWithSplit=eventList;
		 List<String> list=new ArrayList<String>();
		for (String event : eventList) {
			if(event.equals("MusicEvent")){
				list.add("Musique");
							
			}else if(event.equals("DanceEvent")){
				list.add( "Dance");
				
			}else if(event.equals("ChildrensEvent")){
				list.add("Enfants");
				
			}else if(event.equals("Festival")){
				list.add("Festival");
				
			}else if(event.equals("TheaterEvent")){
				list.add("Théâtre");
				
			}else if(event.equals("ComedyEvent")){
				list.add("Comédie");
				
			}else if(event.equals("SportsEvent")){
				list.add("Sport");
				
			}else if(event.equals("SocialEvent")){
				list.add("Social");
				
			}else if(event.equals("LiteraryEvent")){
				list.add("Littérature");
				
			}else if(event.equals("BusinessEvent")){
				list.add("Commerce");
				
			}else if(event.equals("DeliveryEvent")){
				list.add("Livraison");
				
			}else if(event.equals("EducationEvent")){
				list.add("Education");
				
			}else if(event.equals("ExhibitionEvent")){
				list.add("Exposition");
				
			}else if(event.equals("SaleEvent")){
				list.add("Vente");
				
			}else if(event.equals("FoodEvent")){
				list.add("Alimentaire");
				
			}else if(event.equals("PublicationEvent")){
				list.add("Publication");
				
			}else if(event.equals("VisualArtsEvent")){
				list.add("Art visuel");
			}
			
		}
		return list;
	}

}
