package com.example.sleuthpedia.dscs;

public class SupportSkill implements Filterable {

    private int id;
    private String name;
    private String description;

    public SupportSkill(int id, String name, String description) {
        this.id = id;
        this.name = name;
        this.description = description;
    }

    @Override
    public String getName() {
        return this.name;
    }

    public int getId() {
        return id;
    }

    public String getDescription() {
        return description;
    }
}
