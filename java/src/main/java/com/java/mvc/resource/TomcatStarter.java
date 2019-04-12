/*
 * Copyright 2012-2017 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.java.mvc.resource;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.springframework.boot.web.servlet.ServletContextInitializer;

import javax.servlet.ServletContainerInitializer;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import java.util.Set;

/**
 * {@link ServletContainerInitializer} used to trigger {@link ServletContextInitializer
 * ServletContextInitializers} and track startup errors.
 *
 * TomcatStarter实现了Servlet 3.0提供的容器接口，但是并没有使用其子类提供的onStartup方法，用的是
 * Spring提供的SpringContextInitializer子类提供的onStartup方法，其子类实现分别对应的servlet、filter
 * 、listener对应Servlet 3.0提供的方法。
 *
 * @author Phillip Webb
 * @author Andy Wilkinson
 */
public class TomcatStarter implements ServletContainerInitializer {

	private static final Log logger = LogFactory.getLog(TomcatStarter.class);

	private final ServletContextInitializer[] initializers;

	private volatile Exception startUpException;

	TomcatStarter(ServletContextInitializer[] initializers) {
		this.initializers = initializers;
	}

	@Override
	public void onStartup(Set<Class<?>> classes, ServletContext servletContext)
			throws ServletException {
		try {
			for (ServletContextInitializer initializer : this.initializers) {
				initializer.onStartup(servletContext);
			}
		}
		catch (Exception ex) {
			this.startUpException = ex;
			// Prevent Tomcat from logging and re-throwing when we know we can
			// deal with it in the main thread, but log for information here.
			if (logger.isErrorEnabled()) {
				logger.error("Error starting Tomcat context. Exception: "
						+ ex.getClass().getName() + ". Message: " + ex.getMessage());
			}
		}
	}

	public Exception getStartUpException() {
		return this.startUpException;
	}

}
