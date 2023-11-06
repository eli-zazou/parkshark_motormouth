# parkshark

This project uses Quarkus, the Supersonic Subatomic Java Framework.

If you want to learn more about Quarkus, please visit its website: https://quarkus.io/ .

## Running the application in dev mode

You can run your application in dev mode that enables live coding using:
```shell script
./mvnw compile quarkus:dev
```

> **_NOTE:_**  Quarkus now ships with a Dev UI, which is available in dev mode only at http://localhost:8080/q/dev/.

## Packaging and running the application

The application can be packaged using:
```shell script
./mvnw package
```
It produces the `quarkus-run.jar` file in the `target/quarkus-app/` directory.
Be aware that it’s not an _über-jar_ as the dependencies are copied into the `target/quarkus-app/lib/` directory.

The application is now runnable using `java -jar target/quarkus-app/quarkus-run.jar`.

If you want to build an _über-jar_, execute the following command:
```shell script
./mvnw package -Dquarkus.package.type=uber-jar
```

The application, packaged as an _über-jar_, is now runnable using `java -jar target/*-runner.jar`.

## Creating a native executable

You can create a native executable using: 
```shell script
./mvnw package -Dnative
```

Or, if you don't have GraalVM installed, you can run the native executable build in a container using: 
```shell script
./mvnw package -Dnative -Dquarkus.native.container-build=true
```

You can then execute your native executable with: `./target/parkshark-1.0.0-SNAPSHOT-runner`

If you want to learn more about building native executables, please consult https://quarkus.io/guides/maven-tooling.

## Related Guides

- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Class diagram
If you want to modify the class diagram :
([class_diagram](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=parkshark_class_diagram.drawio#R7Z1tb9s2EMc%2FjYFtQAM9y36Zp7bB0i5oum7dO8ZibLaSaFB0EvfT7yhTlu2TE6eWRAMlUKAhTckS7yfy7s8TPfDPs6d3gsymH3hC04HnJE8D%2F2Lgea7jePCfqlksa8I4XlZMBEt0o7rilv2g1ZG6ds4SWmw0lJynks02K8c8z%2BlYbtQRIfjjZrN7nm5%2B64xMKKq4HZMU1%2F7DEjld1g5Dp65%2FT9lkWn0z3PHyk4xUjXVFMSUJf1yr8i8H%2FrngXC7%2Fyp7Oaao6r%2BqX5XFvd3y6ujBBc7nPAR%2Fvz9zw6Ut%2B9v2HF19m3yZ%2Ffr5%2Fo8%2FyQNK5vuEL9sAKxnN90XJR9UTxyLKU5FA6u%2Be5vNWfOFAeT1maXJMFn6srKSQZf69KZ1Mu2A9oT1L4yIUK%2BFhIbWgvUmdjaXrOUy6gIuflF9QH3aqT6a8RtIDDbqo7dreqPpCnjYbXpJDVBfI0JbOC3ZWXrA7MiJiw%2FIxLyTPd6HHKJL2dkbFq8wg4qwuRWXXduqeokPRppwnclWHhiaA8o1IsoIk%2BIKiw0Q%2BDW5Ufa7TcSNdN17EKI420xnmyOndtcfhDG%2F0VAHgIAGi%2BbXq4Y1laTvDvdMtUDdYjKZvkUEzpvTpMdRmDJ%2BpUV0uueraAjmb55LpscxHUNZ%2F0basqDsfep%2BVTM2VJQnNlXC6JJHcr%2BGac5bLslvAM%2FkHvnTsn4SCECz%2BHsluX4Z9qLuQ5z%2BFeCCutRgGTR6pQaYWA3Y8ZxkJj4EX7UeB1BYGPIMhJRi0G%2FWIQeoYxCBAGMAzDKEnSjxaH3nGIh6ZHBTw3JEyAkwVWtSz0yoLrBKZhCBEMKYMu8SKSqU5IVe%2FUvqOunciyU5xifpfozwrLTs%2Fs%2BHs6mZ2xMwqQzWkCUZYuQpdN%2BYTDNHNZ14Ih5nlCE22Gus01VwYue%2BwblXKhIwkyl3yzP6HPxOLf9cJXdTIwli5eVOHCsrSoSk9Mrg6Dv9eOglJ9kCqsjsmTUxVs1pRCzVumuqn8fHn%2F6qZ%2FwrLQcXwuxvRlLx4iqwl97nxBMymCpkSyh82La8KgPBTulCzWGuhHpD7zjaqoo54VbFtAvt3VfnhY%2B5G7Rezygmt%2BV3f%2B80jHHkK6PWCdVwJ7OHz7cubuDVp4IGkHDTiuE3VonteOJ0doHndoYiDwg80H1d9UrLp5Uqs%2BWXNcboj4rpwDLhElVuQ6XOSKN60cOA3%2BR9Dkf%2FhBV%2BMBFjisytWBH%2Bo964g2yVyNHHTlh7pY4LA6lwEQmoSufkHAwew5kXTCy6v8jebz7HdLRc9UNOldvVLhR4iKMVHmkAsLQ88wNApe%2FdIQ4zFCOWtjeUNF0bA%2BapHoGIkmHatfJIYIidMkAT%2Fcapq9wxCadib9EYJhJtiYwujwHuL0q%2FxyLrjlom8uhqZ9y%2BrL1rggD4Sl5I6l1pMwEHU6pv1KF%2FuVz3sSVoQ6WITyt5YQRnv6Dqvxo30KsD9pNagORoPo%2BdHAtNvgYh%2FSalAGQDCvQTX4j1NlTEtCvyQY150CvDqRcfAW6Y3lwQAP5qWnAGfl0wxCCItC3ygYl5wCD6FgJSdDMBiXnDwsLeyCwYaQB4eQqxe3qjwGd98Y0ukqj8HDEwM8IpRKm5%2FfxYCw9preUcaSHp4cNA7z7I4KC0TPQBiPKT0cScw4DOFg2cSOD33jYDywrBLx2s2C7S2b1dP992I26xJ7U8nGQ%2Fx2w6%2BQCu61kkKMcoQDL9zwuuIo3DzFkgZ91JbpWkgXHnaZOb5mwK8b9jsSa%2B7xrIVGE%2Fs9rJ5%2FoI2%2Bjg2AWg%2BAwnhvNcTpyv5YNLfKWDe%2BzY7h%2FVhiHx9rITNSFI9cJBaGnmEwHvf4WBdZTgvFlM2u6QO1A0TfTBgPfiK7qclRgGB%2BOS3Cr%2F3cM1FY0dQIDsaX1CL88o9dUjMEg%2FEltQjnZtosHCMoGE%2FcjnCC5jUb07ygNymRlojeQ0zjidsRVpsEnTDVS5Lx%2FMJSYYAK37Q76fvI6IcrxHrPIeck8KNBve%2FQG%2BfEqT7fsfMQFG6oYHBvVAx6143333Gk2ar9yMYBjgDq972tcNy2cBxt7QDih3tuZTjqSjcOsM8%2F8KJyzzoGf5Tb1P0Nj7CYlI%2Fy2RwMDeOrGhZ1sztRtTu9g6H23Va77UZVDVwtq%2BvsXNHuXBHs2M%2FslfvqRl3la4VYg3JOTv7YBcJan1S2Bv%2BzHNeRtTOw23KfPDUI7LSbMo2aborlQOS%2BArcWBoJwe8OnhoGgae72uxoHQjwRuL%2BQNTaX1n0HPx9%2Br9bAo%2FKzEZedmn%2BeAW3z4b5JrJV%2B377RseKS23TFTqbHXbkax7KGG2LFZQxulWzwyi0L3bJgfAk37iKqPv4sulAntLwYP0dGcxyry3zFArudrA%2BerKNq03Vzb5xU4dJzgfPZp78%2B%2FndpI95ehvHRcU%2FpEc7EQbzcXl1%2FufxkeTkCXoxP%2B5H3Mi%2Fv%2Frq%2BsLQcAS3mc7pGHTqJx7ute5WH%2FfIiyw779eMkxlhKO1rrrBbcBus%2F8uH4w8FrF9tatHO1LfHLLzx5Ru2MRdTTNOXjclHcxgHtr6ehd6L23lHf6UrFi7F0a3cza39Cjl%2B%2FwNXrhDzEGTKz1Y9rXFkg%2BgbCuD8%2FxEJRVgpFFobeYTDuro%2BweFTO4p%2BZTbzvnQbz72GMGrY1yxMLgwkYjL%2BFMcLCj822NkmE8VcxRg0%2F0lWcjst41tLQMw3G38YYhsjobe7DEbe6Eceu9G3nZBR5JhWlYF%2FlcBcPB%2B7m4m9lAsfVXrfVKZZ3gHZzwdvCbGUSxnG%2F28KMsN5xlT9wmLEQplbwOljwGm79YnAc7rv1Yme5iiOctmYFr%2FbnpdGRp6y5DvZSxoLa98FMEWFc8VrF1eth7dOM2XcEjTFhXPhyHewuWCaMMtGl%2FAVFwblc9zjhxqYfeEJVi%2F8B))

Here is class diagram :

![current diagram](parkshark_class_diagram.jpeg)
