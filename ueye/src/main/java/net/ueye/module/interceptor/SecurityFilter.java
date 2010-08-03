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
 * 2009-10-28
 */
public class SecurityFilter implements Filter {
	
	private String fileResource;

	public void init(FilterConfig filterConfig) throws ServletException {
		this.fileResource = filterConfig.getInitParameter("fileResource");
	}

	public void doFilter(ServletRequest req, ServletResponse resp,FilterChain chain) throws IOException, ServletException {
		HttpServletRequest request = ((HttpServletRequest)req);		
		Account account = (Account) request.getSession().getAttribute(SessionCons.CURRENT_ACCOUNT);		
		String url = request.getRequestURI();
		
		String context = request.getContextPath();
		
		String[] resources = null;
		
		if(fileResource != null){
			resources = fileResource.split(",");
		}
		
		if(handleResource(resources, context, url, request.getMethod())){
			chain.doFilter(req, resp);
		}
		else if((account != null) && (account.getUsername() != null) && (!"".equals(account.getUsername()))){
			chain.doFilter(req, resp);
		}
		else{
			((HttpServletResponse)resp).sendRedirect(context + "/login");
		}	
	}
	
	public boolean handleResource(String[] resources, String context, String url, String method){
		if(resources == null){
			return true;
		}
		for(String resource: resources){
			if(resource != null){
				resource = resource.trim();
			}
			if(url != null && url.contains(context+resource) && !"".equals(resource)){
				return true;
			}
		}
		return false;
	}
	
	public void destroy() {
		this.fileResource = null;
	}
	
}
