package ch.hcuge.simed.gbserver.controllers;

import java.sql.DriverManager;
import java.util.HashMap;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;

import org.hug.simed.omop.store.IStoreConfiguration;
import org.hug.simed.omop.store.IStoreProvider;
import org.hug.simed.omop.store.Store;
import org.json.JSONObject;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.style.ValueStyler;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;

import ch.hcuge.simed.gbserver.model.AggregatesPerCategoricalConcept;
import ch.hcuge.simed.gbserver.model.Count;
import ch.hcuge.simed.gbserver.model.CountsPerConcept;
import ch.hcuge.simed.gbserver.model.CountsPerStudy;
import ch.hcuge.simed.gbserver.model.CountsPerStudyAndConcept;
import ch.hcuge.simed.gbserver.model.ValueCounts;

import org.hug.simed.omop.store.constraint.Constraint;
import org.hug.simed.omop.store.constraint.ConstraintHelper;



@RestController
@CrossOrigin(origins = "*")
public class ObservationController {
    private static Logger logger = LoggerFactory.getLogger(ObservationController.class);
    
    private IStoreProvider store;
    
    private Gson gson;
	
    public ObservationController() {
		logger.info("ObservationController const ");


    	try {
    		DriverManager.registerDriver(new com.clickhouse.jdbc.ClickHouseDriver());
    	}
    	catch(java.sql.SQLException e){
    		e.printStackTrace();
    	}


    	this.store = new Store(new IStoreConfiguration() {
    		@Override
    		public String getConnectionUrl() {
    			return  "jdbc:ch://vmld-01523.huge.ad.hcuge.ch:8123/hugdata_r";
    		}

    		@Override
    		public Properties getConnectionProperties() {
    			try {
    				final Properties p = new Properties();

    				p.put("user", "hugdata_r_r");
    				p.put("password", "hugdata_r_r");
    				p.put("socket_timeout", "300000");

    				return p;
    			} catch(Exception e) {
    				e.printStackTrace();
    			}
    			return null;
    		}
    	});

    	gson = new Gson();

    }


	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/counts_per_concept", produces="application/json")
	public /*CountsPerConcept*/ Map<String, ?>  countsPerConcept(@RequestBody Object constraint)
	{
		logger.info("countsPerConcept - Recu : " + constraint.toString());
		
		try {
			return store.query_counts_per_concept(new JSONObject(gson.toJson(constraint))).toMap();
		} catch (Exception e) {
		      logger.error("While query counts", e);
		      return new JSONObject("{\"err\":\""+e.getMessage()+"\"}").toMap();
		}
//		CountsPerConcept countsPerConcept = new CountsPerConcept();
//		
//		Count count = new Count(100,100);
//		countsPerConcept.getCountsPerConcept().put("patient.age", count);
//		count = new Count(15,7);
//		countsPerConcept.getCountsPerConcept().put("patient.gender", count);
//		
//		count = new Count(15,7);
//		countsPerConcept.getCountsPerConcept().put("44029006", count);
//		return countsPerConcept;
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/counts_per_study", produces="application/json")
	public /*CountsPerStudy*/ Map<String, ?> countsPerStudy(@RequestBody Object constraint)
	{
		logger.info("countsPerStudy - Recu : " + constraint.toString());

		try {
			return store.query_counts_per_study(new JSONObject(gson.toJson(constraint))).toMap();
		} catch (Exception e) {
		      logger.error("While query counts", e);
		      return new JSONObject("{\"err\":\""+e.getMessage()+"\"}").toMap();
		}
//		CountsPerStudy countsPerStudy = new CountsPerStudy();
//		
//		Count count = new Count(100,100);
//		countsPerStudy.getCountsPerStudy().put("TEST", count);
////		count = new Count(15,7);
////		countsPerConcept.getCountsPerConcept().put("Biomaterial.biomaterial_date", count);
//		return countsPerStudy;
	}

	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/counts_per_study_and_concept", produces="application/json")
	public /*CountsPerStudyAndConcept*/ Map<String, ?> countsPerStudyAndConcept(@RequestBody Object constraint)
	{
		logger.info("countsPerStudyAndConcept - Recu : " + constraint.toString());

		try {
			return store.query_counts_per_study_and_concept(new JSONObject(gson.toJson(constraint))).toMap();
		} catch (Exception e) {
		      logger.error("While query counts", e);
		      return new JSONObject("{\"err\":\""+e.getMessage()+"\"}").toMap();
		}

//		HashMap<String,HashMap<String,Count>> hashMap1 = new HashMap<String, HashMap<String,Count>>();
//		HashMap<String,Count> countsPerConcept = new HashMap<String,Count>();
//
//		Count count = new Count(100,100);
//		countsPerConcept.put("patient.gender", count);
//		
//		count = new Count(20,20);
//		countsPerConcept.put("patient.age", count);
//		
//		count = new Count(20,20);
//		countsPerConcept.put("9999", count);
//				
//		hashMap1.put("TEST", countsPerConcept);
//
//
//		CountsPerStudyAndConcept countsPerStudy = new CountsPerStudyAndConcept();
//		countsPerStudy.setCountsPerStudy(hashMap1);
//
//		return countsPerStudy;
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/counts", produces="application/json")
	public /*Count*/ Map<String, ?> counts(@RequestBody Object constraint)
	{
//		LinkedHashMap<String, LinkedHashMap<String, String>> constraintmap = ((LinkedHashMap<String , LinkedHashMap<String,String>>)constraint);

//		constraint.
//		ConstraintHelper.
//		final Constraint constraint2 = ConstraintHelper.parse(new JSONObject(st));
//		JSONObject constraint2 = ConstraintHelper.to_json((Constraint) constraint);
//		return new Count (120657,1357);	
		logger.info("counts - Recu : " + constraint.toString());
//		logger.info("gson : " + gson.toJson(constraint));
//		logger.info("json send : " + (new JSONObject(gson.toJson(constraint))).toString());
		try {
			return store.query_counts(new JSONObject(gson.toJson(constraint))).toMap();
		} catch (Exception e) {
		      logger.error("While query counts", e);
		      return new JSONObject("{\"err\":\""+e.getMessage()+"\"}").toMap();
		}
	}
	
	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/aggregates_per_categorical_concept", produces="application/json")
	public Map<String, ?> aggregatesPerCategoricalConcept(@RequestBody Object constraint) // {constraint={type=concept, conceptCode=9999}}
	{
		logger.info("aggregatesPerCategoricalConcept - Recu : " + constraint.toString());

		LinkedHashMap<String,String> constraints = ((LinkedHashMap<String , LinkedHashMap<String,String>>)constraint).get("constraint");
		String conceptCode = constraints.get("conceptCode");
		
		JSONObject response = null;
//		AggregatesPerCategoricalConcept toReturn = null;
		try {
			response = store.query_aggregates_per_categorical_concept(conceptCode);
//			ObjectMapper mapper = new ObjectMapper();
//			toReturn = mapper.readValue(response.toString(), AggregatesPerCategoricalConcept.class);
			return response.toMap();
		} catch (Exception e) {
		      logger.error("While query counts", e);
		      return new JSONObject("{\"err\":\""+e.getMessage()+"\"}").toMap();

		}
	}
	
	
//	@RequestMapping(method = RequestMethod.POST,path = "/services/v2/observations/aggregates_per_categorical_concept", produces="application/json")
//	public AggregatesPerCategoricalConcept aggregatesPerCategoricalConcept(@RequestBody Object constraint) // {constraint={type=concept, conceptCode=9999}}
//	{
//		LinkedHashMap<String,String> concepts = ((LinkedHashMap<String , LinkedHashMap<String,String>>)constraint).get("constraint");
//		String conceptCode = concepts.get("conceptCode");
//		AggregatesPerCategoricalConcept aggregatesPerCategoricalConcept = new AggregatesPerCategoricalConcept();
//		
//		ValueCounts valueCounts = new ValueCounts();
//		HashMap<String, Integer> intValue = new HashMap<String, Integer>();
//		intValue.put("F", 30);
//		intValue.put("M", 34);
//		intValue.put("I", 3);	
//		valueCounts.setValueCounts(intValue);
//		
//		aggregatesPerCategoricalConcept.getAggregatesPerCategoricalConcept().put(conceptCode, valueCounts);
//		return aggregatesPerCategoricalConcept;
//	}

}
