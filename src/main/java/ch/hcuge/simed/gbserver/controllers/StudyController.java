package ch.hcuge.simed.gbserver.controllers;

import java.util.Arrays;
import java.util.List;

import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import ch.hcuge.simed.gbserver.model.Studies;
import ch.hcuge.simed.gbserver.model.Study;


@RestController
@CrossOrigin(origins = "*")
public class StudyController {
	
	@RequestMapping(method = RequestMethod.GET,path = "/services/v2/studies", produces="application/json")
	public Studies getStudies()
	{
		Studies studies = new Studies();
		Study study = new Study();
		study.setId(1);
		study.setStudyId("TEST");
		studies.getStudies().add(study);
		return studies;
	}
//	public List<Study> getStudies()
//	{
//		Study study = new Study();
//		study.setId(1);
//		study.setStudyId("TEST");
//		
//		return Arrays.asList(study);
//	}
	

}
