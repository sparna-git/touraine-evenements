package fr.sparna.touraine.evenements;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EventController {
	
	@Autowired
	private DaoInterface daoCkeckedEvent;
	@Autowired
	private DaoInterface searchtext;
	
	@RequestMapping("liste")
	public ModelAndView listForm(
			//paramètres à récupérer
			HttpServletRequest request,
			HttpServletResponse response,
			@RequestParam(value="evenement",  required=false) String evenement,
			@RequestParam(value="deb",  required=false) String startDate,
			@RequestParam(value="fin",  required=false) String endDate,
			@RequestParam(value="zonesearch", required=false) String fulltextsearch,
			@RequestParam(value="index",  defaultValue="0",required=false) Integer index
	) throws IOException  {
		
		EventData data=new EventData();
		FormPost post = null;
		List<Event> eventList = null;
		List<String>event=null;
		Integer resultLength=0;
		Integer listOfTypeSize=0;
		List<TraitementOfTypes> listOfType=null;
		
		data.setIndex(index);
		//définir la date de début pour une recherche d'événement
		if((startDate==null)||(startDate.equals(""))){
			data.setStartDate(null);
		}else{
			data.setStartDate(startDate);
		}
		//définir la date de fin pour une recherche d'événement
		if((endDate==null)||(endDate.equals(""))){
			data.setEndDate(null);
		}else{
			data.setEndDate(endDate);
		}

		//extraction de la liste des évévements demandés
		if((evenement==null)||(evenement.equals(""))){
			data.setEvenement(null);

		}else{
			event=data.extraireListeEvenementSplit(evenement);
			data.setEvenement(evenement);
			data.setEvenementListName(data.getEventName(event));
		}
		//recherche sur critère(date de début, date de fin,type d'évènements demandés)
		post=new FormPost(event,startDate,endDate,fulltextsearch);

		//récupération de la liste des évènements sur critères choisis
		if((fulltextsearch==null)||(fulltextsearch.equals(""))){
			data.setFullText(null);
			eventList=daoCkeckedEvent.getEvenementList(post,index);
			resultLength=daoCkeckedEvent.getResultLength();
			listOfType=daoCkeckedEvent.getTypeNumberList();
			listOfTypeSize=daoCkeckedEvent.getSizetypeNumber();
		}else{
			//récupération de la liste des événements pour une recherche full text
			data.setFullText(fulltextsearch);
			eventList=searchtext.getEvenementList(post, index);
			resultLength=searchtext.getResultLength();
			listOfType=searchtext.getTypeNumberList();
			listOfTypeSize=searchtext.getSizetypeNumber();
		}		
		
		data.setDataList(eventList);
		data.setResultLenght(resultLength);
		data.setMapType(listOfType);
		data.setEventTypeLength(listOfTypeSize);
		return new ModelAndView("liste", EventData.KEY, data);
	}

}
