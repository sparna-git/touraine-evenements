package fr.sparna.touraine.evenements;

import java.util.Arrays;
import java.util.List;

public class EvenementData {
	public static final String KEY = EvenementData.class.getCanonicalName();

	protected List<Evenement> dataList;

	protected String evenement;

	protected String startDate;

	protected String endDate;

	protected Integer index;

	protected Integer resultLenght;
	
	protected String fullText;
	
	protected List<String> listEvent;
	
	

	public String getFullText() {
		return fullText;
	}

	public void setFullText(String fullText) {
		this.fullText = fullText;
	}

	public List<Evenement> getDataList() {
		return dataList;
	}

	public void setDataList(List<Evenement> dataList) {
		this.dataList = dataList;
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
			this.listEvent=Arrays.asList(extraireevenement.split("[-]"));
			return Arrays.asList(extraireevenement.split("[-]"));
		}

	}

}
