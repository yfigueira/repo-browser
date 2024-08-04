# *GitHub Repository Browser*

## *Description*
Server-side application for a GitHub Repository browser.

Get a list of a user's repositories by providing their username. The server returns a list of the user's public github repositories, which are not forks.

## *Requirements*

This project was built using SpringBoot with **Java 21** and **Maven** as the build tool.

## *Running the app*
In order to run this application you will need to:

- clone this repository: `git clone https://github.com/yfigueira/repo-browser.git`
- navigate to the project root directory: `cd repo-browser`
- run the application: `mvn spring-boot:run`
- in your web browser, navigate to `http://localhost:8080/swagger-ui.html` and try making a GET request providing a GitHub username
