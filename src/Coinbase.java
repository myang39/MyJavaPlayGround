import javafx.scene.layout.Priority;

import java.util.List;
import java.util.PriorityQueue;

public class Coinbase {
    public static void main(String[] args) {

    }

    public List<Integer> fillOrder(List<Integer> buyOrder, List<Integer> sellOrder) {
        //sanity check
        List<Integer> ret;
        if (buyOrder == null || sellOrder == null) {
            return null;
        }

        PriorityQueue<Integer> buyQueue = new PriorityQueue<>(
                (a, b) -> (b - a)
        );

        PriorityQueue<Integer> sellQueue = new PriorityQueue<>();

        return null;

    }
}
