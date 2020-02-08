# FirstGrade
This project is a sample [SpringBoot](https://spring.io/projects/spring-boot) microservice initially used during a [Domain Driven Design](https://dddcommunity.org/) workshop during [DevNexus 2020](https://devnexus.com/) in Atlanta, GA and presented by a team of software architects and consultants from [Daugherty Business Solutions](https://www.daugherty.com/).

The sample company described during the workshop is fictional educational software company called "FirstGrade". FirstGrade creates tests for K-12 schools that may be administered either online or via printed tests with bubble sheets for automated grading. The company grades tests, makes them available online for teachers, and runs various metrics on the tests.

This web service is only the start of a testbed that can be used to explore Domain Driven Design, but also exercises architectural concepts and techology including:

- [SpringBoot](https://spring.io/projects/spring-boot)
- [JPA](https://spring.io/projects/spring-data-jpa)
- [Lombok](https://projectlombok.org/)
- [Gradle](https://gradle.org/)
- Layered Architecture
- [JUnit 5](https://junit.org/junit5/)
- [Mockito](https://site.mockito.org/)

And of course [Domain Driven Design](https://dddcommunity.org/), particularly the work of [Vaughn Vernon](https://vaughnvernon.co/) and his book [Implementing Domain Driven Design](https://www.amazon.com/Implementing-Domain-Driven-Design-Vaughn-Vernon/dp/0321834577) with some adjustments based on the experiences of the authors of this sample project.

## How To Use This Project
This project was built using [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) and while this project can be developed in some other IDE... why would you? Also, since the dependency management and build chosen is [Gradle](https://gradle.org/), it is possible to build this project from the command line also.

### Install Tools
Install these tools, following the instructions on their respective web sites:
- [IntelliJ IDEA Community Edition](https://www.jetbrains.com/idea/) (or another IDE if you must ;-)
- [Gradle](https://gradle.org/)
- [Git](https://git-scm.com/downloads)

### Install Lombok
The specifics for installing [Lombok](https://projectlombok.org/) depends on the IDE that you chose. If you are using IntelliJ IDEA, a plugin exists that can be found in the Marketplace:
1)  Main menu **IntelliJ IDEA | Preferences...**
2)  Enter **Plugins** in the top-left search box.
3)  Click the **Marketplace** tab.
4)  Enter **lombok** in the Marketplace search box.
5)  Click **Install** on the **Lombok** plugin.
6)  Restart the IDE.

Lombok is a "pre-processor". Technically, it rewrites the byte code that your Java code would otherwise generate by automatically generating boilerplate functions within your classes. Thus, you can add a lot of functionality by only annotating your classes and class properties with Lombok annotations.

After opening a project in IntelliJ that uses Lombok, you must turn on annotation processing. This is probably the most easily missed part of using Lombok, and knowing that this box must be checked will save you a lot of grief.
1)  Main menu **IntelliJ IDEA | Preferences...**
2)  Enter **Annotation Processors** in the top-left search box.
3)  Ensure the **Annotation Processors** properties tab is selected (under Build, Execution, Deployment -> Compiler)
4)  Check the box **Enable annotation processing**.

### Cloning the Project
From the command line, use Git to clone the project by executing the following command:

`git clone https://github.com/DDDDevNexus2020/FirstGrade.git`

### Building the Project
From the command line, use Gradle to build the project by executing the following command:

`gradle build`

### Importing into IntelliJ
1)  If no project is currently opened in IntelliJ IDEA, click **Open** on the welcome screen. Otherwise, select File | Open from the main menu.
2)  In the dialog that opens, select a file that contains a Gradle project description **build.gradle**. Click **OK**.
3)  In the dialog that opens, click **Open as Project**. IntelliJ IDEA opens and syncs the project in the IDE.
