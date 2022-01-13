package multithread;

public class MultithreadThing extends Thread {
  @Override
  public void run() {
    for (int i = 1; i <= 5; i++) {
      System.out.println(i);
      try {
        Thread.sleep(1000);
      } catch (InterruptedException e) {
        e.printStackTrace();
      }
    }

  }
}
