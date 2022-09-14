package ch.hcuge.simed.gbserver.model;

import java.util.HashMap;

public class CountsPerConcept {
	 HashMap<String,Count> countsPerConcept = new HashMap<String, Count>();

	public HashMap<String, Count> getCountsPerConcept() {
		return countsPerConcept;
	}

	public void setCountsPerConcept(HashMap<String, Count> countsPerConcept) {
		this.countsPerConcept = countsPerConcept;
	}
	 
	 

}
