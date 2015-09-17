package com.ericsson.mssp.solution.service;

import java.util.List;

import com.ericsson.mssp.common.dto.TNTDetailDTO;
import com.ericsson.mssp.common.dto.TNTPartitionDateDTO;
import com.ericsson.mssp.common.exception.MSSPException;

public interface ITNTControllerService 
{
	/**
	 * This method saves all the tnt data on to the database
	 * Method Name	: saveTntPartitionDateDetails
	 * 	@param partitionDate The TNTPartitionDate containing all the data that needs to be stored on the database.
	 * 	@throws MSSPException if any error occurs during the operation.
	 */
	public void saveTntPartitionDateDetails(TNTPartitionDateDTO partitionDate) throws MSSPException;
	/**
	 * This method retrieves all the tnt data from the database for a particular solution 
	 * Method Name	: getPartitionDate
	 * 	@param solutionId The solution id for which the T&T data will be retrieved.
	 *  @param oppId The opportunity id for which the T&T dates will be retrieved.
	 * 	@return The data as a TNTPartitionDate object
	 */
	public TNTPartitionDateDTO getPartitionDate(Long solutionId, Long oppId);
	
	/**
	 * This method gets the details of the tnt for a particular solution 
	 * Method Name	: getTntDetails
	 * 	@param solutionId The solution id for which the tnt details will be retrieved.
	 * 	@return The TNTDetail object containing the data.
	 */
	public List<TNTDetailDTO> getTntDetails(Long solutionId);
	/**
	 * This method saves all the tnt details on the database 
	 * Method Name	: saveTntDetails
	 * 	@param tntDetail The TNTDetail object containing all the data.
	 * 	@throws MSSPException If any error occurs.
	 */
	public void saveTntDetailDto(List<TNTDetailDTO> tntDetail,Long solutionId) throws MSSPException;
}
