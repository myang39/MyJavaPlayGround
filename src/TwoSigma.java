import java.util.*;

public class TwoSigma {

    public static void main(String[] args) {
//        int[][] bids = {{1,2,5,0},{2,1,4,2},{3,5,4,6}};
//        System.out.println(Arrays.toString(numNotGettingShare(bids, 3)));
        int[] parent = {-1, 0, 0, 1, 1, 2};
        int[] input = {1, 2, 2, 1, 1, 2};
        System.out.println(flow(parent, input));
    }

    public static int[] numNotGettingShare(int[][] bids, int totalShare) {
        //sanity check
        if (bids == null || bids[0].length == 0) {
            return null;
        }

        PriorityQueue<int[]> pq = new PriorityQueue<>(
                (a, b) -> (
                        b[2] - a[2] == 0 ? a[3] - b[3] : b[2] - a[2]
                )
        );

        for (int[] bid : bids) {
            pq.offer(bid);
        }

        while (totalShare > 0 && !pq.isEmpty()) {
            List<int[]> l = new LinkedList<>();
            int sharesNeeded = 0;
            int[] cur = pq.poll();
            sharesNeeded += cur[1];
            l.add(cur);
            int price = cur[2];

            while (!pq.isEmpty() && pq.peek()[2] == price) {
                int[] next = pq.poll();
                sharesNeeded += next[1];
                l.add(next);
            }

            if(sharesNeeded <= totalShare) {
                totalShare -= sharesNeeded;
                continue;
            }
// 4 total remaining share, x is current count of bid
// x > 4: last x - 4 people need to be added to the result
// x == 4: none need to be added to the result
// x < 4 : none need to be added to the result
            if(l.size() > totalShare) {
                int leftOver = l.size() - totalShare;
                for(int i = 1; i <= leftOver; ++i) {
                    pq.offer(l.get(l.size() - i));
                }
                break;
            } else if(l.size() <= totalShare) {
                break;
            }
        }
        int[] res = new int[pq.size()];
        for(int i = 0; i < res.length; ++i) {
            res[i] = pq.poll()[0];
        }
        Arrays.sort(res);
        return res;
    }

    private static class Node {
        int val;
        Node left;
        Node right;
        int sum;
        public Node(int val) {
            this.val = val;
            sum = val;
        }
    }

    public static int flow(int[] parent, int[] input) {
        Map<Integer, Node> map = new HashMap<>();
        for(int i = 0; i < input.length; ++i) {
            map.put(i, new Node(input[i]));
        }

        for(int i = 0; i < parent.length; ++i) {
            if(parent[i] == -1) {
                continue;
            }
            Node parentNode = map.get(parent[i]);
            Node node = map.get(i);
            if(parentNode.left == null) {
                parentNode.left = node;
            } else {
                parentNode.right = node;
            }
        }

        calculateSum(map.get(0));
        int[] res = new int[]{Integer.MAX_VALUE};
        calculateMinFlow(map.get(0).left, map.get(0).sum, res);
        calculateMinFlow(map.get(0).right, map.get(0).sum, res);
        return res[0];
    }
// root.sum - 2 * node.sum

    private static void calculateMinFlow(Node node, int rootSum, int[] res) {
        if(node == null) {
            return;
        }
        res[0] = Math.min(Math.abs(2 * node.sum - rootSum), res[0]);
        calculateMinFlow(node.left, rootSum, res);
        calculateMinFlow(node.right, rootSum, res);
    }

    private static int calculateSum(Node node) {
        if(node == null) {
            return 0;
        }

        int leftSum = calculateSum(node.left);
        int rightSum = calculateSum(node.right);
        node.sum = node.val + leftSum + rightSum;
        return node.sum;
    }


}
