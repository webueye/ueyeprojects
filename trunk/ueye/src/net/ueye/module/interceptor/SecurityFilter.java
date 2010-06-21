package net.ueye.module.interceptor;

import java.io.IOException;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import net.ueye.module.common.SessionCons;
import net.ueye.module.entity.Account;

/**
 * @author rubys@vip.qq.com
 *	Oct 7, 2009
 */
public class SecurityFilter implements Filter {


	public void init(FilterConfig filterConfig) throws ServletException {

	}

	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = ((HttpServletRequest)req);		
		Account account = (Account) request.getSession().getAttribute(SessionCons.CURRENT_ACCOUNT);		
		String url = request.getRequestURI();
		String[] eq = {"/ueye","/ueye/login","/ueye/images/LoginBackgroundTile.gif","/ueye/images/body_bj.jpg"};
		String[] contain = {"/ueye/images/","/ueye/css/","/ueye/js/","index.jsp"};
		if(equalsOrContains(eq, contain, url, request.getMethod())){
			chain.doFilter(req, resp);
		}
		else if((account != null) && (account.getUsername() != null) && (!"".equals(account.getUsername()))){
			chain.doFilter(req, resp);
		}
		else{
			((HttpServletResponse)resp).sendRedirect("/ueye/login");
		}	
	}
	
	public boolean equalsOrContains(String[] eq, String[] contains, String url, String method){		
		for(String str: eq){
			if(str.equals(url)){
				return true;
			}
		}			
		for(String str:contains){
			if(url.contains(str) && !str.equals(url)){
				return true;
			}
		}		
		return false;
	}
	
	public void p(Object value){
		System.out.println("     security      "+value);
	}
	
	public void destroy() {

	}

}
