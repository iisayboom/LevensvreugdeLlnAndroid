package com.example.dreeki.projectleerlingenapp.Models;

import java.util.List;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;

/**
 * Created by dreeki on 26/10/17.
 */

public class Route {
    private String id;
    private Location begin;
    private Location end;
    private List<Location> checkpoints;

    public Route(Location begin, Location end, List<Location> checkpoints, String id) {
        this.begin = begin;
        this.end = end;
        this.checkpoints = checkpoints;
        this.id = id;
    }

    public Location getBegin() {
        return begin;
    }

    public void setBegin(Location begin) {
        this.begin = begin;
    }

    public Location getEnd() {
        return end;
    }

    public void setEnd(Location end) {
        this.end = end;
    }

    public List<Location> getCheckpoints() {
        return checkpoints;
    }

    public void setCheckpoints(List<Location> checkpoints) {
        this.checkpoints = checkpoints;
    }
}
