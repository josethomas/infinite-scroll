package jose.ex;

import com.google.inject.Guice;

public class Bootstrap {
    public static void main(String[] args) {
        Guice.createInjector(
                new MongoModule());
    }
}
