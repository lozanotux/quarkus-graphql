# Quarkus - GraphQL

This project uses Quarkus, the Supersonic Subatomic Java Framework to test GraphQL.

## How to use this project?

**Prerequisites:**
  * JDK 17 (and configure `JAVA_HOME` environment variable)
  * Maven 3.9.6

1. To Create the scaffold (initial structure) you can run next command:
    ```shell script
    mvn io.quarkus.platform:quarkus-maven-plugin:3.9.1:create \
      -DprojectGroupId=com.redhat \
      -DprojectArtifactId=quarkus-graphql \
      -Dextensions='quarkus-smallrye-graphql' \
      -DnoCode
    ```
    ```shell script
    cd quarkus-graphql
    ```
    
    > **NOTE:** if you already have a quarkus project, and you need add the graphql dependency only. You can run next command:
    > ```shell script
    > mvn quarkus:add-extension -Dextensions='quarkus-smallrye-graphql'
    > ```

2. You can run your application locally using next command:
    ```shell script
    mvn quarkus:dev
    ```

3. Introspect and see the full schema of the GraphQL API running the following command:
    ```shell script
    curl http://localhost:8080/graphql/schema.graphql
    ```

4. Play with GraphQL UI (Experimental - not included in the MicroProfile specification). GraphQL UI is a great tool permitting easy interaction with your GraphQL APIs.
    > **NOTE:** The Quarkus `smallrye-graphql` extension ships with GraphiQL and enables it by default in dev and test modes, but it can also be explicitly configured for production mode as well, by setting the `quarkus.smallrye-graphql.ui.always-include` configuration property to `true`.
    
    Open the [http://localhost:8080/q/graphql-ui/](http://localhost:8080/q/graphql-ui/) URL in your favorite web browser.
    
    And make a Query to get all Films, for example:
    ```
    {
      allFilms {
        director,
        heroes {
          name
          surname
          darkSide
        }
      }
    }
    ```
   
   Or make a Query to get specific films, for example:
   ```
   query getFilms {
     film0 : film(filmId: 0) {
       episodeID,
       title,
       heroes {
         name
       }
     }
     film1 : film(filmId: 1) {
       episodeID,
       title,
       releaseDate
     }
   }
   ```
   
   Or create a new Hero doing a `Mutation`, for example:
   ```
   mutation createNewHero {
     createHero(hero: {
       name: "Ahsoka",
       surname: "Tano",
       height: 1.70,
       mass: 54,
       darkSide: false,
       lightSaber: WHITE,
       episodeIds: [1,2]
     }) {
       name,
       surname
     }
   }
   ```
   
   Or you can delete a Hero, for example:
   ```
   mutation deleteAHero {
     deleteHero(id:1) {
       name,
       surname
     }
   }
   ```

6. Make an HTTP call using CURL to explore the required parameters and the returned response: 
   ```shell script
   curl -X POST localhost:8080/graphql -H "Content-Type: application/json" -d '{ "query": "query { allFilms { episodeID } }" }'
   ````
   
   The above HTTP call should retrn the following JSON:
   ```json
   {
      "data": {
         "allFilms": [
            {
               "episodeID": 4
            },
            {
               "episodeID": 5
            },
            {
               "episodeID": 6
            }
        ]
      }
   }
   ```