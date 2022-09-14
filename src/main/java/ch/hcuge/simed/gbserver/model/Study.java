package ch.hcuge.simed.gbserver.model;

import java.io.Serializable;
import java.util.ArrayList;

public class Study implements Serializable{
	private int id;
	private String studyId;
	private String[] dimensions = new String[] {"patient","measurement","observation"};
	
	
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getStudyId() {
		return studyId;
	}
	public void setStudyId(String studyId) {
		this.studyId = studyId;
	}
	public String[] getDimensions() {
		return dimensions;
	}
	public void setDimensions(String[] dimensions) {
		this.dimensions = dimensions;
	}
	
	

}
