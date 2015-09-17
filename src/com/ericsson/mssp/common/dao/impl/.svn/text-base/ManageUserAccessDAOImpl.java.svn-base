/**
 * -------------------------------------------------------------------------------------------------------
 * Copyright (C) 2012 Ericsson India Global Pvt Limited
 * All Rights Reserved
 * Project         		    :  IT_MSSP
 * Module				    :  com.ericsson.mssp.login.service.impl
 * File name       		    :  ManageUserAccessImpl.java
 * Description				:	<To Do>
 * Author, Date & Release	:	Jan 15, 20132013
 * Modification history		:
 * Number	|Date   	   |Author        |Remark
 * -----------------------------------------------------------------------------------------------------
 * 1      	| Jan 15, 2013  	   |eshtgar   	  | Created.
 * -----------------------------------------------------------------------------------------------------
 **/

package com.ericsson.mssp.common.dao.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.Hashtable;
import java.util.List;

import javax.naming.Context;
import javax.naming.NamingEnumeration;
import javax.naming.directory.Attribute;
import javax.naming.directory.Attributes;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.naming.directory.SearchControls;
import javax.naming.directory.SearchResult;

import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Repository;


import com.ericsson.mssp.common.dao.IManageUserAccessDAO;
import com.ericsson.mssp.common.dto.UserAccessDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.entity.User;
import com.ericsson.mssp.common.exception.MSSPException;

/**
 * @author eshtgar
 * 
 */
@Repository
public class ManageUserAccessDAOImpl extends BaseDAOImpl implements IManageUserAccessDAO{
		

	public List<UserAccessDTO> searchLDAPUsers(UserAccessDTO userAccessDTO,String allUsers) {
		List<UserAccessDTO> searchResultList = new ArrayList<UserAccessDTO>();
		Hashtable<String, String> env = new Hashtable<String, String>();
		DirContext ctx = null;
		SearchResult sr = null;
		String search_base = "ou=users,ou=internal,o=ericsson";
		String[] my_attributes = { "mail", "uid", "cn", "telephonenumber",
				"sn", "employeeType", "manager", "givenName" };

		/* connect to the server */

		env.put(Context.INITIAL_CONTEXT_FACTORY,"com.sun.jndi.ldap.LdapCtxFactory");
		env.put(Context.PROVIDER_URL, "ldap://ecd.ericsson.se:389/");
		env.put(Context.SECURITY_AUTHENTICATION, "simple");
		env.put(Context.SECURITY_PRINCIPAL,	"uid=egiotrs,ou=users,ou=internal,o=ericsson");
		env.put(Context.SECURITY_CREDENTIALS, "@WSX3edc4RFV%tgb");

		Enumeration vals = null;
		String[] temp = new String[my_attributes.length];

		try {
			ctx = new InitialDirContext(env);
			System.out.println("ctx : " + ctx);
			SearchControls constraints = new SearchControls();
			constraints.setReturningAttributes(my_attributes);
			constraints.setSearchScope(SearchControls.SUBTREE_SCOPE);
			NamingEnumeration results = null;
			
			String firstName = userAccessDTO.getFirstName();
			String lastName = userAccessDTO.getLastName();
			String signumId = userAccessDTO.getSignumId();
			String emailId = userAccessDTO.getEmailId();
					
			firstName=firstName==null?"":firstName;
			lastName=lastName==null?"":lastName;
			signumId=signumId==null?"":signumId;
			emailId=emailId==null?"":emailId;
			
			//Getting first 7 digits for signum id 
			if((null != signumId) && ("" != signumId))
				signumId= signumId.substring(0,7);
			
			
			if(null != allUsers){
				results = ctx.search(search_base,"(uid=*))",											  
						  	 constraints);
			}
			else{
				results = ctx.search(search_base,"(&(givenName=" + firstName + "*" + ")" 
						  + "&(sn=" + lastName + "*" + ")" +
						  	"(uid="+ signumId +  "*" + ")" +
						  	"(mail=" + emailId +  "*" + "))",	
						   	 constraints);
			}
			int user = 0;
			int index = 0;
			while (results != null && results.hasMore()) {
				UserAccessDTO uad = new UserAccessDTO();
				user++;
				index++;
				sr = (SearchResult) results.next();
				String dn = sr.getName() + "," + search_base;
				Attributes attr = ctx.getAttributes(dn, my_attributes);

				if (attr == null) {
					/*System.out.println("distinguished name : " + dn
							+ "has no specified attribute");*/
					return searchResultList;
				}
				int i = 0;				
				for (; i < my_attributes.length; i++) {
					Attribute arr = attr.get(my_attributes[i]);
					if (arr == null) {
						temp[i] = "-";
						continue;
					}
					//System.out.println(my_attributes[i] + ":");
					for (vals = arr.getAll(); vals.hasMoreElements();) {
						temp[i] = (vals.nextElement()).toString();						
					}					
				}
				i=0;
				uad.setId(index);
				uad.setEmailId(temp[0]);
				uad.setSignumId(temp[1]);
				uad.setFirstName(temp[7]);
				uad.setContactNumber(temp[3]);
				uad.setLastName(temp[4]);
								
				searchResultList.add(uad);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}

		finally {
			try {
				ctx.close();
			} catch (Exception ee) {
				ee.printStackTrace();
			}
		}
		return searchResultList;
	}

	@Override
	public List<String> getRoles() {
		List<String> roleList = new ArrayList<String>();
		String query = "from ApplicationRole";
		roleList = getHibernateTemplate().find(query);
		return roleList;
	}

	@Override
	public boolean saveAssignedRole(UserAccessDTO userAccessDTO) throws MSSPException{
		boolean roleSaved = false;
		String hql = "select u from User u where u.signumId= '"+userAccessDTO.getSignumId()+"'";		
		List<User> u = getHibernateTemplate().find(hql);
		
		if(u!=null && u.size()>0){
			User user = u.get(0);
			user.setFirstName(userAccessDTO.getFirstName());
			user.setLastName(userAccessDTO.getLastName());
			user.setEmailId(userAccessDTO.getEmailId());
			user.setContactNumber(userAccessDTO.getContactNumber());
			user.setSignumId(userAccessDTO.getSignumId());
			user.setApplicationRoles(userAccessDTO.getRoleList());
			updateObject(user);
			roleSaved = true;
		}else{
			// new user entry
			User user = new User();
			user.setFirstName(userAccessDTO.getFirstName());
			user.setLastName(userAccessDTO.getLastName());
			user.setEmailId(userAccessDTO.getEmailId());
			user.setSignumId(userAccessDTO.getSignumId());
			user.setContactNumber(userAccessDTO.getContactNumber());
			user.setApplicationRoles(userAccessDTO.getRoleList());
			saveObject(user);
			roleSaved = true;
		}
		
		/*User user = new User();
		
		try {			
			user.setFirstName(userAccessDTO.getFirstName());
			user.setLastName(userAccessDTO.getLastName());
			user.setEmailId(userAccessDTO.getEmailId());
			user.setSignumId(userAccessDTO.getSignumId());
			user.setContactNumber(userAccessDTO.getContactNumber());
			user.setApplicationRoles(userAccessDTO.getRoleList());
			saveObject(user);
			roleSaved = true;*/
		/*} catch (Exception e) {
			logger.error(" Exception while assgning roles " + e.getMessage());
			e.printStackTrace();
		}*/
		
		return roleSaved;
	}
	
	
	public String getRolesByUserId(Integer userId){
		logger.info("Getting all roles for user::: " +userId);		
		List<String> roleList = new ArrayList<String>();	
		String roleName = "";
		User obj = (User)getObject(User.class, userId);		
		System.out.println(	obj.getApplicationRoles());
		List<ApplicationRole> rolesList = obj.getApplicationRoles();
		if(rolesList!=null){
			for(int i=0;i<rolesList.size();i++){
				roleName += rolesList.get(i).getDisplayName() + ",";			
			}	
			roleName = roleName!=""?roleName.substring(0, (roleName.length()-1)):"";
		}
		return roleName;		
	}
	
	
	public List<User> getRolesByUserSignum(String signum){
		logger.info("Getting all roles for user::: " +signum);		
		List<String> roleList = new ArrayList<String>();	
		String roleName = "";
		String hql 	= "select u from User u where u.signumId='"+signum+"'";		
		List<User>user = getHibernateTemplate().find(hql);
		if(user!=null && user.size()>1){
			logger.error("Error in finding user, more than one instance found in database"); 
			return null;
		}
		logger.debug("Query returned :" + user.size());		
		return user;
		
	}

	@Override
	public List<UserAccessDTO> getUsersList() throws MSSPException {
		List<Object> usersList = new ArrayList<Object>();
		List<UserAccessDTO> result = new ArrayList<UserAccessDTO>();
		UserAccessDTO uad = null;
		
		String query = "select u.firstName, u.lastName, u.signumId, u.emailId, u.startDate, u.endDate, u.contactNumber,u.userId" +
						" from User u";		
		usersList = getHibernateTemplate().find(query);	
		if(usersList!=null){
		for(int i=0;i<usersList.size();i++){
			uad = new UserAccessDTO();
			System.out.println(i);
			Object []o = (Object[])usersList.get(i);
			String firstName = (String) (o[0]!=null?o[0]:"-");
			String lastName = (String) (o[1]!=null?o[1]:"-");
			String signumId = (String) (o[2]!=null?o[2]:"-");
			String emailId = (String) (o[3]!=null?o[3]:"-");
			Date startDate = (Date) (o[4]!=null?o[4]:null);
			Date endDate =  (Date) (o[5]!=null?o[5]:null);
			String contactNo =  (String) (o[6]!=null?o[6]:"-");
			uad.setFirstName(firstName);
			uad.setLastName(lastName);
			uad.setSignumId(signumId);
			uad.setEmailId(emailId);
			uad.setContactNumber(contactNo);
			uad.setStartDate(startDate);
			uad.setEndDate(endDate);
			uad.setRoleName(getRolesByUserId((Integer) o[7]));
			result.add(uad);
		}
		}else{
			result = null;
		}
		
		logger.debug(result);
		
		return result;
	}

}