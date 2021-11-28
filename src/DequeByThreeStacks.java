import java.util.LinkedList;

public class DequeByThreeStacks {
    private LinkedList<Integer> stackFirst;
    private LinkedList<Integer> stackLast;
    private LinkedList<Integer> stackTemp;

    DequeByThreeStacks() {
        stackFirst = new LinkedList<>();
        stackLast = new LinkedList<>();
        stackTemp = new LinkedList<>();
    }

    public static void main(String[] args) {
            // Write your solution here
        DequeByThreeStacks dbts = new DequeByThreeStacks();
        dbts.offerFirst(1);
        dbts.offerFirst(2);
        System.out.println(dbts.pollFirst());
    }

        public void offerFirst(int element) {
            stackFirst.offerFirst(element);
        }

        public void offerLast(int element) {
            stackLast.offerFirst(element);
        }

        public Integer pollFirst() {
            if(isEmpty()) {
                return null;
            }

            peekFirst();
            return stackFirst.pollFirst();
        }

        public Integer pollLast() {
            if(isEmpty()) {
                return null;
            }

            peekLast();
            return stackLast.pollFirst();
        }

        public Integer peekFirst() {
            if(isEmpty()) {
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
            if(isEmpty()) {
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
            return size() == 0;
        }
}
