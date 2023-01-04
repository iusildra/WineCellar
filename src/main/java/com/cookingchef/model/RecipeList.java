package com.cookingchef.model;

import java.util.Optional;

public class RecipeList {
    private Optional<Integer> id = Optional.empty();
    private String name;

    /**
     * Constructor for a new RecipeList.
     * @param name
     */
    public RecipeList(String name) {
        this.name = name;
    }

    /**
     * @return the id
     */
    public Optional<Integer> getId() {
        return id;
    }

    /**
     * Set the ID only once, if it is not already set.
     *
     * @param id The id of the object.
     */
    public void setId(int id) {
        if (this.id.isEmpty())
            this.id = Optional.of(id);
    }

    /**
     * @return the name
     */
    public String getName() {
        return name;
    }

    /**
     * @param name the name to set
     */
    public void setName(String name) {
        this.name = name;
    }
}
