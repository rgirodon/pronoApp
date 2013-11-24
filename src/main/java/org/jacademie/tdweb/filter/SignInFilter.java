package org.jacademie.tdweb.filter;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.jacademie.tdweb.domain.User;

@WebFilter(urlPatterns={"*.do", "*.jsp"})
public class SignInFilter implements Filter {

	private static Logger logger = Logger.getLogger(SignInFilter.class);
	
	@Override
	public void destroy() {
		
		logger.debug("In destroy");
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		HttpServletRequest httpServletRequest = (HttpServletRequest)request;
		HttpServletResponse httpServletResponse = (HttpServletResponse)response;
		
		logger.debug("In doFilter");
		
		String path = httpServletRequest.getServletPath();

		logger.debug("Path : " + path);
		
		if (this.excludeFromFilter(path)) {
			
			logger.debug("No authentification needed");
			
			chain.doFilter(request, response);
	    }
	    else {
	    	logger.debug("Authentification needed");
	    	
	    	HttpSession httpSession = httpServletRequest.getSession();
	    	
	    	User user = (User)httpSession.getAttribute("user");
	    	
	    	if (user == null) {
	    		
	    		logger.debug("User not logged");
	    		
	    		httpServletResponse.sendRedirect(httpServletRequest.getContextPath() + "/SignIn.do");
	    	}
	    	else {
	    		logger.debug("User logged with login : " + user.getLogin());
	    		
	    		chain.doFilter(request, response);
	    	}
	    }
	}

	private boolean excludeFromFilter(String path) {
		
		 if (path.startsWith("/SignIn")) {
			 
			 return true;
		 }
	     else {
	    	 return false;
	     }
	}

	@Override
	public void init(FilterConfig config) throws ServletException {
		
		logger.debug("In init");
	}

	
}
