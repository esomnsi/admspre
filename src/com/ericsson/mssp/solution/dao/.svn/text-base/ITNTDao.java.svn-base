package com.ericsson.mssp.solution.dao;

import java.util.List;

import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
import com.ericsson.mssp.common.entity.TNTDetail;
import com.ericsson.mssp.common.entity.TNTPartitionDate;
import com.ericsson.mssp.common.exception.MSSPException;

public interface ITNTDao 
{
	/**
	 * This method will save the TNT values for number of partitions, start date and end date on the database.
	 * Method Name	: saveTntPartitionDateDetails
	 * Input& Output:
	 * 	@param partitionDate The object containing the partition date data.
	 * 	@throws MSSPException In case of any error this exception is thrown.
	 */
	public void saveTntPartitionDateDetails(TNTPartitionDateDTO partitionDate) throws MSSPException;
	/**
	 * This method gets the partition date data for a particular solutionId as a TNTPartitionDate object.
	 * Method Name	: getPartitionDate
	 * Input& Output:
	 * 	@param solutionId The solution-ID for which the TNT data is required
	 * 	@return The TNTPartitionDate object containing the number of partitions, start date and end date for a particular solution.
	 */
	public TNTPartitionDateDTO getPartitionDate(Long solutionId, Long oppId);
	/**
	 * This method gets the TNTDetail data as a in a list after querying the database. 
	 * Method Name	: getTnTDetail
	 * Input& Output:
	 * 	@param solutionId The solution-ID for which the TNT data is required.
	 * 	@return The TNTDetail data in a list.
	 */
	public List<TNTDetail> getTnTDetail(Long solutionId);
	/**
	 * This method saves the TNTDetail data on the database 
	 * Method Name	: saveTntDetailDto
	 * Input& Output:
	 * 	@param tntDetail The list containing the TNTDetail object(s). 
	 * 	@throws MSSPException In case of any error this exception is thrown.
	 */
	public void saveTntDetail(List<TNTDetail> tntDetail) throws MSSPException;
}
