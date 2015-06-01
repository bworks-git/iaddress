package com.iaddress.api.support.swagger;

import java.io.IOException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.wordnik.swagger.config.ConfigFactory;
import com.wordnik.swagger.config.SwaggerConfig;

public class Bootstrap extends HttpServlet {

	private static final long serialVersionUID = 1L;

	private volatile boolean initialized;

	@Override
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws IOException {
		if (!initialized) {
			response.getWriter().println("Initializing Swagger Bootstrap");
			synchronized (this) {
				if (!initialized) {
					SwaggerConfig config = ConfigFactory.config();
					config.setBasePath(
							request.getScheme() + "://"
									+ request.getServerName() + ":"
									+ request.getServerPort()
									+ request.getServletContext() + "/rest");
					ConfigFactory.setConfig(config);
					initialized = true;
				}
			}
		}
		response.getWriter().println("Swagger Bootstrap Completed");
	}
}
