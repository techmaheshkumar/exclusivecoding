package com.poc.jersey.auth;

import java.io.IOException;
import java.util.Base64;
import java.util.StringTokenizer;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class RestAuthFilter implements Filter {

	@Override
	public void doFilter(ServletRequest servletRequest, ServletResponse servletResponse, FilterChain filter)
			throws IOException, ServletException {
		if (servletRequest instanceof HttpServletRequest) {
			HttpServletRequest httpRequest = (HttpServletRequest) servletRequest;
			String authCredentials = httpRequest.getHeader("Authorization");
			if (toAuthenticate(httpRequest, authCredentials)) {
				filter.doFilter(servletRequest, servletResponse);
			} else {
				if (servletResponse instanceof HttpServletResponse) {
					HttpServletResponse httpResponse = (HttpServletResponse) servletResponse;
					httpResponse.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
				}
			}
		}
	}

	/**
	 * To Authenticate User.
	 * 
	 * @param authdetail
	 * @return boolean
	 */
	public boolean toAuthenticate(HttpServletRequest httpRequest, String authdetail) {
		if (null == authdetail) {
			return false;
		}
		final String encodedUsrPwd = authdetail.replaceFirst("Basic" + " ", "");
		String usrnameAndPwd = null;
		try {
			byte[] decodedBytes = Base64.getDecoder().decode(encodedUsrPwd);
			usrnameAndPwd = new String(decodedBytes, "UTF-8");
		} catch (IOException e) {
			e.printStackTrace();
		}
		final StringTokenizer tokenizer = new StringTokenizer(usrnameAndPwd, ":");
		final String username = tokenizer.nextToken();
		final String password = tokenizer.nextToken();
		if (username.equals("admin") && password.equals("admin")) {
			return true;
		}
		return false;
	}

	@Override
	public void destroy() {
	}

	@Override
	public void init(FilterConfig arg0) throws ServletException {
	}
}
