package ch.hcuge.simed.gbserver.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.Health;


@RestController
@CrossOrigin(origins = "*")
public class HealthController {

	@RequestMapping(method = RequestMethod.GET,path = "/services/health", produces="application/json")
	public ch.hcuge.simed.gbserver.model.Health getHealth()
	{
		return new Health();
	}



}
