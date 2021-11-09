import java.util.LinkedList;

public class DequeByThreeStacks {
    private static LinkedList<Integer> stackFirst;
    private static LinkedList<Integer> stackLast;
    private static LinkedList<Integer> stackTemp;

    public static void main(String[] args) {
            // Write your solution here.
            stackFirst = new LinkedList<>();
            stackLast = new LinkedList<>();
            stackTemp = new LinkedList<>();
        }

        public void offerFirst(int element) {
            stackFirst.offerFirst(element);
        }

        public void offerLast(int element) {
            stackLast.offerFirst(element);
        }

        public Integer pollFirst() {
            if(this.isEmpty()) {
                return null;
            }

            peekFirst();
            return stackFirst.pollFirst();
        }

        public Integer pollLast() {
            if(this.isEmpty()) {
                return null;
            }

            peekLast();
            return stackLast.pollFirst();
        }

        public Integer peekFirst() {
            if(this.isEmpty()) {
                return null;
            }

            if(stackFirst.isEmpty()) {
                while(!stackLast.isEmpty()) {
                    stackTemp.offerFirst(stackLast.pollFirst());
                }

                int size = stackTemp.size() / 2;
                while(stackLast.size() < size) {
                    stackLast.offerFirst(stackTemp.pollFirst());
                }
                while(!stackTemp.isEmpty()) {
                    stackFirst.offerFirst(stackTemp.pollFirst());
                }
            }
            return stackFirst.peekFirst();
        }

        public Integer peekLast() {
            if(this.isEmpty()) {
                return null;
            }

            if(stackLast.isEmpty()) {
                while(!stackFirst.isEmpty()) {
                    stackTemp.offerFirst(stackFirst.pollFirst());
                }

                int size = stackTemp.size() / 2;
                while(stackFirst.size() < size) {
                    stackFirst.offerFirst(stackTemp.pollFirst());
                }
                while(!stackTemp.isEmpty()) {
                    stackLast.offerFirst(stackTemp.pollFirst());
                }
            }
            return stackLast.peekFirst();
        }

        public int size() {
            return stackFirst.size() + stackLast.size();
        }

        public boolean isEmpty() {
            return this.size() == 0;
        }
}
