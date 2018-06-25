package lego.todo;

import io.dropwizard.views.View;

/**
 * Created by kraghunathan on 7/19/16.
 */
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