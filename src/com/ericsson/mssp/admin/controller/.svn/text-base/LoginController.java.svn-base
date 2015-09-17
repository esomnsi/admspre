package com.ericsson.mssp.admin.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.GrantedAuthorityImpl;
import org.springframework.security.core.context.SecurityContext;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.context.SecurityContextImpl;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.ericsson.mssp.common.constant.MSSPConstants;
import com.ericsson.mssp.common.dto.RoleDTO;
import com.ericsson.mssp.common.dto.UserDTO;
import com.ericsson.mssp.common.entity.ApplicationRole;
import com.ericsson.mssp.common.util.ApplicationPropertiesUtil;
import com.ericsson.mssp.login.service.LoginService;
import com.ericsson.mssp.solution.controller.BaseController;

@Controller
public class LoginController extends BaseController implements MSSPConstants {

	@Autowired
	LoginService loginService;
	private String  authMessage = "";
	
	AuthenticationManager authenticationManager = new AuthenticationManager() {

		@Override
		public Authentication authenticate(Authentication arg0)
				throws AuthenticationException {
			return null;
		}
	};

	public static Logger logger = Logger.getLogger(LoginController.class);

	
	@ModelAttribute("rList")
	public List<RoleDTO> listRoles(Map<String, Object> map)
	{
		List<RoleDTO> roleList = new ArrayList<RoleDTO>();
		roleList = loginService.getRoles();
		return roleList;
	}
	
	@RequestMapping(value = "/login/homePage")
	public String homePage(ModelMap model, @RequestParam(value = "flag", required = false, defaultValue = "true") String flag,HttpSession session,
			@RequestParam(value="expire",required = false, defaultValue = "false")String expire) {
		
		logger.info("displaying home page with expire flag :["+expire+"] and authentication as:["+flag+"]");
		String sessionExpired ="";
		String message = "";
		if("true".equals(expire))
	    {
	    	sessionExpired = ApplicationPropertiesUtil.getProperty("msg.common.login.session.expired");
	    }
	  
	    if(flag.equals("false"))
	    {
	    	message = ApplicationPropertiesUtil.getProperty("msg.login.wrongCredentials");
	    }

	    model.addAttribute("userDTO", new UserDTO());
	    model.put("message",message);
		model.put("authMessage", authMessage);
	    model.addAttribute("sessionMsg", sessionExpired);
	    authMessage = null;
	    return "homePage";
	}

	@RequestMapping(value="/login/setRole")
	public @ResponseBody String setUserRole(@RequestParam("role")String role,HttpSession session)
	{
		logger.info("user logged in with role :["+role+"]");
		session.setAttribute("ROLE", role);
		return "success";
	}
	
	@RequestMapping(value="/login/accessDenied")
	public String HPage(ModelMap model) {
		logger.info("displaying access denied page");
		String aMessage = "";
		aMessage = "<ul><li>"+ApplicationPropertiesUtil.getProperty("msg.login.authMessage")+"</li></ul>";
		model.put("aMessage", aMessage);
		return "accessDenied";
	}
	
	@RequestMapping(value = "/login/verifyUser")
	public String verifyUser(ModelMap model, HttpSession session) {
		String returnString = null;
		String selectedRole = null;
		String role_guest = "false";
		List<UserDTO> userDTOList = null;
		Authentication auth = null;
		authMessage = null;
		
		try{
		 
			auth = SecurityContextHolder.getContext()
				.getAuthentication();

		selectedRole = (String)session.getAttribute("ROLE");
		logger.info("verifying user with name as :["+auth.getName()+"] and role as :["+selectedRole+"]");
		
		userDTOList = loginService.getUser(auth.getName());
		System.out.println("user dto try block");
		
		if (userDTOList == null)
		{
			authMessage = ApplicationPropertiesUtil.getProperty("msg.login.noUser");
			returnString = "redirect:./homePage";
		}
		else if (userDTOList.size() < 1)
		{
			authMessage = ApplicationPropertiesUtil.getProperty("msg.login.noUser");
			returnString = "redirect:./homePage";
		}
		else {
			Collection<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
			boolean foundRole = false;
			for (int i = 0; i < userDTOList.size(); i++) {
				List<ApplicationRole> roleList = userDTOList.get(i).getApplicationRoles();
				for(ApplicationRole r:roleList){
					if(selectedRole.equals(r.getRoleName()))
					{
						authorities.add(new GrantedAuthorityImpl(r.getRoleName()));
						foundRole = true;
					}
					else
						continue;
				}
			}
			if(!foundRole)
			{
				String []r = selectedRole.split("_");
				String message="";
				if(r.length >2 )
				{
					message = r[1]+" "+r[2];
				}
				else
				{
					message = r[1];
				}
				authMessage = ApplicationPropertiesUtil.getProperty("msg.login.noRole") +" [ "+message+"]";
				returnString = "redirect:./homePage";
				return returnString;
			}
			
			try {
				Authentication token = new UsernamePasswordAuthenticationToken(auth.getName(), null, authorities);
				SecurityContext securityContext = new SecurityContextImpl();
				SecurityContextHolder.setContext(securityContext);
				SecurityContextHolder.getContext().setAuthentication(token);
				setSessionValueByKey(session, USER_NAME, auth.getName());
				
				if(selectedRole.equals("ROLE_GUEST"))
				{
					role_guest = "true";
					session.setAttribute("role_guest", role_guest);
					returnString = "redirect:/search/openSolution";
				}
				else{
					returnString = "redirect:../action/initiateApproval";
				}
			} catch (Exception e) {
				logger.info("exception veriying user : " + e.getStackTrace());
				returnString = "redirect:./login/homePage";
			}
		}
		
		}catch(Exception e){
			authMessage = "Inter server error. Please retry again";
			returnString = "redirect:./homePage";
		}
		return returnString;
	}

	@RequestMapping(value = "/login/loginFailed")
	public String loginFailed(Model model) {
		logger.info("login failed");
		return null;
	}

	@RequestMapping(value = "/login/logout", method=RequestMethod.GET)
	public String logout(Model model, HttpServletRequest req){
		
		logger.info("session invalidated");
		logger.info("loggin out");
		req.getSession().invalidate();
		SecurityContextHolder.clearContext();		
		String msg =  ApplicationPropertiesUtil.getProperty("msg.common.login.session.logout");
		 model.addAttribute("sessionMsg", msg);
		return "homePage";
	}	
}