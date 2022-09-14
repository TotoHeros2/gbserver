package ch.hcuge.simed.gbserver.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

public class CountsPerStudyAndConcept {
	
	HashMap<String, HashMap<String,Count>> countsPerStudy = new HashMap<String, HashMap<String,Count>>();

	public HashMap<String, HashMap<String, Count>> getCountsPerStudy() {
		return countsPerStudy;
	}

	public void setCountsPerStudy(HashMap<String, HashMap<String, Count>> countsPerStudy) {
		this.countsPerStudy = countsPerStudy;
	}


	 


}
