package com.sergouniotis.inventory.listener;

import javax.annotation.Resource;
import javax.enterprise.inject.Produces;
import javax.sql.DataSource;

import org.flywaydb.core.Flyway;
import org.flywaydb.core.api.configuration.ClassicConfiguration;

public class FlywayProducer {


	@Resource(lookup = "java:jboss/datasources/BPRDatasource")
	private DataSource datasource;
    

    @Produces
    public Flyway create(){

        return new Flyway(getConfiguration());

    }


    private ClassicConfiguration getConfiguration(){

		ClassicConfiguration cfg = new ClassicConfiguration();

		cfg.setDataSource(this.datasource);

		cfg.setLocationsAsStrings("classpath:db/migration");

		cfg.setBaselineOnMigrate(true);
		cfg.setOutOfOrder(true);
		cfg.setMixed(true);

		return cfg;        

    }
    
}
