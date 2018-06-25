import io.dropwizard.views.View;

public class ItemView extends View {

    private final Item item;

    public ItemView(Item item) {
        super("item.ftl");
        this.item = item;
    }

    public Item getItem() {

        return item;
    }
}
