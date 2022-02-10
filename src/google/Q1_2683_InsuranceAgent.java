package google;

import java.util.PriorityQueue;

public class Q1_2683_InsuranceAgent {
  public static void main(String[] args) {

  }

  // question 1
  // How many minutes will skyler needs to wait before meeting with an agent?

  // M people have already arrived with the same demand
  // N agents (number 1 to N)
  // if more than 2 agents can serve a customer at the same time, the customer
  // will always choose the one with the smallest number
  // Skyler arrived at time 0, and all the agents are idle and start to serve
  // the customers

  // a heap solution is straightforward to simulate the queue, which is
  // required for L3. O((M+N)logN)
  /*
  Design: Variable check and case check
  Coding: A good solution is straightforward, which uses heap to simulate the
   queue
   Data Structure & Algorithms:
    One PriorityQueue is enough to solve this problem, redundant structure
    should be avoided.
    Time complexity: O((M+N)logN) for sample solution one.
   */
  public int solve(int n, int m, int[] time) {
    // variable check
    if (m < n) {
      return 0;
    }
    int[] agent = new int[2]; // service end time, index
    PriorityQueue<int[]> agents = new PriorityQueue<>(
            (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
    for (int i = 0; i < n; i++) { // time: O(nlogn) here
        agents.add(new int[]{0, i});
    }

    for (int i = 0; i < m; i++) { // time: O(mlogn) here
      agent = agents.poll();
      agent[0] += time[agent[1]];
      agents.add(agent);
    }

    return agents.peek()[0];
    // total time: O((m+n)logn)
  }
    /* Follow up 1: Who will server Skyler?
      return agents.peek()[1];
     */

    /* Follow up 2: if each agent chagnes the service time periodically
    following serving time T[i][j]. How could Skyler know the waiting time?

     */

  /*
  Time complexity can be O(MlogN) by combining the initialization process with the first possible min(M, N) customers.
   */
  int solveII(int n, int m, int[] time) {
    // variable check
    if (m < n) { // more agents than customers, no need to wait
      return 0;
    }
    int[] agent = new int[2]; // service end time, index
    PriorityQueue<int[]> agents = new PriorityQueue<>(
            (a, b) -> (a[0] != b[0] ? a[0] - b[0] : a[1] - b[1]));
    for (int i = 0; i < n; i++) { // time: O(nlogn) here
        agents.add(new int[]{time[i], i});
    }
    for (int i = n + 1; i <= m; i++) { // time: O((m-n)logn) here
      agent = agents.poll();
      agent[0] += time[agent[1]];
      agents.add(agent);
    }

    return agents.peek()[0];
    // total time: O(nlogn)
  }

  /* L4
   Design: the parameters should be discussed and compared for real world
   problem, where M is much larger than N
   Coding: An excellent answer will use binary search, to reduce the complexity on M.
   Data Structures & Algorithms: The search range should be indicated in reasonable range, like M * T_min
   Time Complexity: O(nlong(m*t_min))

   */

  int solveIII(int n, int m, int[] time) {
    // variable check
    if (m < n) {
      return 0;
    }
    int tMin = Integer.MAX_VALUE, lowerBound = 0, upperBound = 0, waitingTime = 0;
    for (int i = 0; i < n; i++) {
      tMin = Math.min(tMin, time[i]);
    }
    upperBound = m * tMin;
    // Binary Search
    while (lowerBound < upperBound) {
      waitingTime = lowerBound + (upperBound - lowerBound) / 2;
      if (numOfCustomersServed(waitingTime, time) < m) {
        lowerBound = waitingTime + 1;
      } else {
        upperBound = waitingTime;
      }
    }

    return lowerBound;
  }

  int numOfCustomersServed(int time, int[] T) {
    int num = 0;
    for (int i : T) {
      num += time / i;
    }
    return num;
  }

   /* Follow up 1: Who will server Skyler?
      the first index that time % i == 0
      do the numOfCustomersServed Again after the binary search
     */
}
