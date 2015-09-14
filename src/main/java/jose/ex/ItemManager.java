package jose.ex;

import java.util.List;
import java.util.Optional;

import org.bson.types.ObjectId;
import org.mongodb.morphia.Datastore;

import com.google.inject.Inject;

public class ItemManager {

    private final Datastore datastore;

    @Inject
    public ItemManager(final Datastore ds) {
        this.datastore = ds;
    }

    public List<Item> fetchItems(final Optional<Integer> offset, final Optional<Integer> limit, final String baseUrl) {
        List<Item> items = this.datastore.createQuery(Item.class)
            .offset(offset.orElse(0))
            .limit(limit.orElse(20))
            .asList();
        items.forEach(item -> {
            StringBuilder sb = new StringBuilder(baseUrl).append('/').append(item.getId());
            item.setDetailsUrl(sb.toString());
        });
        return items;
    }

    public Item getItem(String id) {
        Item item = get(Item.class, id);
        if (null == item) {
            throw new NotFoundException();
        }
        return item;
    }

    private <T> T get(final Class<T> clazz, final String id) {
        if(!ObjectId.isValid(id)) {
            throw new NotFoundException("not a valid id " + id);
        } else {
            ObjectId oid = new ObjectId(id);
            return this.datastore.get(clazz, oid);
        }
    }}
