package com.example.dreeki.projectleerlingenapp.Models;

import io.objectbox.annotation.Entity;
import io.objectbox.annotation.Id;
import io.objectbox.relation.ToMany;
import io.objectbox.relation.ToOne;

/**
 * Created by dreeki on 26/10/17.
 */
@Entity
public class Route {
    @Id
    public long id;
    public ToOne<Locatie> begin;
    public ToOne<Locatie> end;
    public ToMany<Locatie> checkpoints;
    public ToOne<Mentor> routeMentor;

    public Route() {

    }

    public Route(long id) {
        this.id = id;
    }

    public long getId() {
        return id;
    }
}
