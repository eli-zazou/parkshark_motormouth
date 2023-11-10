# ParkShark

![ParkShark logo](parkshark.png)

ParkShark is a totally legit pay-to-use parking lot company.
Their goal is to take over the (underground) world of parking lots. They first tried to accomplish this by using violence.
However, they figured a mobile app that automates their processes might also get the job done.
As a bonus, that solution will involve less killing. This mobile-app will be used by both ParkShark customers
and ParkShark managers.

The front-end of ParkShark will be created by a freelancer who they'll never pay (that's what the *free* in freelancer stands for after all).

The front-end (mobile app) of ParkShark will communicate with the back-end of ParkShark using an HTTP(S) implementation of REST
using JSON as its message format. This back-end will contain the entire domain and all the business logic.
It's your job to implement this back-end.

## Technical requirements for JAVA

- Create a new GitHub repository (one per team)
    - Think about the files Git should ignore, do this before making your first commit...
- Provide a REST(ful) Web API (with JSON as the message / body format)
- Use Spring Boot or Quarkus (latest (LTS) release)
- Use Maven
- Setup a Jenkins or Travis or GitHub Actions for continuous integration
    - We'll help you with this (but honestly, it's really up to you to configure it!)...
- Perform logging (use spring-boot-starter's logging dependencies: logback and slf4j or read [this tutorial](https://quarkus.io/guides/logging) if you're using Quarkus)
    - Certainly log all interactions with the application that can be defined as "errors"
        - E.g.: unauthorized access, illegal arguments, exceptions in general,...
- Include OpenAPI using Swagger(UI) to provide a readable documentation/manual of your REST(ful) Web API
- use JPA (Hibernate or EclipseLink) in combination with a PostgreSQL or Oracle Database to store and access the data.
    - Setup a proper test configuration, which runs the integration tests against an in-memory database (e.g. H2)
        - Make it a separate technical story.
    - Correctly setup and handle the transactions.
    - Write your DDL (create tables,...) in a separate `.sql` file, which you also put under version control.
- Think about security (Basic authentication or JWT), though it doesn't have to be a priority.
    - Maybe make it a separate technical story.
    - You can use our keycloak server (https://keycloak.switchfully.com)

## Technical requirements for .NET

- Create a new GitHub repository (one per team) and add the teachers to it
- Use REST (with JSON as the message / body format)
- Use ASP.NET Core WebApi
- Use Entity Framework Core
- Use Azure DevOps Build pipeline for continuous integration
    - We'll help you with this
- Perform logging (use logging provided by .NET Core)
    - Certainly log all interactions with the application that can be defined as "errors" - E.g.: unauthorized access, illegal arguments, exceptions in general,...
    - Also log all database queries done via EF Core
- Use Swagger to provide a readable document of your WebApi
- Use MS Sql Server for a database
- Don't bother writing EF Core migrations, you can compose you database schema migrations in T-SQL
    - Do make sure to include your T-SQL changes in a Scripts folder in the GitHub Repo, so that we can inspect the migrations done to the DB
- Setup a transaction per HTTP call (we can help you with this)

## Architectural Setup

You can choose for yourself if you want to work with a multi- or single-module project. Do remember that you have four days in total for this project and multi-module can bring unnecessary complexities to the table. We advise you to only go for this approach when feeling very confident.

Guidelines for a multi-module project:

Provide a multi-module setup where each module (not package) represents a 'layer'.
- Although a Layered architecture might not be the best architecture (for big projects), when used properly(!), it is actually
  a nice way of separating concerns in your code. Read the following links:
    - https://www.codingthearchitecture.com/2016/04/25/layers_hexagons_features_and_components.html
    - http://www.codingthearchitecture.com/2015/03/08/package_by_component_and_architecturally_aligned_testing.html
- We're proposing 3 (to 5) layers: war (top-level, optional), api, service, domain and infrastructure (lowest-level, optional)
    - But, at the very least provide an api, service and domain module.
    - The war module (optional) can be used to package together the whole application (also contains the Main Class)
    - The infrastructure module (optional) can be used to contain code on which all other modules can depend.
- Think about a proper package strategy
    - What's the base package for every module?
    - Do you package per feature or per technical component, or a combination of both?

Furthermore, use DTOs for encapsulating the payload of a request or of a response to/of your Web API.
(and ideally a different DTO for the request and for the response of a call to your Web API)
- https://martinfowler.com/eaaCatalog/dataTransferObject.html

## Project requirements

The Parkshark project is supposed to be organized as a project following the SCRUM methodology.

- We want to see a clear visualized **product backlog**
- We want to see a **project kickoff** followed by **2 sprints** of two days.
- At the start of the sprint we want to see a **sprint kickoff meeting** defining the **sprint backlog** (estimated and prioritized) of that sprint.
- We want to see a clear visualized sprint backlog and up-to-date kanban board
- We want to see **daily standups** (at least once a day on fixed timeslots) discussing the current sprint progress by using a kanban board
- We want to see a **sprint review** (=demo) and **retrospective** at the end of the project
- In your team's Slack channel, we want to see a link to:
    - kanban board
    - team calendar (daily standup, sprint kickoff, scrumboard, whiteboard, ...)

**Project kickoff guidelines:**
- Choose a team name and create a Slack channel for your team
- Define team rules
    - Daily standup at X
    - Scrummaster role (fixed, rotating)
    - Ownership of stories
    - Pairing/repairing
    - Code review sessions
- Review/discuss the project (board session)
    - General understanding of the functionality (stories)
    - General understanding of the technical set-up
    - Domain modelling
- Create a Kanban board (Trello) to visualize work
    - Define the process steps
    - Define the transitions (DoD)
    - Add tasks to the board (color coded, prioritized)
        - Functional tasks (stories)
            - Technical grooming
            - Define subtasks
        - Non-functional tasks
            - technical setup
            - Github repository (add Switchfully trainers!)
            - Setup communication channels
        - Questions?
- Setup a shared drive to share useful documentation
    - domain model, detail of stories, team rules, ...
- Don't forget to add necessary information to your Slack channel (Kanban board, team calendar, ...)

## Timing

We've created two categories of stories: Must-Have's and Nice-To-Have's.
Focus on the Must-Have's, when they're fully implemented (or, to not interfere with other developers) you can implement the Nice-To-Have's.

## Functional Stories

The functional requirements are written down as stories.

- The functional analyst will be available to answer all your questions (he'll speak for the customer ParkShark)
- The functional analyst made some design decisions, if you want to challenge those, you always can. Come prepared though. 😄

### Story DI1: Create a Division
**As a Manager I want to create a division.**
> ParkShark became the company it is by doing takeovers of competing companies.
These companies were never fully merged with ParkShark, they became divisions.
- A division has a name, an original name (the original name of the bought company) and a director
- Prioritization: Must-Have

### Story DI2: Get all Divisions
**As a Manager I want to get an overview of all divisions.**
- Prioritization: Must-Have

### Story DI3: Get a Division
**As a Manager I want to get a single, specific, division.**
- Prioritization: Nice-To-Have

### Story DI4: Create a Subdivision
**As a Manager I want to create a division that is a subdivision of an already existing division**
> Sometimes, a company that was bought by ParkShark, bought some companies itself. We want to be able to express that in our application by creating subdivisions.
- A subdivision is the same as a division, it simply has another division that functions as its parent.
- Prioritization: Must-Have

### Story PL1: Create a Parking lot
**As a Manager I want to create a parking lot.**
- A parking lot has a name, a category, a (max) capacity, a contact person, and an address
- A parking lot also has a price per hour for car allocation (parking your car)
- The contact person has a name, a mobile phone number, a telephone number, an email, and an address
    - As a means of contact:
        - A contact person should always have a (valid) email address.
        - A contact person should always have a mobile phone number, or a telephone number. Both are allowed, but at least one is always required!
- The address consists of a street name, street number, and a postal code
    - A postal code consists of an actual postal code and a label (e.g. *3000, Leuven*)
- A category is currently restricted to either *underground building* or *above ground building*. However, there can be more in the future (e.g. *street*, *open space*,...).
- Prioritization: Must-Have

### Story PL2: Get all Parking lots
**As a Manager I want to get an overview of all parking lots.**
- The returned parking lots should include the id, name, capacity and the contact person's email + telephone (nothing else)
- Prioritization: Must-Have

### Story PL3: Get a Parking lot
**As a Manager I want to get a single, specified, parking lot.**
- The returned parking lot should include all the data we have of a single parking lot
- Prioritization: Nice-To-Have

### Story DIPL1: Parking lot belongs to a Division
**As a Parking lot I want to have a Division so that I know to what division I belong**
- As of now, every parking lot should have a division when being created, whenever a specific parking lot is returned, it should
  also include the information of the division.
    - This story impacts Story PL1 and PL3 (not PL2)
- Prioritization: Must-Have

### Story ME1: Register as a Member
**As a Person I want to register myself as a Member so that I can park my car in a parking lot of ParkShark.**
> In order to use the ParkShark parking lots, a person needs to become a member first.
- A member has personal information (name, address, contact information,...),
    - A name (first, last)
    - An address
    - A telephone number
    - An Email address
    - A license plate (license plate number and issuing country)
    - A registration date.
        - A technical tip: In Java, use the new Java Time API. For JPA mapping, check the following link: https://www.baeldung.com/jpa-java-time
- Prioritization: Must-Have

### Story ME2: get all Members
**As a Manager I want to get an overview of all Members**
- The returned members should contain the id, name, license plate (just the number), telephone number, email-address and registration date (nothing else)
- Prioritization: Must-Have

### Story ME3: get a Member
**As a Manager I want to get a single, identified, Member**
- The returned member should include all the data we have of a single member
- Prioritization: Nice-To-Have

### Story ML1: Select a Membership level
**As a Member I want to select a Membership level.**
> By selecting a certain membership level, a member will gain certain advantages.
- In total, there are 3 membership levels: **bronze**, **silver** and **gold**.
- All membership levels come with a same set of options, however, their values differ:
    - Bronze
        - Monthly cost: €0
        - Parking spot allocation reduction: 0%
        - Maximum allowed allocation-time of parking spot: 4 hours
    - Silver
        - Monthly cost: €10
        - Parking spot allocation price-per-hour reduction: 20%
        - Maximum allowed allocation-time of parking spot: 6 hours
    - Gold
        - Monthly cost: €40
        - Parking spot allocation price-per-hour reduction: 30%
        - Maximum allowed allocation-time of parking spot: 24 hours
- Extend the Member registration so that upon registering a member we can choose to provide a membership level. However,
  it should still be possible to not provide one. In that case, we'll automatically set the membership level to **bronze.**
- It might be possible that extra options or entire membership levels are introduced in the future. Those changes don't have to happen dynamically.
  It's okay if they only take effect after a new deploy to production.
- There are multiple ways of implementing this...
- Prioritization: Must-Have

### Story PSA1: Start Allocating a Parking spot
**As a Member I want to start allocating a Parking spot so that I can enter the parking lot and park my car.**
> Parking spot allocation is the term they use at ParkShark to indicate that a
member is in front of a parking lot which he want to enter so he can park his car. The member can use the mobile app to indicate
he wants to enter the parking lot. This is what ParkShark calls "starting Parking spot allocation".
- To start allocating a Parking spot, the following info is required:
    - The member (id)
    - The license plate number (of the actual car being parked)
    - The parking lot (id)
- Starting a parking allocation only succeeds if the following requirements are met:
    - The member is recognized by the system
    - The provided license plate number is the same as the member's license plate number
        - It can be different, but only if the membership level is Gold
    - The parking lot is recognized by the system
    - The parking lot still has available capacity (it's not full)
- Make sure that the (started) parking allocation is uniquely identifiable (hint).
    - Make sure that the member who started the parking allocation receives this id.
- A parking allocation should have a start time (when did the member start the allocation?)
- Prioritization: Must-Have

### Story PSA2: Stop Allocating a Parking spot
**As a Member I want to stop allocating a Parking spot so that I can leave the parking lot**
> When a member wants to leave a parking lot (and thus has just left his parking spot) he has to use the mobile app to
indicate this. ParkShark calls this "stopping Parking spot allocation".
- To stop a parking allocation, the following info is required:
    - The parking allocation id
    - The Member (id), (if you use security, you can check this based on the logged-in user)
        - Spring: https://www.javacodegeeks.com/2018/02/securitycontext-securitycontextholder-spring-security.html
        - Quarkus:
            - give your endpoint an additional parameter `@Context SecurityContext securityContext`
            - in your method you now have access to `securityContext.getUserPrincipal()`
- A parking allocation can only be stopped, if it's active.
- A parking allocation can only be stopped, if the provided member (id) is the owner of the allocation.
- When an allocation is stopped, a stop time should be added to the allocation.
    - Allocations that have a stop time can be seen as 'passive' / 'stopped' parking allocations. Parking allocations without a stop time can be seen
      as active allocations. Do make this explicit!
        - Thus, every parking allocation should have a status (We'll refer to it as the parking allocation status: active or stopped (for now)).
- Prioritization: Must-Have

### Story PSA3: get all parking allocations
**As a Manager I want to get an overview of all parking allocations**
- By default all allocations are returned, ordered on start time ascending
- The following filters should be available:
    - limit the returned number of allocations to the provided amount (e.g. limit to 100 results)
    - filter between all allocations, all active allocations and all stopped allocations (filter based on parking allocation status)
    - ordering can be set to descending (or ascending)
- Prioritization: Must-Have

### Story PSA4: get all parking allocations for a Member
**As a Manager I want to get an overview of all parking allocations for a given Member**
- The following filter should be available:
    - filter between all allocations, all active allocations and all stopped allocations
- Prioritization: Nice-To-Have

### Story PSA5: get all parking allocations for a Parking lot
**As a Manager I want to get an overview of all Allocations for a given Parking lot**
- The following filter should be available:
    - filter between all allocations, all active allocations and all stopped allocations
- Prioritization: Nice-To-Have

### Story PSA6: get all parking allocations (Duration)
**As a Manager I want to get an overview of all parking allocations, but also showing the duration of each allocation**
- Builds on Story **PSA3**
- Each allocation returned (when getting all Allocations) should also include the duration in `hh:mm:ss` of the allocation (`duration = start time - stop time`)
    - If the stop time is not yet filled in, we use the current time.
- Prioritization: Nice-To-Have

### Story INV1: get monthly Invoice
**As a Member I want to get my monthly Invoice so that I can pay it.** (otherwise ParkShark will come and break my legs :scream: )
> When a member requests his monthly invoice, an invoice will be generated on the fly and returned to the member. All parking allocations
with status stopped should be collected and used (you don't have to check the current month, since we'll generate an invoice every month).
- To get the latest invoice, the following info is required:
    - The member (id)
- Each parking allocation with status **passive** needs to be transformed to an Invoice Item
    - Each of those parking allocations will then get status **invoiced**
- An Invoice Item has
    - A (reference to a) parking allocation
    - A calculated price
        - The price is calculated as follows:
            - `Total number of whole hours parked (rounded up) * cost per hour (defined in parking lot)`
                - E.g. Parked for 2h:20m will be charged as 3h.
                - If any reduction applies (based on Membership level), make sure to apply it!
            - If the total number of whole hours parked (rounded up) exceeds the Maximum allowed allocation-time of parking spot
              (based on Membership level), a fine of €2.5 per hour is added to the price.
- An Invoice has
    - A unique reference number
    - A creation date
    - An expiration date
    - A collection of Invoice items (or should an Invoice Item contain a reference to Invoice?)
    - A status (Open or Closed)
        - If Closed, it should have a "date of payment"
- The returned invoice should also contain the total price to pay. This total price consists of:
    - The sum of the price of each Invoice Item (+ fine)
    - The membership monthly cost
- Prioritization: Nice-To-Have

### Story INV2: get all Invoices
**As a Manager I want to get an overview of all Invoices.**
- Prioritization: Nice-To-Have

### Story INV3: Mark an Invoice as Closed
**As a Manager I want to mark an Invoice as closed**
- To mark an invoice as closed, the following info is required:
    - The invoice unique reference
- The Invoice's status should be set to closed.
- Prioritization: Nice-To-Have

<<<<<<< HEAD
=======
- SmallRye OpenAPI ([guide](https://quarkus.io/guides/openapi-swaggerui)): Document your REST APIs with OpenAPI - comes with Swagger UI
- RESTEasy Reactive ([guide](https://quarkus.io/guides/resteasy-reactive)): A Jakarta REST implementation utilizing build time processing and Vert.x. This extension is not compatible with the quarkus-resteasy extension, or any of the extensions that depend on it.
- Hibernate ORM with Panache ([guide](https://quarkus.io/guides/hibernate-orm-panache)): Simplify your persistence code for Hibernate ORM via the active record or the repository pattern
- JDBC Driver - PostgreSQL ([guide](https://quarkus.io/guides/datasource)): Connect to the PostgreSQL database via JDBC

## Class diagram
If you want to modify the class diagram :
([class_diagram]https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=parkshark_class_diagram.drawio#R7Z1tb9s2EMc%2FjYFtQAM9y36Zp7bB0i5oum7dO8ZibLaSaFB0EvfT7yhTlu2TE6eWRAMlUKAhTckS7yfy7s8TPfDPs6d3gsymH3hC04HnJE8D%2F2Lgea7jePCfqlksa8I4XlZMBEt0o7rilv2g1ZG6ds4SWmw0lJynks02K8c8z%2BlYbtQRIfjjZrN7nm5%2B64xMKKq4HZMU1%2F7DEjld1g5Dp65%2FT9lkWn0z3PHyk4xUjXVFMSUJf1yr8i8H%2FrngXC7%2Fyp7Oaao6r%2BqX5XFvd3y6ujBBc7nPAR%2Fvz9zw6Ut%2B9v2HF19m3yZ%2Ffr5%2Fo8%2FyQNK5vuEL9sAKxnN90XJR9UTxyLKU5FA6u%2Be5vNWfOFAeT1maXJMFn6srKSQZf69KZ1Mu2A9oT1L4yIUK%2BFhIbWgvUmdjaXrOUy6gIuflF9QH3aqT6a8RtIDDbqo7dreqPpCnjYbXpJDVBfI0JbOC3ZWXrA7MiJiw%2FIxLyTPd6HHKJL2dkbFq8wg4qwuRWXXduqeokPRppwnclWHhiaA8o1IsoIk%2BIKiw0Q%2BDW5Ufa7TcSNdN17EKI420xnmyOndtcfhDG%2F0VAHgIAGi%2BbXq4Y1laTvDvdMtUDdYjKZvkUEzpvTpMdRmDJ%2BpUV0uueraAjmb55LpscxHUNZ%2F0basqDsfep%2BVTM2VJQnNlXC6JJHcr%2BGac5bLslvAM%2FkHvnTsn4SCECz%2BHsluX4Z9qLuQ5z%2BFeCCutRgGTR6pQaYWA3Y8ZxkJj4EX7UeB1BYGPIMhJRi0G%2FWIQeoYxCBAGMAzDKEnSjxaH3nGIh6ZHBTw3JEyAkwVWtSz0yoLrBKZhCBEMKYMu8SKSqU5IVe%2FUvqOunciyU5xifpfozwrLTs%2Fs%2BHs6mZ2xMwqQzWkCUZYuQpdN%2BYTDNHNZ14Ih5nlCE22Gus01VwYue%2BwblXKhIwkyl3yzP6HPxOLf9cJXdTIwli5eVOHCsrSoSk9Mrg6Dv9eOglJ9kCqsjsmTUxVs1pRCzVumuqn8fHn%2F6qZ%2FwrLQcXwuxvRlLx4iqwl97nxBMymCpkSyh82La8KgPBTulCzWGuhHpD7zjaqoo54VbFtAvt3VfnhY%2B5G7Rezygmt%2BV3f%2B80jHHkK6PWCdVwJ7OHz7cubuDVp4IGkHDTiuE3VonteOJ0doHndoYiDwg80H1d9UrLp5Uqs%2BWXNcboj4rpwDLhElVuQ6XOSKN60cOA3%2BR9Dkf%2FhBV%2BMBFjisytWBH%2Bo964g2yVyNHHTlh7pY4LA6lwEQmoSufkHAwew5kXTCy6v8jebz7HdLRc9UNOldvVLhR4iKMVHmkAsLQ88wNApe%2FdIQ4zFCOWtjeUNF0bA%2BapHoGIkmHatfJIYIidMkAT%2Fcapq9wxCadib9EYJhJtiYwujwHuL0q%2FxyLrjlom8uhqZ9y%2BrL1rggD4Sl5I6l1pMwEHU6pv1KF%2FuVz3sSVoQ6WITyt5YQRnv6Dqvxo30KsD9pNagORoPo%2BdHAtNvgYh%2FSalAGQDCvQTX4j1NlTEtCvyQY150CvDqRcfAW6Y3lwQAP5qWnAGfl0wxCCItC3ygYl5wCD6FgJSdDMBiXnDwsLeyCwYaQB4eQqxe3qjwGd98Y0ukqj8HDEwM8IpRKm5%2FfxYCw9preUcaSHp4cNA7z7I4KC0TPQBiPKT0cScw4DOFg2cSOD33jYDywrBLx2s2C7S2b1dP992I26xJ7U8nGQ%2Fx2w6%2BQCu61kkKMcoQDL9zwuuIo3DzFkgZ91JbpWkgXHnaZOb5mwK8b9jsSa%2B7xrIVGE%2Fs9rJ5%2FoI2%2Bjg2AWg%2BAwnhvNcTpyv5YNLfKWDe%2BzY7h%2FVhiHx9rITNSFI9cJBaGnmEwHvf4WBdZTgvFlM2u6QO1A0TfTBgPfiK7qclRgGB%2BOS3Cr%2F3cM1FY0dQIDsaX1CL88o9dUjMEg%2FEltQjnZtosHCMoGE%2FcjnCC5jUb07ygNymRlojeQ0zjidsRVpsEnTDVS5Lx%2FMJSYYAK37Q76fvI6IcrxHrPIeck8KNBve%2FQG%2BfEqT7fsfMQFG6oYHBvVAx6143333Gk2ar9yMYBjgDq972tcNy2cBxt7QDih3tuZTjqSjcOsM8%2F8KJyzzoGf5Tb1P0Nj7CYlI%2Fy2RwMDeOrGhZ1sztRtTu9g6H23Va77UZVDVwtq%2BvsXNHuXBHs2M%2FslfvqRl3la4VYg3JOTv7YBcJan1S2Bv%2BzHNeRtTOw23KfPDUI7LSbMo2aborlQOS%2BArcWBoJwe8OnhoGgae72uxoHQjwRuL%2BQNTaX1n0HPx9%2Br9bAo%2FKzEZedmn%2BeAW3z4b5JrJV%2B377RseKS23TFTqbHXbkax7KGG2LFZQxulWzwyi0L3bJgfAk37iKqPv4sulAntLwYP0dGcxyry3zFArudrA%2BerKNq03Vzb5xU4dJzgfPZp78%2B%2FndpI95ehvHRcU%2FpEc7EQbzcXl1%2FufxkeTkCXoxP%2B5H3Mi%2Fv%2Frq%2BsLQcAS3mc7pGHTqJx7ute5WH%2FfIiyw779eMkxlhKO1rrrBbcBus%2F8uH4w8FrF9tatHO1LfHLLzx5Ru2MRdTTNOXjclHcxgHtr6ehd6L23lHf6UrFi7F0a3cza39Cjl%2B%2FwNXrhDzEGTKz1Y9rXFkg%2BgbCuD8%2FxEJRVgpFFobeYTDuro%2BweFTO4p%2BZTbzvnQbz72GMGrY1yxMLgwkYjL%2BFMcLCj822NkmE8VcxRg0%2F0lWcjst41tLQMw3G38YYhsjobe7DEbe6Eceu9G3nZBR5JhWlYF%2FlcBcPB%2B7m4m9lAsfVXrfVKZZ3gHZzwdvCbGUSxnG%2F28KMsN5xlT9wmLEQplbwOljwGm79YnAc7rv1Yme5iiOctmYFr%2FbnpdGRp6y5DvZSxoLa98FMEWFc8VrF1eth7dOM2XcEjTFhXPhyHewuWCaMMtGl%2FAVFwblc9zjhxqYfeEJVi%2F8B](https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=class_diagram_parkshark_final_version.drawio#R7Z1tb9s2EMc%2FjYFtQAM9y3oZJ2lXLO2CZt3WvRkYi7G5SqJB0XHcTz9SpizbJztyLInuRqBAI5qSJd6P5N2fJ3rgXqXP7xiaTT%2FQGCcDx4qfB%2B71wHHsYBiK%2F2TJclUyjJxVwYSRWFWqCu7JN6wKLVU6JzHOtypyShNOZtuFY5pleMy3yhBjdLFd7ZEm2986QxMMCu7HKIGlf5CYT9VT%2BFZV%2FjMmk2n5zbalPklRWVkV5FMU08VGkXszcK8YpXz1V%2Fp8hRPZeGW7rM57u%2BfT9Y0xnPEmJ3x8HNn%2B8%2B%2FZ6Os3J7xJ%2F5n88tvjG3WVJ5TM1QNfkyeSE5qpm%2BbLsiXyBUkTlImj0SPN%2BL36xBLH4ylJ4lu0pHN5JzlH46%2Fl0WhKGfkm6qNEfGSLAvEx48rQTiCvRpLkiiaUiYKMFl9QnXQvL6a%2BhuFcnHZXPrG9U%2FQBPW9VvEU5L2%2BQJgma5eShuGV5YorYhGQjyjlNVaXFlHB8P0NjWWchcJY3wtPyvmF7l42HGcfPG0Wq%2Fd9hmmLOlqKK%2BtQrsVGdwS6PFxVadqDKpptY%2BYFCWuE8WV%2B7srj4Qxn9CAAcAICovmt68Xi8sByjX%2FGOqWqshxIyycRhgh%2FlabJ9iOhRl6qYU9myuWhokk1uizrXXlXyST22LKLi3Mek6DVTEsc4k8alHHH0sIZvRknGi2bxR%2BKfaL0r68If%2BOLGr8SxXR2Lf7I641c0E8%2BCSGFILDBZYInK8QQc7FMvY6EwcIJmFDhdQeACCDKUYoNBvxj4jmYMPICBGIbFKImSjwaH3nEIh7pHBTg3xIQJJ0tY1bDQKwu25emGwQcwJEQ0iROgVDZCIlun8h1V6YQXjWLl84dYfZYbdnpmx23oZHbGTuQBm%2BNYRFnqUDTZlE6omGZuqlJhiHkW41iZoapzS6WBixb7B3O%2BVJEEmnO63Z6iGdnyz82DL%2FJiwljq8LoMF1ZHy%2FLomfD1aeLvjbPEUXWSPFifk8WXMtisKBUlb4lspuLz42yb0zkb45eddhFITfCh66l2l419kBSGE8TJ03bIW4dBcap4UrTcqKC6SHXlO1lQRT1r2HaAfLuv%2FvC0%2BpG9Q%2Bzqhit%2B10%2F%2BeqRDByDdHrDWkcAeDV9TzuzGoPltk3bSgGNbQYfmOXY80W8ee3gWA4HrbXdUd1ux6qanlo204bjcIfZVOgeUA0qMyHW6yBVuW9mzavwPr87%2FcL2uxgMocBiV61Q%2F1GkMxgGZq5aDrvxQGwocRufSAEKd0NUvCDCYvUIcT2hxlz%2FgbJ7%2BaKjomYo6vatXKtwAUDFG0hx8aWDoGYZawatfGkI4RkhnbczvMMtr1kcNEh0jUadj9YvEECBxGcfCDzeaZu8w%2BLqdSTcCMMwYGWMxOvwsAvf32c2cUcNF31wMdfuW5ZdtcIGeEEnQA0mMJ6Eh6rR0%2B5U29CsPexJGhDpZhHJ3lhCihr7DevxonwLoTxoN6tTRIGjMxdloUNCHNBqUBhD0a1A1%2FuNUGtOQ0C8J2nUnD65OpFR4i%2FjO8KCBB%2F3Skwez8nEqQgiDQt8oaJecPAegYCQnTTBol5wcKC3sg8GEkCeHkOsXt8o8BrtpDGl1lcfgwIlBdBGMucnPP3lAWHeu7yeWdODkoHCYpw%2BYGSB6BkJ7TOnASGJGxRAuLBub8aFvHLQHlmUiXrtZsF1lszqquV7MZi0pP5Nk4yF8u%2BE%2FmArudJNCDHKEPcff8rrCwN%2B%2BxAoPddaO6VpIFx52mTm%2BYcAvW%2FbTZc0X%2B5p%2FXon9DlTPP%2BBaX8cEQK0HQH7YWA2xurI%2FFM2NMtaCbzNszMa5xD4u1EJmKM8XlMUGhp5h0B73uFAXWU0L%2BZTMbvETNgNE30xoD34Cs6nJWYCgfzktgK%2F9PBKWG9FUCw7al9QC%2BPKPWVLTBIP2JbUA5maaLBwtKGhP3A5gguYtGeMsx3cJ4oaI3kNM7YnbAVSbGJ4Q2Uqc0OzaUKGBCle3O%2Bm6wOinK8RqzyHrwnODQbXv0Bvrwio%2F37PzkDi4w4yIZ8Ns0LVu3HzHkcE5ycYejACq972NcNy2cBzs7ADi%2Bg23Moy60o096PMPnKDYs46IP4pt6j6LLswmRVcezYWhxfgqh0VV7YGV9S4fxFD7bqfebqWyRNwtqcrMXHHCXLHuxK3vqxt0la%2FlQw3Kurj4aR8IG21S2lr4n8W4DqydCrut9smTg8Beu0nTyOkmXw1E9hG4tTAQ%2BLsbPtUMBHVzt9vVOODDicD%2BH1lje2ndtWD%2FcHu1BhyVD0ZcZmp%2BPQPlzy80TWIt9fv2jQ4Vl8ykK54%2BPa470%2FezhutDxWUs3Cpe45UbFrplQfsSbthFVH12WXS%2Byl95MX4OzivHsbzvIxbYzWR98mQdlJuu63vjpAyXDgXOo0%2B%2FfvzrxkS87Q%2Fj0bHEaJ%2FSA5iJA3i5f3%2F7%2B80nw8sZ8KJ92g%2Bcl3l59%2BvttaHlDGjRn9MVdegkns227mXa9cuLLNFZOYkhlNLO1jrrBbfB5o98WO5wcOxi2%2BvtXO5C%2FPILT8552RmKqJdJQsfForiJA9pfTwPvRDXeUd%2FqSsULoXRrdjM7cUIOW1ng6nVCHsIMmdn6xzXeGyD6BkK7Pz%2BEQlFaCEUGht5h0O6uR1A8Kmbx34hJvO%2BdBv3vYUQ125plsYFBBwza38KIoPBjsq11EqH9VYyo5ke68stxEc8aGnqmQfvbGEMfGL3NfTjCVjfi2Je%2BbV1EgdOjouQ1VQ7Dbn4ZFv7i404mcFjudVteYvVIYDcXuC3MTiZhGHa2LYxLL5MJupwt76j3583np29fFn%2B%2FCbukcdCB8Lxj2dfxFDVVKCOtgmQExaj32RMV7gSwmlEjT1YjHXu7K0ZOQ1%2FS7iyRNII5hUaNPNFpiL6%2FfELbgi7kmGHzsp4uIrTLkWvRY1NzeJ4R8wKnNia0q5K2Bd0Fw4RWJnrVJmv9%2B%2BF%2F3b%2BvfWq7oXu%2F2navB%2Ff%2B0F1udNfPudnxcU%2FvBJ2uhoy9rv2OZ78Ounvx7GttD1Vi49ifBMD%2BHnYWfn3t7UGvfi4GALN9V%2F8o9OnQ194edOfNrp96UOjTj6%2B9PejFM5qYEaFnDPS77nDDdukbl%2B5f5bjfVKWjNpeSPNvbdvDVms9BF18cvW7Xnn51%2FdUOWO07%2FuKQUco312gEsdMPNMayxr8%3D)https://viewer.diagrams.net/?tags=%7B%7D&highlight=0000ff&edit=_blank&layers=1&nav=1&title=class_diagram_parkshark_final_version.drawio#R7Z1tb9s2EMc%2FjYFtQAM9y3oZJ2lXLO2CZt3WvRkYi7G5SqJB0XHcTz9SpizbJztyLInuRqBAI5qSJd6P5N2fJ3rgXqXP7xiaTT%2FQGCcDx4qfB%2B71wHHsYBiK%2F2TJclUyjJxVwYSRWFWqCu7JN6wKLVU6JzHOtypyShNOZtuFY5pleMy3yhBjdLFd7ZEm2986QxMMCu7HKIGlf5CYT9VT%2BFZV%2FjMmk2n5zbalPklRWVkV5FMU08VGkXszcK8YpXz1V%2Fp8hRPZeGW7rM57u%2BfT9Y0xnPEmJ3x8HNn%2B8%2B%2FZ6Os3J7xJ%2F5n88tvjG3WVJ5TM1QNfkyeSE5qpm%2BbLsiXyBUkTlImj0SPN%2BL36xBLH4ylJ4lu0pHN5JzlH46%2Fl0WhKGfkm6qNEfGSLAvEx48rQTiCvRpLkiiaUiYKMFl9QnXQvL6a%2BhuFcnHZXPrG9U%2FQBPW9VvEU5L2%2BQJgma5eShuGV5YorYhGQjyjlNVaXFlHB8P0NjWWchcJY3wtPyvmF7l42HGcfPG0Wq%2Fd9hmmLOlqKK%2BtQrsVGdwS6PFxVadqDKpptY%2BYFCWuE8WV%2B7srj4Qxn9CAAcAICovmt68Xi8sByjX%2FGOqWqshxIyycRhgh%2FlabJ9iOhRl6qYU9myuWhokk1uizrXXlXyST22LKLi3Mek6DVTEsc4k8alHHH0sIZvRknGi2bxR%2BKfaL0r68If%2BOLGr8SxXR2Lf7I641c0E8%2BCSGFILDBZYInK8QQc7FMvY6EwcIJmFDhdQeACCDKUYoNBvxj4jmYMPICBGIbFKImSjwaH3nEIh7pHBTg3xIQJJ0tY1bDQKwu25emGwQcwJEQ0iROgVDZCIlun8h1V6YQXjWLl84dYfZYbdnpmx23oZHbGTuQBm%2BNYRFnqUDTZlE6omGZuqlJhiHkW41iZoapzS6WBixb7B3O%2BVJEEmnO63Z6iGdnyz82DL%2FJiwljq8LoMF1ZHy%2FLomfD1aeLvjbPEUXWSPFifk8WXMtisKBUlb4lspuLz42yb0zkb45eddhFITfCh66l2l419kBSGE8TJ03bIW4dBcap4UrTcqKC6SHXlO1lQRT1r2HaAfLuv%2FvC0%2BpG9Q%2Bzqhit%2B10%2F%2BeqRDByDdHrDWkcAeDV9TzuzGoPltk3bSgGNbQYfmOXY80W8ee3gWA4HrbXdUd1ux6qanlo204bjcIfZVOgeUA0qMyHW6yBVuW9mzavwPr87%2FcL2uxgMocBiV61Q%2F1GkMxgGZq5aDrvxQGwocRufSAEKd0NUvCDCYvUIcT2hxlz%2FgbJ7%2BaKjomYo6vatXKtwAUDFG0hx8aWDoGYZawatfGkI4RkhnbczvMMtr1kcNEh0jUadj9YvEECBxGcfCDzeaZu8w%2BLqdSTcCMMwYGWMxOvwsAvf32c2cUcNF31wMdfuW5ZdtcIGeEEnQA0mMJ6Eh6rR0%2B5U29CsPexJGhDpZhHJ3lhCihr7DevxonwLoTxoN6tTRIGjMxdloUNCHNBqUBhD0a1A1%2FuNUGtOQ0C8J2nUnD65OpFR4i%2FjO8KCBB%2F3Skwez8nEqQgiDQt8oaJecPAegYCQnTTBol5wcKC3sg8GEkCeHkOsXt8o8BrtpDGl1lcfgwIlBdBGMucnPP3lAWHeu7yeWdODkoHCYpw%2BYGSB6BkJ7TOnASGJGxRAuLBub8aFvHLQHlmUiXrtZsF1lszqquV7MZi0pP5Nk4yF8u%2BE%2FmArudJNCDHKEPcff8rrCwN%2B%2BxAoPddaO6VpIFx52mTm%2BYcAvW%2FbTZc0X%2B5p%2FXon9DlTPP%2BBaX8cEQK0HQH7YWA2xurI%2FFM2NMtaCbzNszMa5xD4u1EJmKM8XlMUGhp5h0B73uFAXWU0L%2BZTMbvETNgNE30xoD34Cs6nJWYCgfzktgK%2F9PBKWG9FUCw7al9QC%2BPKPWVLTBIP2JbUA5maaLBwtKGhP3A5gguYtGeMsx3cJ4oaI3kNM7YnbAVSbGJ4Q2Uqc0OzaUKGBCle3O%2Bm6wOinK8RqzyHrwnODQbXv0Bvrwio%2F37PzkDi4w4yIZ8Ns0LVu3HzHkcE5ycYejACq972NcNy2cBzs7ADi%2Bg23Moy60o096PMPnKDYs46IP4pt6j6LLswmRVcezYWhxfgqh0VV7YGV9S4fxFD7bqfebqWyRNwtqcrMXHHCXLHuxK3vqxt0la%2FlQw3Kurj4aR8IG21S2lr4n8W4DqydCrut9smTg8Beu0nTyOkmXw1E9hG4tTAQ%2BLsbPtUMBHVzt9vVOODDicD%2BH1lje2ndtWD%2FcHu1BhyVD0ZcZmp%2BPQPlzy80TWIt9fv2jQ4Vl8ykK54%2BPa470%2FezhutDxWUs3Cpe45UbFrplQfsSbthFVH12WXS%2Byl95MX4OzivHsbzvIxbYzWR98mQdlJuu63vjpAyXDgXOo0%2B%2FfvzrxkS87Q%2Fj0bHEaJ%2FSA5iJA3i5f3%2F7%2B80nw8sZ8KJ92g%2Bcl3l59%2BvttaHlDGjRn9MVdegkns227mXa9cuLLNFZOYkhlNLO1jrrBbfB5o98WO5wcOxi2%2BvtXO5C%2FPILT8552RmKqJdJQsfForiJA9pfTwPvRDXeUd%2FqSsULoXRrdjM7cUIOW1ng6nVCHsIMmdn6xzXeGyD6BkK7Pz%2BEQlFaCEUGht5h0O6uR1A8Kmbx34hJvO%2BdBv3vYUQ125plsYFBBwza38KIoPBjsq11EqH9VYyo5ke68stxEc8aGnqmQfvbGEMfGL3NfTjCVjfi2Je%2BbV1EgdOjouQ1VQ7Dbn4ZFv7i404mcFjudVteYvVIYDcXuC3MTiZhGHa2LYxLL5MJupwt76j3583np29fFn%2B%2FCbukcdCB8Lxj2dfxFDVVKCOtgmQExaj32RMV7gSwmlEjT1YjHXu7K0ZOQ1%2FS7iyRNII5hUaNPNFpiL6%2FfELbgi7kmGHzsp4uIrTLkWvRY1NzeJ4R8wKnNia0q5K2Bd0Fw4RWJnrVJmv9%2B%2BF%2F3b%2BvfWq7oXu%2F2navB%2Ff%2B0F1udNfPudnxcU%2FvBJ2uhoy9rv2OZ78Ounvx7GttD1Vi49ifBMD%2BHnYWfn3t7UGvfi4GALN9V%2F8o9OnQ194edOfNrp96UOjTj6%2B9PejFM5qYEaFnDPS77nDDdukbl%2B5f5bjfVKWjNpeSPNvbdvDVms9BF18cvW7Xnn51%2FdUOWO07%2FuKQUco312gEsdMPNMayxr8%3D)

Here is class diagram :

![current diagram](parkshark_class_diagram.jpg)
>>>>>>> 1f3ca851bf316f710c5c306aaccb6cef74fe3c9f
