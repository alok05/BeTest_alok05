# Getting Started
## Description
This API takes any input as source for available data sources it can read data and 
populate that data as view.
Api is designed using spring-boot 

## Points to Set up code in IDE
1. lombok dependency is used , so if lombok plugin is not installed in your IDE then please install it.
2. Code repository should be placed at a location where you have access to read file. i.e, Workbook.csv
3. jdk1.8 is used, make sure JAVA_HOME is in your machine
4. Import this project as gradle project


## How to build the code
Go to project root directory and run
```
/BeTest_alok05>gradlew clean build
```

## How to run the application
1. Using the jar
```
/BeTest_alok05/build/libs> java -jar BeTest_alok05-0.0.1-SNAPSHOT.jar
```
2. Using bootRun
```
/BeTest_alok05>gradlew bootRun
```

After successful run test using this http://localhost:9091/records in browser

### Reference Documentation
For further reference, please consider the following sections:

* [Official Gradle documentation](https://docs.gradle.org)
* [Spring Boot Gradle Plugin Reference Guide](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/)
* [Create an OCI image](https://docs.spring.io/spring-boot/docs/2.4.4/gradle-plugin/reference/html/#build-image)
* [Spring Web](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-developing-web-applications)
* [Thymeleaf](https://docs.spring.io/spring-boot/docs/2.4.4/reference/htmlsingle/#boot-features-spring-mvc-template-engines)

### Guides
The following guides illustrate how to use some features concretely:

* [Building a RESTful Web Service](https://spring.io/guides/gs/rest-service/)
* [Serving Web Content with Spring MVC](https://spring.io/guides/gs/serving-web-content/)
* [Building REST services with Spring](https://spring.io/guides/tutorials/bookmarks/)
* [Handling Form Submission](https://spring.io/guides/gs/handling-form-submission/)

### Additional Links
These additional references should also help you:

* [Gradle Build Scans – insights for your project's build](https://scans.gradle.com#gradle)

