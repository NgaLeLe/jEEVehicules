package fr.filtre;

import java.io.IOException;
import java.io.PrintWriter;

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

/**
 * Servlet Filter implementation class FiltreAccessible
 */
@WebFilter("/FiltreAccessible")
public class FiltreAccessible implements Filter {

	/**
	 * Default constructor.
	 */
	public FiltreAccessible() {
	}

	/**
	 * @see Filter#destroy()
	 */
	public void destroy() {
		// TODO Auto-generated method stub
	}

	/**
	 * @see Filter#doFilter(ServletRequest, ServletResponse, FilterChain)
	 */
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest req = (HttpServletRequest) request;
		HttpServletResponse res = (HttpServletResponse) response;
		HttpSession session = req.getSession(); // récupère session

		String fUser = req.getParameter("username");
		String fPass = req.getParameter("password");
		System.out.println(fUser + "___" + fPass);
		if ((fUser != null && fPass != null) || (!req.getParameter("error").contains("1"))
				|| (!req.getParameter("disconnect").contains("1"))) {
			PrintWriter out = response.getWriter();
			out.print("You have permission");
			chain.doFilter(req, res);
		} else {
			res.sendRedirect(req.getContextPath() + "/login");
		}
	}

	/**
	 * @see Filter#init(FilterConfig)
	 */
	public void init(FilterConfig fConfig) throws ServletException {
		// TODO Auto-generated method stub
	}

}
