package lego.todo;

import io.dropwizard.views.View;

import java.util.List;

/**
 * Created by kraghunathan on 7/19/16.
 */
public class ItemsView extends View {

    private final List<Item> items;

    public ItemsView(List<Item> items) {
        super("items.ftl");
        this.items = items;
    }

    public List<Item> getItems() {
        return items;
    }
}