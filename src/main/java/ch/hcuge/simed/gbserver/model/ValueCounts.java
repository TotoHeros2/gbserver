package ch.hcuge.simed.gbserver.model;

import java.util.HashMap;

public class ValueCounts {
	private HashMap<String, Integer> valueCounts = new HashMap<String, Integer>();

	public HashMap<String, Integer> getValueCounts() {
		return valueCounts;
	}

	public void setValueCounts(HashMap<String, Integer> valueCounts) {
		this.valueCounts = valueCounts;
	}

}
