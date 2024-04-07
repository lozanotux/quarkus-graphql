package com.redhat.entities;

import java.util.ArrayList;
import java.util.List;

public class Hero {
    /**
     * For readability, we use classes with public fields, but classes with private fields
     * with public getters and setters will also work.
     */
    public String name;
    public String surname;
    public Double height;
    public Integer mass;
    public Boolean darkSide;
    public LightSaber lightSaber;
    public List<Integer> episodeIds = new ArrayList<>();
}
