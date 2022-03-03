package square;

import java.util.*;

public class CalendarStream {
    public static void main(String[] args) {
        CalendarStream cs = new CalendarStream();
        cs.insert(new Calendar(5, 9, "c"));
        cs.insert(new Calendar(5, 8, "b"));
        cs.insert(new Calendar(5, 9, "a"));
        cs.insert(new Calendar(6, 9, "c"));

        TreeSet<Calendar> ts = cs.read();
        for (Calendar c : ts) {
            System.out.println(c);
        }
    }

    TreeSet<Calendar> calendars;

    // random access?

    public CalendarStream() {
        calendars = new TreeSet<>((a, b) ->
        {
            if (a.endTime != b.endTime) {
                return Integer.compare(a.endTime, b.endTime);
            }
            if (a.startTime != b.startTime) {
                return Integer.compare(a.startTime, b.startTime);
            }
            return a.key.compareTo(b.key);
        });
    }

    static class Calendar {
        int startTime;
        int endTime;
        String key;

        public Calendar(int s, int e, String k) {
            startTime = s;
            endTime = e;
            key = k;
        }

        @Override
        public boolean equals(Object o) {
            if (o == this) {
                return true;
            }
            if (!(o instanceof Calendar)) {
                return false;
            }
            Calendar c = (Calendar)o;
            return startTime == c.startTime && endTime == c.endTime && key.equals(c.key);
            // return false;
        }

        @Override
        public int hashCode() {
            return Objects.hash(startTime, endTime, key);
        }

        @Override
        public String toString() {
            return startTime + " " + endTime + " " + key;
        }
    }

    public void insert(Calendar c) {
        calendars.add(c);
    }

    public TreeSet<Calendar> read() {
        return calendars;
    }

}
