package com.losd.tw;

import org.junit.Test;

import static org.hamcrest.MatcherAssert.assertThat;
import static org.hamcrest.core.Is.is;


/**
 * Created by andrew on 24/07/2014.
 */
public class DurationTest extends UnitTest {

   @Test
   public void shouldCalculateDurationOfRouteBetweenTwoTowns(){
       Graph graph = GraphBuilder.build("AB5");
       Route route = new Route(A, B);

       assertThat(graph.duration(route), is("5"));
   }

   @Test
    public void shouldCalculateDurationOfRouteBetweenThreeTown() {
       Graph graph = GraphBuilder.build("AB5, BC6");
       Route route = new Route(A, B, C);

       assertThat(graph.duration(route), is("13"));
   }

   @Test
   public void shouldNotCalculateDurationForNonExistentRoute(){
       Graph graph = GraphBuilder.build("AB5, CB5");
       Route route = new Route(A, C);

       assertThat(graph.duration(route), is("NO SUCH ROUTE"));
   }
}
