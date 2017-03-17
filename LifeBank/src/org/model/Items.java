package org.model;

/**
 * Items entity. @author MyEclipse Persistence Tools
 */

public class Items implements java.io.Serializable {

	// Fields

	private Long id;
	private String LLowAlarm;
	private String LHighAlarm;
	private String THAlarm;
	private String THhAlarm;
	private String level;
	private String temperature1;
	private String temperature2;
	private String temperature3;
	private String temperature4;
	private String vesselTemp;
	private String sampleTemp;
	private String serial;
	private Integer clock;

	// Constructors

	/** default constructor */
	public Items() {
	}

	/** full constructor */
	public Items(String LLowAlarm, String LHighAlarm, String THAlarm,
			String THhAlarm, String level, String temperature1,
			String temperature2, String temperature3, String temperature4,
			String vesselTemp, String sampleTemp, String serial, Integer clock) {
		this.LLowAlarm = LLowAlarm;
		this.LHighAlarm = LHighAlarm;
		this.THAlarm = THAlarm;
		this.THhAlarm = THhAlarm;
		this.level = level;
		this.temperature1 = temperature1;
		this.temperature2 = temperature2;
		this.temperature3 = temperature3;
		this.temperature4 = temperature4;
		this.vesselTemp = vesselTemp;
		this.sampleTemp = sampleTemp;
		this.serial = serial;
		this.clock = clock;
	}

	// Property accessors

	public Long getId() {
		return this.id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLLowAlarm() {
		return this.LLowAlarm;
	}

	public void setLLowAlarm(String LLowAlarm) {
		this.LLowAlarm = LLowAlarm;
	}

	public String getLHighAlarm() {
		return this.LHighAlarm;
	}

	public void setLHighAlarm(String LHighAlarm) {
		this.LHighAlarm = LHighAlarm;
	}

	public String getTHAlarm() {
		return this.THAlarm;
	}

	public void setTHAlarm(String THAlarm) {
		this.THAlarm = THAlarm;
	}

	public String getTHhAlarm() {
		return this.THhAlarm;
	}

	public void setTHhAlarm(String THhAlarm) {
		this.THhAlarm = THhAlarm;
	}

	public String getLevel() {
		return this.level;
	}

	public void setLevel(String level) {
		this.level = level;
	}

	public String getTemperature1() {
		return this.temperature1;
	}

	public void setTemperature1(String temperature1) {
		this.temperature1 = temperature1;
	}

	public String getTemperature2() {
		return this.temperature2;
	}

	public void setTemperature2(String temperature2) {
		this.temperature2 = temperature2;
	}

	public String getTemperature3() {
		return this.temperature3;
	}

	public void setTemperature3(String temperature3) {
		this.temperature3 = temperature3;
	}

	public String getTemperature4() {
		return this.temperature4;
	}

	public void setTemperature4(String temperature4) {
		this.temperature4 = temperature4;
	}

	public String getVesselTemp() {
		return this.vesselTemp;
	}

	public void setVesselTemp(String vesselTemp) {
		this.vesselTemp = vesselTemp;
	}

	public String getSampleTemp() {
		return this.sampleTemp;
	}

	public void setSampleTemp(String sampleTemp) {
		this.sampleTemp = sampleTemp;
	}

	public String getSerial() {
		return this.serial;
	}

	public void setSerial(String serial) {
		this.serial = serial;
	}

	public Integer getClock() {
		return this.clock;
	}

	public void setClock(Integer clock) {
		this.clock = clock;
	}

}