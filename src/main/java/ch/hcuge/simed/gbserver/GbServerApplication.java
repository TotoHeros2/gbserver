package ch.hcuge.simed.gbserver;

import java.util.Properties;

import org.apache.cayenne.configuration.server.ServerRuntime;
import org.hug.simed.omop.store.IStoreConfiguration;
import org.hug.simed.omop.store.IStoreProvider;
import org.hug.simed.omop.store.Store;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class GbServerApplication {
    private static Logger logger = LoggerFactory.getLogger(GbServerApplication.class);

	
//	public static ServerRuntime cayenneRuntime;
//	public static IStoreProvider store;

	static {
//	    cayenneRuntime = ServerRuntime.builder().addConfig("cayenne-OMOP.xml").build();
//	    cayenneRuntime = ServerRuntime.builder().addConfig("cayenne-SNOMED.xml").build();

	}

	public static void main(String[] args) {
		SpringApplication.run(GbServerApplication.class, args);
	}

}
