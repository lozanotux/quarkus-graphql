package com.redhat.controllers;

import com.redhat.entities.Film;
import com.redhat.entities.Hero;
import com.redhat.services.GalaxyService;
import io.smallrye.graphql.api.Subscription;
import io.smallrye.mutiny.Multi;
import io.smallrye.mutiny.operators.multi.processors.BroadcastProcessor;
import org.eclipse.microprofile.graphql.GraphQLApi;
import org.eclipse.microprofile.graphql.Query;
import org.eclipse.microprofile.graphql.Mutation;
import org.eclipse.microprofile.graphql.Name;
import org.eclipse.microprofile.graphql.Description;
import org.eclipse.microprofile.graphql.DefaultValue;
import org.eclipse.microprofile.graphql.Source;

import jakarta.enterprise.context.ApplicationScoped;
import jakarta.inject.Inject;
import java.util.List;

// This annotation indicates that the CDI bean will be a GraphQL endpoint
@GraphQLApi
@ApplicationScoped
public class FilmResource {
    @Inject
    GalaxyService service;

    private final BroadcastProcessor<Hero> processor = BroadcastProcessor.create();

    // The value of the @Query annotation is optional and would implicitly be defaulted to the method name if absent.
    @Query("allFilms")
    @Description("Get all Films from a galaxy far far away")
    public List<Film> getAllFilms() {
        return service.getAllFilms();
    }

    @Query
    @Description("Get a Films from a galaxy far far away")
    public Film getFilm(@Name("filmId") int id) {
        return service.getFilm(id);
    }

    /**
     * @Source annotation allow to extend the getFilm() method adding heroes for that film
     */
    public List<Hero> heroes(@Source Film film) {
        return service.getHeroesByFilm(film);
    }

    @Query("getAllHeroes")
    @Description("Get all existing heroes")
    public List<Hero> getAllHeroes() { return service.getAllHeroes(); }

    /**
     * @Mutation annotation of GraphQL is needed when we need to create or update a Hero.
     * In case we have a database, @Transactional annotation is needed too.
     */
    @Mutation
    public Hero createHero(Hero hero) {
        service.addHero(hero);
        processor.onNext(hero);
        return hero;
    }

    @Mutation
    public Hero deleteHero(int id) {
        return service.deleteHero(id);
    }

    @Query
    public List<Hero> getHeroesWithSurname(@DefaultValue("Skywalker") String surname) {
        return service.getHeroesBySurname(surname);
    }

    /**
     * A experimental functionality to read data from websocket
     */
    @Subscription
    public Multi<Hero> heroCreated() {
        return processor;
    }
}