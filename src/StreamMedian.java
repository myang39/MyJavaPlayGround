import java.util.PriorityQueue;

public class StreamMedian {
    public static void main(String[] args) {
        MedianFinder m = new MedianFinder();
        m.addNum(1);
        m.addNum(2);
        m.addNum(3);
        System.out.println(m.findMedian());
    }

    static class MedianFinder {

        PriorityQueue<Integer> lo; // max heap
        PriorityQueue<Integer> hi; // min heap

        public MedianFinder() {
            lo = new PriorityQueue<>(
                    (a, b) -> b - a
            );
            hi = new PriorityQueue<>();
        }

        public void addNum(int num) {
            if (lo.size() == 0) {
                lo.offer(num);
//                System.out.println("size lo:" + lo.size() + " " + " hi:" + hi.size());
                return;
            }

            if (num <= lo.peek()) {
                lo.offer(num);
            } else {
                hi.offer(num);
            }

            if (lo.size() > hi.size() + 1) {
                hi.offer(lo.poll());
                return;
            }

            if (lo.size() < hi.size()) {
                lo.offer(hi.poll());
            }

//            System.out.println("size lo:" + lo.size() + " " + " hi:" + hi.size());
        }

        public double findMedian() {
            return lo.size() > hi.size() ? (double)lo.peek() : ((double)lo.peek() + hi.peek()) / 2;
        }
    }
}
