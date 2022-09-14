package ch.hcuge.simed.gbserver.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

public class Dimension implements Serializable {
	
	private String name;
	private String dimensionType;
	private String sortIndex;
	private String valueType;
	private List<Field> fields = new ArrayList<Field>();
	
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getDimensionType() {
		return dimensionType;
	}
	public void setDimensionType(String dimensionType) {
		this.dimensionType = dimensionType;
	}
	public String getSortIndex() {
		return sortIndex;
	}
	public void setSortIndex(String sortIndex) {
		this.sortIndex = sortIndex;
	}
	public String getValueType() {
		return valueType;
	}
	public void setValueType(String valueType) {
		this.valueType = valueType;
	}
	public List<Field> getFields() {
		return fields;
	}
	public void setFields(List<Field> fields) {
		this.fields = fields;
	}

	
}
