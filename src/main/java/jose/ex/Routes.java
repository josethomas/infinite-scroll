package jose.ex;

import static spark.Spark.exception;
import static spark.Spark.get;
import static spark.SparkBase.staticFileLocation;

import java.util.List;
import java.util.Optional;

import com.google.inject.Inject;

public class Routes {

    @Inject
    public Routes(ItemManager itemManager, JsonUtils jsonUtils) {
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
                (request, response) -> {return itemManager.getItem(request.params(":jobId"));},
                (o) -> jsonUtils.marshall(o));
    }
}
