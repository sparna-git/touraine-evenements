package fr.sparna.touraine.evenements;



import java.util.ArrayList;
import java.util.List;

import org.eclipse.rdf4j.model.Literal;
import org.eclipse.rdf4j.query.BindingSet;
import org.eclipse.rdf4j.query.QueryLanguage;
import org.eclipse.rdf4j.query.TupleQuery;
import org.eclipse.rdf4j.query.TupleQueryResult;
import org.eclipse.rdf4j.repository.Repository;
import org.eclipse.rdf4j.repository.RepositoryConnection;


public class SearchCheckedEventsDao implements DaoInterface{

	protected Integer resultLength;

	protected List<TraitementOfTypes>typeNumber;

	protected Integer sizetypeNumber;

	private Integer compteur=0;

	private Repository repository;

	public SearchCheckedEventsDao(GraphConnexion graphConnexion) {
		super();
		this.repository = graphConnexion.createRepository();
	}
	
	@Override
	public Integer getResultLength() {
		return resultLength;
	}
	/**
	 * définis la taille du resultat d'une recherche donnée
	 * @param resultLength
	 */
	public void setResultLength(Integer resultLength) {
		this.resultLength = resultLength;
	}

	@Override
	public List<TraitementOfTypes> getTypeNumberList() {
		return typeNumber;
	}
	/**
	 * définit pour chaque type d'évènement son nombre.Elle prend en paramètre une liste de type TraitementOfTypes
	 * @param typeNumber
	 */
	public void setTypeNumberList(List<TraitementOfTypes> typeNumber) {

		this.typeNumber = typeNumber;
		for (TraitementOfTypes traitementDesTypes : typeNumber) {
			if(traitementDesTypes==null){
				compteur++;
			}
		}
		this.sizetypeNumber=(typeNumber.size()-compteur);
		compteur=0;
	}

	@Override
	public Integer getSizetypeNumber() {
		return sizetypeNumber;
	}
	/**
	 * renvoi la requete sparql a exécuter pour avoir la taille des résultats
	 * @return
	 */
	private String getSparqlSizeRequest(){
		String req="PREFIX schema: <http://schema.org/>"+
				"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+"PREFIX luc: <http://www.ontotext.com/owlim/lucene#>"+

		"SELECT (COUNT(?name) AS ?count)"+
		"WHERE {"+
		"{"+
		"SELECT DISTINCT"+
		"?type"+
		"?name "+ 
		"WHERE {"+
		"?x rdf:type schema:Event ."+

			   " GRAPH ?g {"+

			    	"?x schema:name ?name ."
			    	+

			        "?x rdf:type ?type ."+
			        " OPTIONAL {?x schema:startDate ?startDate . }"+

			    	"OPTIONAL {?x schema:endDate ?endDate . }"+

			       " OPTIONAL {?x schema:location/schema:name ?lieu}"+

					"OPTIONAL {\n"
					+ "?x schema:location/schema:address ?a .\n"
					+ "?a schema:streetAddress ?streetAddress .\n"
					+ "?a schema:postalCode ?postalCode .\n"
					+ "?a schema:addressLocality ?addressLocality .\n"
					+ "BIND (CONCAT(STR(?streetAddress) , STR(?postalCode), STR(?addressLocality)) AS ?adresseString)\n"+
					"}"+
					"OPTIONAL {?x schema:image ?i}\n"
					+ "					OPTIONAL {?x schema:description ?d}\n"
					+ "					OPTIONAL {\n"
					+ "							  ?x schema:location ?loc .\n"
					+ "				  			  ?loc schema:geo/schema:latitude ?lat .\n"
					+ "							  ?loc schema:geo/schema:longitude ?long .\n"
					+ "				             }\n"+
					"}"+

				"BEGINDATE"+"\n"
				+ "ENDDATE"+"\n"
				+ "EVENTCHOOSE"+"\n"

				+ "}\n"
				+ " GROUP BY ?type ?name\n"
				+ " ORDER BY (STR(?startDate))\n"+
				"}"+
				"}";
		return req;
	}

	/**
	 * renvoi la requête sparql à exécuter pour avoir pour chaque type d'évènement son nombre
	 * @return
	 */
	private String getSparqlRequestTypeNumber(){

		String req="PREFIX schema: <http://schema.org/>"+
				"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"+
				"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
				+"PREFIX luc: <http://www.ontotext.com/owlim/lucene#>"+

		"SELECT ?type (COUNT(?name) AS ?count)"+
		"WHERE {"+
		"{"+
		"SELECT DISTINCT"+
		"?type"+
		"?name "+ 
		"WHERE {"+
		"?x rdf:type schema:Event ."+

			   " GRAPH ?g {"+

			    	"?x schema:name ?name ."
			    	+

			        "?x rdf:type ?type ."+
			        " OPTIONAL {?x schema:startDate ?startDate . }"+

			    	"OPTIONAL {?x schema:endDate ?endDate . }"+

			       " OPTIONAL {?x schema:location/schema:name ?lieu}"+

					"OPTIONAL {\n"
					+ "?x schema:location/schema:address ?a .\n"
					+ "?a schema:streetAddress ?streetAddress .\n"
					+ "?a schema:postalCode ?postalCode .\n"
					+ "?a schema:addressLocality ?addressLocality .\n"
					+ "BIND (CONCAT(STR(?streetAddress) , STR(?postalCode), STR(?addressLocality)) AS ?adresseString)\n"+
					"}"+
					"OPTIONAL {?x schema:image ?i}\n"
					+ "					OPTIONAL {?x schema:description ?d}\n"
					+ "					OPTIONAL {\n"
					+ "							  ?x schema:location ?loc .\n"
					+ "				  			  ?loc schema:geo/schema:latitude ?lat .\n"
					+ "							  ?loc schema:geo/schema:longitude ?long .\n"
					+ "				             }\n"+
					"}"+

				"BEGINDATE"+"\n"
				+ "ENDDATE"+"\n"
				+ "EVENTCHOOSE"+"\n"

				+ "}\n"
				+ " GROUP BY ?type ?name\n"
				+ 
				"}"+
				"}"
				+ "GROUP BY ?type ORDER BY DESC(?count)";
		return req;

	}

	@Override
	public List<Event> getEvenementList(FormPost object, Integer offset) {
		List<Event> resultList=new ArrayList<Event>();
		EvenementSourceDescriptionLimit tronquer=null;
		try (RepositoryConnection repositoryConnection=repository.getConnection()) {
			String querySizeResult=getSparqlSizeRequest();
			String queryStringTypeNumber=getSparqlRequestTypeNumber();
			String queryString = "PREFIX schema: <http://schema.org/>"
					+"PREFIX skos: <http://www.w3.org/2004/02/skos/core#>"
					+"PREFIX foaf: <http://xmlns.com/foaf/0.1/>"
					+"PREFIX luc: <http://www.ontotext.com/owlim/lucene#>"
					+"SELECT DISTINCT ?type ?name \n"
					+ "(SAMPLE(?startDate) AS ?start)\n"
					+ "(SAMPLE(?endDate) AS ?end)\n"
					+ "(SAMPLE(?lieu) AS ?location)\n"
					+ "(SAMPLE(?adresseString) AS ?adresse)\n"
					+ "(SAMPLE(?i) AS ?image)\n"
					+ "(SAMPLE(?d) AS ?description)\n"
					+ "(SAMPLE(?lat) AS ?latitude)\n"
					+ "(SAMPLE(?long) AS ?longitude)\n"
					+ "(GROUP_CONCAT(?g) AS ?sources)\n"
					+ 		"WHERE { \n"
					+ "				 ?x rdf:type schema:Event .\n"
					+ "				 GRAPH ?g {\n"
					+ "				 ?x schema:name ?name .\n"
					+ "				 ?x rdf:type ?type .\n"
					+ "				 OPTIONAL {?x schema:startDate ?startDate . }\n"
					+ "				 OPTIONAL {?x schema:endDate ?endDate . }\n"
					+ "				 OPTIONAL {?x schema:location/schema:name ?lieu}\n"
					+ "				 OPTIONAL {\n"
					+ "							?x schema:location/schema:address ?a .\n"
					+ "							?a schema:streetAddress ?streetAddress .\n"
					+ "							?a schema:postalCode ?postalCode .\n"
					+ "							?a schema:addressLocality ?addressLocality .\n"
					+ "				BIND (CONCAT(STR(?streetAddress) , STR(?postalCode), STR(?addressLocality)) AS ?adresseString)\n"
					+ "}"
					+ "				    OPTIONAL {?x schema:image ?i}\n"
					+ "					OPTIONAL {?x schema:description ?d}\n"
					+ "					OPTIONAL {\n"
					+ "							  ?x schema:location ?loc .\n"
					+ "				  			  ?loc schema:geo/schema:latitude ?lat .\n"
					+ "							  ?loc schema:geo/schema:longitude ?long .\n"
					+ "				             }\n"
					+ "        }\n"

					+ "BEGINDATE"+"\n"
					+ "ENDDATE"+"\n"
					+ "EVENTCHOOSE"+"\n"

				    + "}\n"
				    + " GROUP BY ?type ?name\n"
				    + " ORDER BY (STR(?startDate))\n"
				    + "LIMIT 10 OFFSET "+offset;

			//recupère le filter sur evenements
			String filterListEvenement=null;
			if(object.evenement!=null){
				filterListEvenement=filterEvenementList(object.evenement);
			}
			//date debut
			if((object.startDate==null)||(object.startDate.equals(""))){
				queryString=queryString.replaceAll("BEGINDATE", "");
				//string requête pour avoir le nombre de résultat total pour une recherche
				querySizeResult=querySizeResult.replaceAll("BEGINDATE", "");
				//string requête pour avoir pour le nombre total pour chaque type d'événement présent
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("BEGINDATE", "");

			}else{
				queryString=queryString.replaceAll("BEGINDATE", "FILTER(STR(?startDate) >\""+object.startDate+"\")");
				querySizeResult=querySizeResult.replaceAll("BEGINDATE", "FILTER(STR(?startDate) >\""+object.startDate+"\")");
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("BEGINDATE", "FILTER(STR(?startDate) >\""+object.startDate+"\")");
			}
			//date de fin
			if((object.endDate==null)||(object.endDate.equals(""))){
				queryString=queryString.replaceAll("ENDDATE",  "");
				querySizeResult=querySizeResult.replaceAll("ENDDATE",  "");
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("ENDDATE",  "");
			}else{
				queryString=queryString.replaceAll("ENDDATE",  "FILTER(STR(?startDate) <\""+object.endDate+"\")");
				querySizeResult=querySizeResult.replaceAll("ENDDATE",  "FILTER(STR(?startDate) <\""+object.endDate+"\")");
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("ENDDATE",  "FILTER(STR(?startDate) <\""+object.endDate+"\")");
			}

			//filtrage sur evenement
			if((object.evenement==null)||(object.evenement.isEmpty())){
				queryString=queryString.replaceAll("EVENTCHOOSE", "");
				querySizeResult=querySizeResult.replaceAll("EVENTCHOOSE", "");
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("EVENTCHOOSE", "");
			}else{
				queryString=queryString.replaceAll("EVENTCHOOSE", filterListEvenement);
				querySizeResult=querySizeResult.replaceAll("EVENTCHOOSE", filterListEvenement);
				queryStringTypeNumber=queryStringTypeNumber.replaceAll("EVENTCHOOSE", filterListEvenement);
			}

			setResultLength(getSizeOfEvents(querySizeResult, repositoryConnection));
			setTypeNumberList(getNumberOfEachEventType(queryStringTypeNumber,repositoryConnection));
			System.out.println(queryString);
			TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, queryString);
			Event event=null;
			String valueOfSource=null;
			String valueOfDescription =null;

			//lecture et récupération du resultat
			try (TupleQueryResult result = tupleQuery.evaluate()) {
				while (result.hasNext()) {  // iterate over the result

					event=new Event();
					BindingSet bindingSet = result.next();
					if(bindingSet.getValue("type")!=null){
						String valueOfType = bindingSet.getValue("type").stringValue();
						TraitementOfTypes tr=new TraitementOfTypes();
						tr.setMapType(valueOfType);
						event.setType(tr.nom);
					}else{
						event.setType("");
					}
					if(bindingSet.getValue("name")!=null){
						String valueOfName = ((Literal)bindingSet.getValue("name")).stringValue();
						event.setNom(valueOfName);
					}else{
						event.setNom("");
					}
					if(bindingSet.getValue("end")!=null){
						String  end = ((Literal)bindingSet.getValue("end")).stringValue();
						event.setDatefin(end);
					}else{
						event.setDatefin("");
					}
					if(bindingSet.getValue("start")!=null){
						String  start = ((Literal)bindingSet.getValue("start")).stringValue();
						event.setDatedeb(start);
					}else{
						event.setDatedeb("");
					}
					if(bindingSet.getValue("location")!=null){
						String valueOfLocation = ((Literal)bindingSet.getValue("location")).stringValue();
						event.setLocation(valueOfLocation);
					}else{
						event.setLocation("");
					}
					if(bindingSet.getValue("adresse")!=null){
						String valueOfAdresse = ((Literal)bindingSet.getValue("adresse")).stringValue();
						event.setAdresse(valueOfAdresse);
					}else{
						event.setAdresse("");
					}					
					if((bindingSet.getValue("image"))!=null){
						String valueOfImage = (bindingSet.getValue("image")).stringValue();
						event.setImagePath(valueOfImage);


					}else{
						event.setImagePath(null);
					}
					if((bindingSet.getValue("description"))!=null){
						valueOfDescription = (bindingSet.getValue("description")).stringValue();
					}else{
						event.setDescription("");
					}
					if((bindingSet.getValue("latitude"))!=null){
						String valueOfLatitude = ((Literal)bindingSet.getValue("latitude")).stringValue();
						event.setLatitude(valueOfLatitude);
					}else{
						event.setLatitude("");
					}
					if((bindingSet.getValue("longitude"))!=null){
						String valueOfLongitude= ((Literal)bindingSet.getValue("longitude")).stringValue();
						event.setLongitude(valueOfLongitude);
					}else{
						event.setLongitude("");
					}
					if(bindingSet.getValue("sources")!=null){
						valueOfSource = ((Literal)bindingSet.getValue("sources")).stringValue();
					}else{
						event.setSources(null);
					}
					//tronquer la description et la source avant de retourner la liste
					if(valueOfDescription!=null){
						tronquer=new EvenementSourceDescriptionLimit(valueOfDescription,valueOfSource);
					}else{
						tronquer=new EvenementSourceDescriptionLimit("",valueOfSource);
					}

					if(tronquer.getDescription()!=null){
						event.setDescription(tronquer.getDescription());
					}else{
						event.setDescription(null);
					}
					if(tronquer.getSource()!=null){
						event.setSources(tronquer.getSource());
					}else{
						event.setSources(null);
					}
					if(tronquer.getSourceSize()!=null){
						event.setSourcesize(tronquer.getSourceSize());
					}else{
						event.setSourcesize(0);
					}


					resultList.add(event);

				}
			}


		}
		System.out.println(resultList);
		return resultList;
	}


	@Override
	public String filterEvenementList(List<String> listEvenement) {
		// TODO Auto-generated method stub
		int lastcomaIndex=0;
		String filterListEvent="FILTER(?type IN(";
		for (String event : listEvenement) {
			String filter="<http://schema.org/"+event+">";
			filterListEvent+=filter+",";

		}
		lastcomaIndex=filterListEvent.lastIndexOf(",");
		filterListEvent=filterListEvent.substring(0, lastcomaIndex);
		filterListEvent+="))";

		return filterListEvent;
	}

	public Integer getSizeOfEvents(String request, RepositoryConnection repositoryConnection) {
		TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, request);
		Integer value=0;
		try (TupleQueryResult result = tupleQuery.evaluate()) {
			while (result.hasNext()) {  // iterate over the result
				BindingSet bindingSet = result.next();
				if(bindingSet.getValue("count")!=null){
					value =( (Literal)bindingSet.getValue("count")).intValue();
					System.out.println(value);
				}
			}
		}
		return value;
	}

	public List<TraitementOfTypes> getNumberOfEachEventType(String req, RepositoryConnection repositoryConnection) {
		TupleQuery tupleQuery = repositoryConnection.prepareTupleQuery(QueryLanguage.SPARQL, req);
		Integer value=0;
		String type=null;
		List<TraitementOfTypes> listType=new ArrayList<TraitementOfTypes>();
		try (TupleQueryResult result = tupleQuery.evaluate()) {
			while (result.hasNext()) {  // iterate over the result
				BindingSet bindingSet = result.next();
				TraitementOfTypes tr=new TraitementOfTypes();
				value =((Literal)bindingSet.getValue("count")).intValue();
				if(bindingSet.getValue("type")!=null){
					type = bindingSet.getValue("type").stringValue();
					if(type.equals("http://schema.org/Event") || type.equals("http://schema.org/VisuelArtsEvent")){
						listType.add(null);
					}else{

						tr.setMapType(type);
						tr.setNombre(value);
						listType.add(tr);
					}
				}
				continue;


			}
		}
		return listType;
	}

}

