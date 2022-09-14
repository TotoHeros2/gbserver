package ch.hcuge.simed.gbserver.controllers;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.RelationType;
import ch.hcuge.simed.gbserver.model.RelationTypes;


@RestController
@CrossOrigin(origins = "*")
public class RelationTypeController {
	
	@RequestMapping(method = RequestMethod.GET,path = "/services/v2/pedigree/relation_types", produces="application/json")
	public RelationTypes getRelationTypes()
	{
		return new RelationTypes();
	}
	                
	
//	public List<RelationType> getRelationTypes()
//	{
//		RelationType relationType = new RelationType(1, "Parent", "PAR", false, false);
//		RelationType relationType2 = new RelationType(2, "Spouse", "SPO", false, true);
//
//		return Arrays.asList(relationType,relationType2);
//	}


}
