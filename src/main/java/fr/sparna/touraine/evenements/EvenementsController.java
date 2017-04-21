package fr.sparna.touraine.evenements;

import java.io.IOException;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

@Controller
public class EvenementsController {

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
		EvenementData data=new EvenementData();
		FormPost post;
		
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
		List<String>event=null;
		//extraction de la liste des évévements demandés
		if((evenement!=null)){
			event=data.extraireListeEvenementSplit(evenement);
			data.setEvenement(evenement);
		}else{
			data.setEvenement(null);
		}
		
		//plain text
		if((fulltextsearch==null)||(fulltextsearch.equals(""))){
			data.setFullText(null);
		}else{
			data.setFullText(fulltextsearch);
		}
		//connexion avec la base graphdb
		GraphDao dao=new GraphDao();
		dao.connexion();
		
		//recherche sur critère(date de début, date de fin,type d'évènements demandés)
		post=new FormPost(event,startDate,endDate,fulltextsearch);
		data.setIndex(index);
		data.setDataList(dao.listEvenement(post,index));
		data.setResultLenght(dao.getResultLength());
		return new ModelAndView("liste", EvenementData.KEY, data);
	}

}
