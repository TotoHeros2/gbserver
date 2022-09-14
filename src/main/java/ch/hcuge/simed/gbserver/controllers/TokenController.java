package ch.hcuge.simed.gbserver.controllers;


import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.api.FakeToken;


@RestController
@CrossOrigin(origins = "*")
public class TokenController {

	
	@RequestMapping(method = RequestMethod.POST,path = "/token", produces="application/json")
	public FakeToken getToken()
	{
		return new FakeToken();
	}
	
//	@RequestMapping(method = RequestMethod.GET,path = "/auth", produces="application/json")
//	public FakeToken getAuth()
//	{
//		return new FakeToken();
//	}
}
