package com.ericsson.mssp.common.entity;

//Generated May 07, 2014 11:53:09 AM by Hibernate Tools 3.4.0.CR1

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;

import static javax.persistence.GenerationType.IDENTITY;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
* @author eanujau
*/
@Entity
@Table(name = "ReleaseManagement")
@NamedQueries({
	@NamedQuery(
	name = "get.releaseManagement.bySolutionID",
	query = "from ReleaseManagement where SolutionID = ?"
	)
})
public class ReleaseManagement implements java.io.Serializable {

/**
* 
*/
private static final long serialVersionUID = -7676448561170657940L;
private Integer releaseManagementID;
private Solution solution;
private OpportunityScope opportunityScope;
private float instlnDistnOfSoftRel;
private float defineRolloutPlan;
private float ensureTraceability;
private float ensureInstallation;
private float totalReleaseManagementHours;


@Id
@GeneratedValue(strategy = IDENTITY)
@Column(name = "ReleaseManagementID", unique = true, nullable = false)
public Integer getReleaseManagementID() {
	return this.releaseManagementID;
}

public void setReleaseManagementID(Integer releaseManagementID) {
	this.releaseManagementID = releaseManagementID;
}

@ManyToOne(fetch = FetchType.LAZY)
@JoinColumn(name = "SolutionID", nullable = false)
public Solution getSolution() {
	return this.solution;
}

public void setSolution(Solution solution) {
	this.solution = solution;
}

	/**
	 * @return the opportunityScope
	 */
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name = "OpportunityScopeID")
	public OpportunityScope getOpportunityScope() {
		return opportunityScope;
	}

	/**
	 * @param opportunityScope the opportunityScope to set
	 */
	public void setOpportunityScope(OpportunityScope opportunityScope) {
		this.opportunityScope = opportunityScope;
	}

	@Column(name="InstlnDistnOfSoftRel")
	public float getInstlnDistnOfSoftRel() {
		return instlnDistnOfSoftRel;
	}

	public void setInstlnDistnOfSoftRel(float instlnDistnOfSoftRel) {
		this.instlnDistnOfSoftRel = instlnDistnOfSoftRel;
	}

	@Column(name="DefineRolloutPlan")
	public float getDefineRolloutPlan() {
		return defineRolloutPlan;
	}

	public void setDefineRolloutPlan(float defineRolloutPlan) {
		this.defineRolloutPlan = defineRolloutPlan;
	}

	@Column(name="EnsureTraceability")
	public float getEnsureTraceability() {
		return ensureTraceability;
	}

	public void setEnsureTraceability(float ensureTraceability) {
		this.ensureTraceability = ensureTraceability;
	}

	@Column(name="EnsureInstallation")
	public float getEnsureInstallation() {
		return ensureInstallation;
	}

	public void setEnsureInstallation(float ensureInstallation) {
		this.ensureInstallation = ensureInstallation;
	}

	@Column(name="TotalReleaseManagementHours")
	public float getTotalReleaseManagementHours() {
		return totalReleaseManagementHours;
	}

	public void setTotalReleaseManagementHours(float totalReleaseManagementHours) {
		this.totalReleaseManagementHours = totalReleaseManagementHours;
	}

}
