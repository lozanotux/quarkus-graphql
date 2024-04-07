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
    
    And make a Query, for example:
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