package org.jacademie.tdweb.servlet;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.log4j.Logger;

/**
 * Servlet implementation class HelloServlet
 */
@WebServlet("/Hello")
public class HelloServlet extends HttpServlet {

	private static final long serialVersionUID = -4182619520304776714L;

	private static Logger logger = Logger.getLogger(HelloServlet.class);
	
	/**
     * Default constructor. 
     */
    public HelloServlet() {
    	logger.debug("In constructor");
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		logger.debug("In doGet");
		
		request.setAttribute("message", "Hello World !");
		
		RequestDispatcher requestDispatcher = this.getServletContext().getRequestDispatcher("/Hello.jsp");
		
		requestDispatcher.forward(request, response);
	}

}
