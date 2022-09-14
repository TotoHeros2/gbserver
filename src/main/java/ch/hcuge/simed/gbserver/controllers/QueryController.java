package ch.hcuge.simed.gbserver.controllers;

import java.util.ArrayList;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.Queries;
import ch.hcuge.simed.gbserver.model.Query;


@RestController
@CrossOrigin(origins = "*")
public class QueryController {
	
	@RequestMapping(method = RequestMethod.GET,path = "/services/queries", produces="application/json")
	public Queries getQueries()
	{
		return new Queries();
	}

}
