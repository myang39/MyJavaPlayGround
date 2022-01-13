package multithread;

public class Multithreading {
  public static void main(String[] args) {
    MultithreadThing myThing = new MultithreadThing();
    MultithreadThing myThing2 = new MultithreadThing();
    // myThing.run(); will run, but would be in the same thread
    myThing.start(); // would kick off a new thread
    myThing2.start(); // would kick off a new thread

  }


}

