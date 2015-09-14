package jose.ex;

import org.mongodb.morphia.Datastore;
import org.mongodb.morphia.Morphia;

import com.google.inject.AbstractModule;
import com.google.inject.Provides;
import com.google.inject.Singleton;
import com.mongodb.MongoClient;

public class MongoModule extends AbstractModule {

    @Provides
    @Singleton
    public Datastore getMongoDatastore() {
        MongoClient mongoClient = new MongoClient("localhost", 27017);
        Morphia morphia = new Morphia();
        Datastore ds = morphia.createDatastore(mongoClient, "ex");
        ds.ensureIndexes();
        return ds;
    }

    @Override
    protected void configure() {
        bind(JsonUtils.class);
        bind(ItemManager.class);
        bind(Routes.class).asEagerSingleton();
    }
}
