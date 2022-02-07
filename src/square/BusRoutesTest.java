package square;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class BusRoutesTest {

  @Test
  void numBusesToDestination() {
    int[][] routes = {
            {1,2,7},{3,6,7}
    };
    int source = 6;
    int target = 1;
    assertEquals(2, BusRoutes.numBusesToDestination(routes, source, target));
  }
}