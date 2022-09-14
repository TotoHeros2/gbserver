package ch.hcuge.simed.gbserver.model;

import java.util.HashMap;

public class AggregatesPerCategoricalConcept {
	
	private HashMap<String, ValueCounts> aggregatesPerCategoricalConcept = new HashMap<String, ValueCounts>();
	private int nullValueCounts = 0;



	public HashMap<String, ValueCounts> getAggregatesPerCategoricalConcept() {
		return aggregatesPerCategoricalConcept;
	}

	public void setAggregatesPerCategoricalConcept(HashMap<String, ValueCounts> aggregatesPerCategoricalConcept) {
		this.aggregatesPerCategoricalConcept = aggregatesPerCategoricalConcept;
	}

	public int getNullValueCounts() {
		return nullValueCounts;
	}

	public void setNullValueCounts(int nullValueCounts) {
		this.nullValueCounts = nullValueCounts;
	}
	
}
