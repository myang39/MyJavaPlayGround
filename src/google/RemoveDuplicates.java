package google;

import java.util.*;

public class RemoveDuplicates {
    public static void main(String[] args) {
        List<Item> items = new ArrayList<>();
        items.add(new Item(1L, 1L));
        items.add(new Item(1L, 2L));
        items.add(new Item(2L, 1L));
        items.add(new Item(2L, 4L));
        removeDuplicates(items);
        for (int i = 0; i < items.size(); i++) {
            System.out.println(items.get(i).id + " " + items.get(i).timeStamp);
        }
    }

    static class Item {
        Long id;
        Long timeStamp;

        Item (Long id, Long timeStamp) {
            this.id = id;
            this.timeStamp = timeStamp;
        }
    }
    static void removeDuplicates(List<Item> items) {
        Map<Long, Item> map = new HashMap<>();
        for (Item item : items) {
            if (!map.containsKey(item.id)) {
                map.put(item.id, item);
            } else {
                Item oldItem = map.get(item.id);
                if (oldItem.timeStamp < item.timeStamp) {
                    map.put(item.id, item);
                }
            }
        }

//        int i = 0;
//        for (Item item : map.values()) {
//            items.set(i, item);
//        }
        // This is my solution, got rejected
        ListIterator<Item> iterator = items.listIterator();
        for (Item item : map.values()) {
            Item oldItem = iterator.next();
//            oldItem = item;
            iterator.set(item);
        }

        int i = 0;
        int size = items.size();
        for (i = size - 1; i >= map.size(); i--) {
            items.remove(i);
        }
        // This is my solution, got rejected

        // The interviewer want to do swap
    }
}
