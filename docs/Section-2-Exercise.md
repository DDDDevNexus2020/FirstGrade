# Section 2 Exercise

## Strategic Design
In Domain-Driven Design, the goal of **strategic design** is to identify **bounded contexts** and their interactions. **Context mapping** is a visual process of identifying the bounded contexts of a domain. A bounded context is what creates the boundary that contains a **ubiquitous language**.

Consider your glossary of terms from the [Section 1](https://github.com/DDDDevNexus2020/FirstGrade/blob/master/docs/Section-1-Exercise.md) exercise. Some of the words may have had more than one definition. This is a strong indicator of the presence of another bounded context. There should be NO ambiguity of meaning within a bounded context.

### Goal
The goal of this exercise is to practice context mapping and relating bounded contexts using strategic design patterns.

### Solo Phase  (15 minutes)
1)  Review the [FirstGrade Scenario](https://github.com/DDDDevNexus2020/FirstGrade/blob/master/docs/Scenario.md).
2)  Attempt to group related concepts from the ubiquitous language glossary together into a bounded context. Start with the "Content Domain" -- the part of the system that deals with potential test content (Questions and Answers).
3)  Note where the same term might have a different meaning in two or more contexts (such as a "Test").
4)  Note where the same term might mean the same thing, but is used differently in different contexts (such as a "Question").
 
### Team Phase (30 minutes)
1)  Collaborate with your team to identify and agree upon a set of bounded contexts.
2)  Draw the bounded contexts using the provided materials.
3)  Explore how the bounded contexts communicate, leveraging strategic design patterns such as: 
    -   **Shared Kernel** *(a context is shared without translation between two contexts)*
    -   **Customer/Supplier** *(one context uses another's language, but one side is clearly the "owner")*
    -   **Conformist** *(one context has no say in what another context does, but depends on it)*
    -   **Anticorruption Layer** *(contexts communicate, but some translation is required)*
    -   **Separate Ways** *(contexts do not communicate, at least not via mechanisms provided by the system)*
    -   **Open Host Service** *(one context provides "standard", trusted services that others consume)*
4)  Be prepared to share your context map with the class.

**IMPORTANT**: Resist gravitating towards technical solutioning. This is an exercise in inter-business communication. Some day the bounded contexts might be owned by development teams or external companies, and may or may not be a system of micro-services. Technical considerations will be discussed in the next section.
