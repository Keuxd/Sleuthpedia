package com.example.sleuthpedia.dscs;

import com.example.sleuthpedia.enums.Attribute;
import com.example.sleuthpedia.enums.MoveType;

public class Move implements Filterable {

    private int id;
    private String name;
    private String description;
    private Attribute attribute;
    private MoveType moveType;
    private int spCost;
    private int power;
    private boolean isInherited;

    public Move(int id, String name, String description, Attribute attribute, MoveType moveType, int spCost, int power, boolean isInherited) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.attribute = attribute;
        this.moveType = moveType;
        this.spCost = spCost;
        this.power = power;
        this.isInherited = isInherited;
    }

    @Override
    public String getName() {
        return name;
    }

    public String getDescription() {
        return description;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public int getPower() {
        return power;
    }

    public int getSpCost() {
        return spCost;
    }
}
