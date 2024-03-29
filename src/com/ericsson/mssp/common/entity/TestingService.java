package com.ericsson.mssp.common.entity;

// Generated Mar 12, 2013 8:53:41 AM by Hibernate Tools 3.4.0.CR1

import java.util.Date;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

/**
 * TestingService generated by hbm2java
 */
@Entity
@Table(name = "TestingService")
public class TestingService implements java.io.Serializable {

    /**
     * 
     */
    private static final long serialVersionUID = 2399665645444147435L;
    private Integer testingServiceId;
    private Solution solution;
    private String releaseType;
    private Date releaseDate;
    private Float pcdistribution;

    public TestingService() {
    }

    public TestingService(Solution solution, String releaseType,
	    Date releaseDate, Float pcdistribution) {
	this.solution = solution;
	this.releaseType = releaseType;
	this.releaseDate = releaseDate;
	this.pcdistribution = pcdistribution;
    }

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "TestingServiceID", unique = true, nullable = false)
    public Integer getTestingServiceId() {
	return this.testingServiceId;
    }

    public void setTestingServiceId(Integer testingServiceId) {
	this.testingServiceId = testingServiceId;
    }

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "SolutionID")
    public Solution getSolution() {
	return this.solution;
    }

    public void setSolution(Solution solution) {
	this.solution = solution;
    }

    @Column(name = "ReleaseType", length = 10)
    public String getReleaseType() {
	return this.releaseType;
    }

    public void setReleaseType(String releaseType) {
	this.releaseType = releaseType;
    }

    @Temporal(TemporalType.DATE)
    @Column(name = "ReleaseDate", length = 19)
    public Date getReleaseDate() {
	return this.releaseDate;
    }

    public void setReleaseDate(Date releaseDate) {
	this.releaseDate = releaseDate;
    }

    @Column(name = "PCDistribution", precision = 10)
    public Float getPcdistribution() {
	return this.pcdistribution;
    }

    public void setPcdistribution(Float pcdistribution) {
	this.pcdistribution = pcdistribution;
    }

}
