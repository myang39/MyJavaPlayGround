package square;

import sun.awt.image.ImageWatched;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class DisplayPageNumber {
    public static void main(String[] args) {
        List<String> list = new LinkedList<>();
        list.add("3");
        list.add("4");
        list.add("5");
        list.add(0, "<");
        list.add(">");
        String s = String.join(" ", list);
//        System.out.println(s);

        System.out.println(displayPageNumber(10, 7, 10));
    }

    public static String displayPageNumber(int totalPages, int pagesToShow, int curPage) {
        List<String> list = new ArrayList<>();
        // amount of pages to show excluding the current page
        int leftHalf = pagesToShow / 2;
        int rightHalf = pagesToShow - 1 - leftHalf;

        int pagesRight;
        int pagesLeft;
        if (curPage - 1 >= leftHalf) {
            pagesRight = Math.min(totalPages - curPage, rightHalf);
            pagesLeft = pagesToShow - 1 - pagesRight;
        } else {
            pagesLeft = Math.min(curPage - 1, leftHalf);
            pagesRight = pagesToShow - 1 - pagesLeft;
        }
        int left = curPage - pagesLeft;
        int right = curPage + pagesRight;

        for (int i = left; i <= right; i++) {
            if (i == curPage) {
                list.add(i + "*");
            } else {
                list.add(String.valueOf(i));
            }
        }
//        System.out.println("right + \" \" + totalPages: " + right + " " + totalPages);
        if (right != totalPages) {
            list.set(list.size() - 1, "... " + totalPages);
        }
        if (left != 1) {
            list.set(0, 1 + " ...");
        }
        if (curPage != 1) {
            list.add(0, "<");
        }
        if (curPage != totalPages) {
            list.add(">");
        }
        return String.join(" ", list);
    }
}
