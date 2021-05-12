package com.sergouniotis.inventory.listener;

import javax.inject.Inject;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;

import org.flywaydb.core.Flyway;

@WebListener
public class InventoryListener implements ServletContextListener{

    @Inject
    Flyway flyway;

    public void contextInitialized(ServletContextEvent sce) {
		flyway.migrate();
	}

    public void contextDestroyed(ServletContextEvent sce) {}
    
}
