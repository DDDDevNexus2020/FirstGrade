# Section 4 Exercise

## Layered Architecture
**Layered Architecture** is an architectural pattern for designing applications that enforces a "separation of concerns" between the components of an application. A layer of an application is specifically responsible for a particular aspect of the application's behavior, and should only be dependent on the layers "below" it.

Traditionally these layers and responsibilities are:
-   **Presentation** *(interface with incoming commands and present outgoing responses)*
-   **Application** *(use incoming commands to coordinate domain object interaction)*
-   **Domain** *(handle business logic and maintain the integrity of the state of the system)*
-   **Infrastructure** *(handle business-agnostic cross-cutting functionality used by other layers)*

### Goal
The **goal** of this exercise is to design and implement the presentation and application layers that interface with the Question and Answer entities.

### Solo Phase  (45 minutes)
Though a solo exercise, it is encouraged that you freely collaborate with your teammates:
1)  Commit the work that you've done on the [FirstGrade](https://github.com/DDDDevNexus2020/FirstGrade) project so that you may switch branches. (Command: ```git -am "Section 3" ```)
2)  Switch to the **layers** branch of the GitHub project. (Command: ```git checkout layers``` then ```git pull```)
3)  Note that the Question and Answer domain classes have been fully implemented.
4)  Also note that a domain service has been implemented to handle the business logic relating to business rules regarding the interactions between Questions and Answers.
5)  Design and implement a REST controller to receive a *createQuestion* command and follow through the layers to persist the data.
6)  Design and implement a REST controller to receive a *createAnswer* command and follow through the layers to persist the data.

### Further Exploration & Self Study
-   Complete the remaining commands for Questions and Answers based on the [FirstGrade Scenario](https://github.com/DDDDevNexus2020/FirstGrade/blob/master/docs/Scenario.md).
-   Consider an endpoint that can return a Question with all of its potential Answers included in a single response JSON.
-   Consider an endpoint that can return a list of Questions based on a list of matching tags.
-   **BONUS:** How might this web service be implemented using the **Event Sourcing** design pattern? An excellent writeup on this design pattern can be found [here](https://docs.microsoft.com/en-us/azure/architecture/patterns/event-sourcing). 
