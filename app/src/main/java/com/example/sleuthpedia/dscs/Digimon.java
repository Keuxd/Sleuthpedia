package com.example.sleuthpedia.dscs;

import com.example.sleuthpedia.enums.Attribute;
import com.example.sleuthpedia.enums.DigimonFamily;
import com.example.sleuthpedia.enums.DigimonStage;
import com.example.sleuthpedia.enums.DigimonType;

public class Digimon implements Filterable {

    private int level = 1;

    private int id;
    private String name;
    private DigimonStage stage;
    private DigimonType type;
    private Attribute attribute;
    private int equipSlot;
    private int memoryUse;

    private int supportSkill;
    private DigimonFamily family;

    private int HP;
    private int SP;
    private int ATK;
    private int INT;
    private int DEF;
    private int SPD;

    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    public DigimonType getType() {
        return type;
    }

    public Attribute getAttribute() {
        return attribute;
    }

    public Digimon(int id, String name, DigimonType type, Attribute attribute) {
        this.id = id;
        this.name = name;
        this.type = type;
        this.attribute = attribute;
    }

    public Digimon(int id, String name, DigimonStage stage, DigimonType type, Attribute attribute, int equipSlot, int memoryUse, int supportSkill, int level, DigimonFamily family, int HP, int SP, int ATK, int INT, int DEF, int SPD) {
        this.id = id;
        this.name = name;
        this.stage = stage;
        this.type = type;
        this.attribute = attribute;
        this.equipSlot = equipSlot;
        this.memoryUse = memoryUse;
        this.supportSkill = supportSkill;
        this.level = level;
        this.family = family;
        this.HP = HP;
        this.SP = SP;
        this.ATK = ATK;
        this.INT = INT;
        this.DEF = DEF;
        this.SPD = SPD;
    }

    public void setLevel(int level) {
        if(level < 0 || level > 99) return;


        scaleStatus();
    }

    private void scaleStatus() {

    }

}
