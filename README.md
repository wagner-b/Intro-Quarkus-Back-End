# Project Introduction to Quarkus (Dev Java Back-End)
### Course by Ada Tech
Simple Rest API (Quarkus + Maven) to manage Courses and Lessons.
<br>
Command used to initialize Quarkus with the dependencies (extensions):

```
quarkus create app tech.ada:project-intro-quarkus --java=21 \
--extension="rest-jackson,hibernate-orm-panache,jdbc-h2,jdbc-mysql,hibernate-validator,smallrye-health"
```
