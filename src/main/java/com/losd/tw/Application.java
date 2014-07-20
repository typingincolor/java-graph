package com.losd.tw;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;

/**
 * Application to demonstrate the thoughtworks test cases
 *
 * Created by andrew on 20/07/2014.
 */
public class Application {
    static Town A = new Town("A");
    static Town B = new Town("B");
    static Town C = new Town("C");
    static Town D = new Town("D");
    static Town E = new Town("E");

    public static void main(String[] args) {
        try {
            InputStream stream = Application.class.getClassLoader().getResourceAsStream("graph.txt");
            InputStreamReader is = new InputStreamReader(stream);

            StringBuilder stringBuilder = new StringBuilder();
            BufferedReader br = new BufferedReader(is);

            String read = br.readLine();

            while (read != null) {
                stringBuilder.append(read);
                read = br.readLine();
            }

            Graph graph = GraphBuilder.build(stringBuilder.toString());

            // run the ThoughtWorks test cases...
            System.out.println(String.format("Output #1: %s", graph.distance(new Route(A, B, C))));
            System.out.println(String.format("Output #2: %s", graph.distance(new Route(A, D))));
            System.out.println(String.format("Output #3: %s", graph.distance(new Route(A, D, C))));
            System.out.println(String.format("Output #4: %s", graph.distance(new Route(A, E, B, C, D))));
            System.out.println(String.format("Output #5: %s", graph.distance(new Route(A, E, D))));
            System.out.println(String.format("Output #6: %s", graph.distance(new Route(A, E, D))));
            System.out.println(String.format("Output #7: %s", graph.searchByNumberOfStops(A, C, 4).size()));
            System.out.println(String.format("Output #8: %s", graph.distance(graph.getShortestRouteBetween(A, C))));
            System.out.println(String.format("Output #9: %s", graph.distance(graph.getShortestRouteBetween(B, B))));
            System.out.println(String.format("Output #10: %s", graph.searchByMaximumDistance(C, C, 29).size()));


        } catch (IOException e) {
            System.err.println("There was a problem reading graph.txt");
            e.printStackTrace();
        }
    }
}
