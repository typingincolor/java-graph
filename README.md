# README #

## Design Notes ##

The railway network has been modelled as a graph represented as an adjacency list. 
 
The Graph class contains a HashMap where the keys are the Town, and the corresponding value is a list
of Trips. A Trip has a destination and a distance to the destination.

As an example if there is a railway line from A -> B of length 5, and line from A -> C of length 6: this
is modelled as a HashMap entry with a key of "A", and a value containing a trip array of {B, 5} and {C, 6}.

Routes in the network are modelling using the Route class which contains a lists of Towns representing each segment
of the Route.

You can measure the distance of a Route by finding the start point in the HashMap, and looking for the end point. 
This gives you the distance for a segment of the Route. You carry on looking in the HashMap for the next start point
until you reach the end of the route.

The search algorithms used to find the shortest route, the routes with a specified number of stops and the all
the routes with a maximum distance are all variation of a "breadth first search". Each search has a different 
constraint to ensure that the search terminates.

For the sake of simplicity, the Application to print off the results requested is invoked using Maven which was used
to build the application.

## Problems With The Implementation ##

There are a number of outstanding issues with the implementation that need to be addressed:

* The three search algorithms are essentially the same but with a different constraint. The code as implemented
  duplicates the code and it needs to be refactored to remove this duplication.
  
* The shortest distance search has two problems: firstly I have had to specify a value to get the algorithm started.
  It uses this value as an upper limit for the length of route to start the search. I chose a value of 30 as I knew
  it was sufficiently large for the network in question, but would not take too long for the algorithm to terminate.
  It should not be necessary to have this value. The second problem is that I have used a global variable in the implementation
  to keep track of the current shortest route. This is not best practice and should be refactored out.
  
* The Town class is used to model a stop on the network. As it only has a name it isn't really necessary: towns could
  be modelled as Strings.
 
* I have used Exceptions when no route exists in the search algorithms. It may be better to have made the searches
  return null values if no result could be found.
  
* Once I have found a route from the searches, I have to walk it to get the distance. It would have been better to 
  store the distance as part of the Route.

## Assumptions ##

* Distances between towns are integers
* Town names are single characters

## To run the application ##

The easiest way to run the application is to use maven [http://maven.apache.org/]

Once this is installed the application can be run by typing

    mvn clean compile exec:java -Dexec.mainClass="com.losd.tw.Application" -q
    
The file containing the graph is `./src/main/resources/graph.txt`

## CI Status ##

[ ![Codeship Status for typingincolor/thoughtworks-techincal-assignment](https://codeship.io/projects/7850e470-e5b8-0131-9be6-3221b462f0c1/status)](https://codeship.io/projects/25780)