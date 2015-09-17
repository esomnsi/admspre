package com.ericsson.mssp.common.dto;

public class VolumetricsDefinedByDTO {
	
	 private Integer inputVolumetricsDefinedById;
	    private String name;
	    private String description;
		public Integer getInputVolumetricsDefinedById() {
			return inputVolumetricsDefinedById;
		}
		public void setInputVolumetricsDefinedById(Integer inputVolumetricsDefinedById) {
			this.inputVolumetricsDefinedById = inputVolumetricsDefinedById;
		}
		public String getName() {
			return name;
		}
		public void setName(String name) {
			this.name = name;
		}
		public String getDescription() {
			return description;
		}
		public void setDescription(String description) {
			this.description = description;
		}
}
