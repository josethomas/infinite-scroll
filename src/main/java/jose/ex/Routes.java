package jose.ex;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.SparkBase.port;
import static spark.SparkBase.staticFileLocation;

import java.util.List;
import java.util.Optional;
import java.util.Properties;

import com.google.inject.Inject;

/**
 * This class serves the purpose of defining the RESTful interface on top of the business logic.
 * It set up url handlers and http status code mappings.
 * @author jose thomas
 *
 */
public class Routes {

    @Inject
    public Routes(ItemManager itemManager, ConvertUtils jsonUtils, Properties prop) {
        port(jsonUtils.toPositiveInteger(prop.get("port").toString()).get());
        staticFileLocation("/public");
        exception(NumberFormatException.class, (e, req, response) ->{
            response.status(400);
            response.body("Failed to convert to number. " + e.getMessage());
        });
        exception(IllegalArgumentException.class, (e, req, response) ->{
            response.status(400);
            response.body(e.getMessage());
        });
        get("/item", "application/json",
                (request, response) -> {
                    Optional<Integer> offset = jsonUtils.toPositiveInteger(request.queryParams("offset"));
                    Optional<Integer> limit = jsonUtils.toPositiveInteger(request.queryParams("limit"));
                    List<Item> items = itemManager.fetchItems(offset, limit, request.url());
                    response.status(200);
                    response.type("application/json");
                    return items;
                },
                (o) -> jsonUtils.marshall(o));
        
        get("/item/:id", "application/json",
                (request, response) -> {return itemManager.getItem(request.params(":id"));},
                (o) -> jsonUtils.marshall(o));
    }
}
