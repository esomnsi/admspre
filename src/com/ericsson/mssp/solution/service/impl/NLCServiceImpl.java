package com.ericsson.mssp.solution.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ericsson.mssp.common.exception.MSSPException;
import com.ericsson.mssp.solution.dao.INLCDao;
import com.ericsson.mssp.solution.forms.NLCForm;
import com.ericsson.mssp.solution.service.INLCService;

@Service
public class NLCServiceImpl implements INLCService {
	
	@Autowired
    private INLCDao nlcDAO;
	
	public void saveNLC(NLCForm nlcForm) throws MSSPException{
		nlcDAO.saveNLC(nlcForm);
	}
	
	public NLCForm retrieveNLC(int solutionId,NLCForm nlcForm) throws MSSPException{
		return nlcDAO.retrieveNLC(solutionId, nlcForm);
	}
}
