package fr.sparna.touraine.evenements;

import java.util.List;

public class FormPost {

	protected List<String> evenement;

	protected String startDate;

	protected String endDate;

	protected EventData data;

	protected String searchFullText;


	public FormPost(List<String> evenement, String startDate, String endDate,String fulltextsearch) {
		super();

		this.evenement = evenement;
		this.startDate = startDate;
		this.endDate = endDate;
		this.searchFullText=fulltextsearch;
	}

	public List<String> getEvenement() {
		return evenement;
	}

	public String getStartDate() {
		return startDate;
	}

	public String getEndDate() {
		return endDate;
	}

	public String getSearchFullText() {
		return searchFullText;
	}





}
