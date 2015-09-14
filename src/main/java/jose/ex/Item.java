package jose.ex;

import org.bson.types.ObjectId;
import org.mongodb.morphia.annotations.Entity;
import org.mongodb.morphia.annotations.Id;

@Entity("item")
public class Item {

    @Id
    private ObjectId     id;
    private String title;
    private String blurb;
    private String author;
    private String thumbUrl;
    private String detailsUrl;
    
    public Item() {super();}

    public Item(ObjectId id, String title, String blurb, String author, String thumbUrl, String detailsUrl) {
        this();
        this.id = id;
        this.title = title;
        this.blurb = blurb;
        this.author = author;
        this.thumbUrl = thumbUrl;
        this.detailsUrl = detailsUrl;
    }
    public String getId() {
        return id.toHexString();
    }

    public String getTitle() {
        return title;
    }

    public String getBlurb() {
        return blurb;
    }

    public String getAuthor() {
        return author;
    }

    public String getThumbUrl() {
        return thumbUrl;
    }

    public String getDetailsUrl() {
        return detailsUrl;
    }

    public void setDetailsUrl(String detailsUrl) {
        this.detailsUrl = detailsUrl;
    }
}
