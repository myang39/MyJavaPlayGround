package snapchat;

import java.util.*;

public class TaskScheduler {
  public static void main(String[] args) {
    String tasks = "abcbca";
    Map<Character, Integer> coolDown = new HashMap<>();
    coolDown.put('a', 2);
    coolDown.put('b', 3);
    coolDown.put('c', 1);
//        System.out.println(getTime(tasks, coolDown));

    System.out.println("shortestTime: " + shortestTime(tasks, coolDown));
    System.out.println("permu: " + permu(tasks, coolDown));
  }

  public static int getTime(String tasks, Map<Character, Integer> coolDown) {
    if (tasks == null || tasks.length() == 0) {return 0; }
    char[] taskArray = tasks.toCharArray();
    Map<Character, Integer> nextStartTime = new HashMap<>();
    int total = 0;
    for (char c : taskArray) {
      if (nextStartTime.get(c) != null) {
        total = Math.max(nextStartTime.get(c), total);
      }
      total += 1;
      nextStartTime.put(c, coolDown.get(c) + total);
    }
    return total;
  }

  static class Task {
    Character type;
    int count;
    int nextTime;
    int coolDown;

    public Task(Character type, Integer value, int i, Integer integer) {
      this.type = type;
      this.count = value;
      this.nextTime = i;
      this.coolDown = integer;
    }
  }

  /*
  貌似不太对
   */
  public static int shortestTime(String tasks, Map<Character, Integer> coolDown) {
    PriorityQueue<Task> pq = new PriorityQueue<>(
            (a, b) -> (
                    a.nextTime - b.nextTime == 0 ? b.coolDown - a.coolDown : a.nextTime - b.nextTime
            )
    );
    Map<Character, Integer> taskCount = new HashMap<>();
    char[] taskArray = tasks.toCharArray();
    for (char c : taskArray) {
      taskCount.put(c, taskCount.getOrDefault(c, 0) + 1);
    }

    for (Map.Entry<Character, Integer> e : taskCount.entrySet()) {
      Character type = e.getKey();
      pq.offer(new Task(type, e.getValue(), 0, coolDown.get(type)));
    }
    int total = 0;
    while (!pq.isEmpty()) {
      Task task = pq.poll();
      total = Math.max(total, task.nextTime);
      total++;
      task.count--;
      if (task.count == 0) {continue;}
      task.nextTime = total + task.coolDown;
      pq.offer(task);
    }

    return total;
  }

  public static int permu(String tasks, Map<Character, Integer> coolDown) {
    List<String> lists = new ArrayList<>();
    dfs(tasks.toCharArray(), 0, lists);
    int min = Integer.MAX_VALUE;
    for (String s : lists) {
      min = Math.min(min, getTime(s, coolDown));
    }
    return min;
  }

  private static void dfs(char[] tasks, int i, List<String> rest) {
    if (i == tasks.length) {
      rest.add(new String(tasks));
      return;
    }
    Set<Character> added = new HashSet<>();
    for (int j = i; j < tasks.length; j++) {
      if (added.add(tasks[j])) {
        swap(tasks, i, j);
        dfs(tasks, i + 1, rest);
        swap(tasks, i, j);
      }
    }
  }

  private static void swap(char[] tasks, int i, int j) {
    char c = tasks[i];
    tasks[i] = tasks[j];
    tasks[j] = c;
  }

}
