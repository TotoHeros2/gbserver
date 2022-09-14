package ch.hcuge.simed.gbserver.model;

import java.io.Serializable;

public class Count implements Serializable {
	private int observationCount;
	private int patientCount;
	
	public Count() {
		super();
	}
		
	public Count(int observationCount, int patientCount) {
		super();
		this.observationCount = observationCount;
		this.patientCount = patientCount;
	}
	public int getObservationCount() {
		return observationCount;
	}
	public void setObservationCount(int observationCount) {
		this.observationCount = observationCount;
	}
	public int getPatientCount() {
		return patientCount;
	}
	public void setPatientCount(int patientCount) {
		this.patientCount = patientCount;
	}
	
	

}
