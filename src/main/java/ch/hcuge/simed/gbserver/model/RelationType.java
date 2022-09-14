package ch.hcuge.simed.gbserver.model;

import java.io.Serializable;

public class RelationType implements Serializable {
	private int id;
	private String description;
	private String label;
	private boolean biological;
	private boolean symmetrical;
	
	
	
	public RelationType(int id, String description, String label, boolean biological, boolean symmetrical) {
		super();
		this.id = id;
		this.description = description;
		this.label = label;
		this.biological = biological;
		this.symmetrical = symmetrical;
	}
	
	public RelationType() {
		super();
		// TODO Auto-generated constructor stub
	}

	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getLabel() {
		return label;
	}
	public void setLabel(String label) {
		this.label = label;
	}
	public boolean isBiological() {
		return biological;
	}
	public void setBiological(boolean biological) {
		this.biological = biological;
	}
	public boolean isSymmetrical() {
		return symmetrical;
	}
	public void setSymmetrical(boolean symmetrical) {
		this.symmetrical = symmetrical;
	}



}
