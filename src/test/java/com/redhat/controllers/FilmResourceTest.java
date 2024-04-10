package com.redhat.controllers;

import io.quarkus.test.junit.QuarkusTest;
import io.restassured.http.ContentType;
import jakarta.ws.rs.core.Response;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.given;
import static org.hamcrest.CoreMatchers.is;

@QuarkusTest
public class FilmResourceTest {
    @Test
    public void getAllFilms() {
        String requestBody = "{ \"query\": \"query { allFilms { episodeID } }\" }";

        given()
                .contentType(ContentType.JSON)
                .body(requestBody)
                .post("/graphql")
                .then()
                .body("data.allFilms.size()", is(3))
                .statusCode(Response.Status.OK.getStatusCode());
    }
}