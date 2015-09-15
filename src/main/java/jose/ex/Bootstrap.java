package jose.ex;

import com.google.inject.Guice;

/**
 * Startup class for the application.  Basically creates a guice injector
 * which in turn initializes the various classes including those that
 * start Jetty and connect to mongoDB.
 * @author jose thomas
 *
 */
public class Bootstrap {
    public static void main(String[] args) {
        Guice.createInjector(
                new CoreModule());
    }
}
