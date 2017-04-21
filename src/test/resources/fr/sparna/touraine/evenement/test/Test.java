package fr.sparna.touraine.evenement.test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class Test {
	
	public String extraireevenement(String extraireevenement) {
		char[] tableau=extraireevenement.toCharArray();
		String url="<http://schema.org/";
		String event="";
		String listevent="";
		for (int i = 0; i < tableau.length; i++) {		
			if(tableau[i]!='-') {
				event+=tableau[i];
			} else {
				listevent+=url+event+">,";
				event="";
			}
		}
		// on rajoute le dernier au resultat, sans la virgule
		listevent+=url+event+">";
		
		return listevent;
	}
	
	public List<String> extraireListeEvenement(String extraireevenement) {
		char[] tableau=extraireevenement.toCharArray();
		String event="";
		List<String> listevent= new ArrayList<String>();
		for (int i = 0; i < tableau.length; i++) {		
			if(tableau[i]!='-') {
				event+=tableau[i];
			} else {
				listevent.add(event);
				event="";
			}
		}
		// on rajoute le dernier au resultat
		listevent.add(event);
		
		return listevent;
	}
	
	public List<String> extraireListeEvenementIndex(String extraireevenement) {
		
		List<String> listevent= new ArrayList<String>();
		int firstIndex=0;
		int indexFound=0;
		int beginIndex=0;
		int lastIndex=extraireevenement.lastIndexOf("-");
		System.out.println(lastIndex);
		for (int i = 0; i < 6; i++) {
			beginIndex=firstIndex+i;
			indexFound=extraireevenement.indexOf("-",beginIndex);
			System.out.println(indexFound);
			if((indexFound!=lastIndex)){
				if(indexFound!=-1){
					listevent.add(extraireevenement.substring(firstIndex,(indexFound)));
					firstIndex=indexFound+1;
				}else{
					break;
				}
			}else{
				listevent.add(extraireevenement.substring(firstIndex,(indexFound)));
				break;
			}
		}
		listevent.add(extraireevenement.substring(indexFound+1));
		
		return listevent;
	}
	
	public List<String> extraireListeEvenementSplit(String extraireevenement) {
		return Arrays.asList(extraireevenement.split("[-]");		
	}

}
