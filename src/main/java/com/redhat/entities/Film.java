package com.redhat.entities;

import java.time.LocalDate;

public class Film {
    /**
     * For readability, we use classes with public fields, but classes with private fields
     * with public getters and setters will also work.
     */
    public String title;
    public Integer episodeID;
    public String director;
    public LocalDate releaseDate;
}
