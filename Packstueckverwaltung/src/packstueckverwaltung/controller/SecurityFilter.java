package packstueckverwaltung.controller;

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

@WebFilter("*.html")
public class SecurityFilter implements Filter
{
	@Override
	public void destroy()
	{
		//blablabal
		// TODO Auto-generated method stub		
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException
	{
		// TODO Auto-generated method stub		
	}

	public void doFilter(ServletRequest req, ServletResponse res, FilterChain chain) throws IOException,
			ServletException
	{
		HttpServletRequest request = (HttpServletRequest) req;
		HttpServletResponse response = (HttpServletResponse) res;
		
		if (request.getSession().getAttribute("session_person") == null)
		{
			request.setAttribute("contentpage", "login");
			request.getRequestDispatcher("/WEB-INF/views/base.jsp").forward(request, response);
			
			return;
		}
		chain.doFilter(request, response);
	}
}
