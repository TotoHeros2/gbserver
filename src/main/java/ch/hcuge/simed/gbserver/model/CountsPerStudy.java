package ch.hcuge.simed.gbserver.model;

import java.util.HashMap;

public class CountsPerStudy {
	 HashMap<String,Count> countsPerStudy = new HashMap<String, Count>();

	public HashMap<String, Count> getCountsPerStudy() {
		return countsPerStudy;
	}

	public void setCountsPerStudy(HashMap<String, Count> countsPerStudy) {
		this.countsPerStudy = countsPerStudy;
	}

}
