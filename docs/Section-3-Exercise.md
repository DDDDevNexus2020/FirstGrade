# Section 3 Exercise

## Tactical Design
In Domain-Driven Design, *Tactical Design* refers to a set of technical design patterns that can be leveraged to model the concepts within a bounded context. Many of these tactical design patterns are familiar Object-Oriented Design (OOD) patterns with specific meaning. 

[Lombok](https://projectlombok.org/) is a library that makes the coding of Aggregates, Entities, and Value objects easier than it would be if you had to code all of the boilerplate code by hand.

The **goal** of this exercise is to have designed and implemented the *Question* aggregate, an *entity* in the "Content Domain" that references a list of potential *Answers*.

### Solo Phase  (45 minutes)

1)  Read the README of the GitHub project https://github.com/DDDDevNexus2020/FirstGrade
2)  Ensure that the requisite tools and libraries are installed.
3)  **Design and implement** the Question (aggregate) and Answer (entity) classes, leveraging Lombok.

    Here are some facts about Questions and Answers:

    -   A Question consists of the question text, the recommended grade level, and a list of text tags used for searching for questions (such as "Biology" and "Cell Structure").
    -   An Answer consists of the answer text, the Question to which it belongs, and whether this is the correct Answer among all Answers owned by the Question.
    -   Answers can be edited independently of Questions.

4)  Think about how you might implement a new business rule that there may only be one correct Answer for a Question, and that no two Answers for a given Question may have the same text. Would you implement this as a Service, or as methods of the Question or Answer class? 
5)  Discuss with members of your team for suggestions.

**IMPORTANT**: There should be no public setters. Think about how the state of these objects would change based on distinct business transactions and create the appropriate methods in the classes.
 
