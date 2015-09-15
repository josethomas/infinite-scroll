package jose.ex;

import java.io.IOException;
import java.util.Properties;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;

/**
 * The only guice module used in this app.  Sets up all the bindings and providers
 * used in the app.
 * @author jose thomas
 *
 */
public class CoreModule extends AbstractModule {

    @Provides
    @Singleton
    public Datastore getMongoDatastore() throws IOException {
        Properties prop = new Properties();
        prop.load(CoreModule.class.getClassLoader().getResourceAsStream("app.properties"));
        String portStr = prop.getProperty("mongo.port", "27017").trim();
        int port = Integer.valueOf(portStr);
        String host = prop.getProperty("mongo.host", "localhost").trim();
        String db = prop.getProperty("mongo.db", "ex").trim();
        MongoClient mongoClient = new MongoClient(host, port);
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(mongoClient, db);
        ds.ensureIndexes();
        return ds;
    }
    
    @Provides
    @Singleton
    public Properties getProperties() throws IOException {
        Properties prop = new Properties();
        prop.load(CoreModule.class.getClassLoader().getResourceAsStream("app.properties"));
        return prop;
    }

    @Override
    protected void configure() {
        bind(ConvertUtils.class);
        bind(ItemManager.class);
        bind(Routes.class).asEagerSingleton();
    }
}
