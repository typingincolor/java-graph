# README #

## Design Notes ##

## Problems With The Implementation ##

## Assumptions ##

* Distances between towns are integers
* Town names are single characters

## To run the application ##

The easiest way to run the application is to use maven [http://maven.apache.org/]

Once this is installed the application can be run by typing

    mvn clean compile exec:java -Dexec.mainClass="com.losd.tw.Application" -q
    
The file containing the graph is ./src/main/resources/graph.txt

## CI Status ##

[ ![Codeship Status for typingincolor/thoughtworks-techincal-assignment](https://codeship.io/projects/7850e470-e5b8-0131-9be6-3221b462f0c1/status)](https://codeship.io/projects/25780)