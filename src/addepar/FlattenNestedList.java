package addepar;

import java.util.ArrayDeque;
import java.util.ArrayList;
import java.util.Deque;
import java.util.List;

public class FlattenNestedList {

    public interface NestedString {
        // @return true if this NestedInteger holds a single integer, rather than a nested list.
        boolean isString();

        // @return the single integer that this NestedInteger holds, if it holds a single integer
        // Return null if this NestedInteger holds a nested list
        String getString();

        // @return the nested list that this NestedInteger holds, if it holds a nested list
        // Return empty list if this NestedInteger holds a single integer
        List<NestedString> getList();
    }

    static class NestedStringNode implements NestedString {
        boolean isString;
        String string;
        List<NestedString> nestedStringList;

        NestedStringNode(boolean isString) {
            this.isString = isString;
        }

        public void setString(String string) {
            this.string = string;
        }

        public void setNestedStringList(List<NestedString> nestedStringList) {
            this.nestedStringList = nestedStringList;
        }

        @Override
        public boolean isString() {
            return isString;
        }

        @Override
        public String getString() {
            return this.isString? this.string : null;
        }

        @Override
        public List<NestedString> getList() {
            return this.isString? null : nestedStringList;
        }
    }
    public static void main(String[] args) {
        NestedStringNode root = new NestedStringNode(false);

        NestedStringNode node1 = new NestedStringNode(false);

        List<NestedString> listNode1 = new ArrayList<>();
        NestedStringNode node1e1 = new NestedStringNode(true);
        node1e1.setString("123");
        listNode1.add(node1e1);

        NestedStringNode node1e2 = new NestedStringNode(true);
        node1e2.setString("hello");
        listNode1.add(node1e2);

        NestedStringNode node1e3 = new NestedStringNode(true);
        node1e3.setString("bye");
        listNode1.add(node1e3);

        NestedStringNode node1e4 = new NestedStringNode(true);
        node1e4.setString("456");
        listNode1.add(node1e4);

        node1.setNestedStringList(listNode1);

        NestedStringNode node2 = new NestedStringNode(false);

        List<NestedString> listNode2 = new ArrayList<>();
        NestedStringNode node2e1 = new NestedStringNode(true);
        node2e1.setString("53");
        listNode1.add(node2e1);

        NestedStringNode node2e2 = new NestedStringNode(true);
        node2e2.setString("yo");
        listNode1.add(node2e2);

        node2.setNestedStringList(listNode2);

        List<NestedString> listRoot = new ArrayList<>();
        listRoot.add(node1);
        listRoot.add(node2);
        root.setNestedStringList(listRoot);

        List<String> rest = getEvenIndexNumber(root);
        System.out.println(rest);
    }

    public static List<String> getEvenIndexNumber(NestedStringNode root) {
        if (root == null) {
            return null;
        }
        List<String> rest = new ArrayList<>();
        Deque<NestedString> stack = new ArrayDeque<>();
        stack.offerFirst(root);
        dfs(stack, rest);
        return rest;
    }

    private static void dfs(Deque<NestedString> stack, List<String> rest) {
        int count = 0;
        StringBuilder sb = new StringBuilder();
        while (!stack.isEmpty()) {
            NestedString cur = stack.pollFirst();
            if (cur.isString()) {
                String cs = cur.getString();
                if (isNumber(cs)) {
                    Integer i = Integer.valueOf(cs);
                    sb.append(i);
                    count = addDigits(sb, rest, count);
                    continue;
                }
                if (count % 2 == 0) {
                    cs = cs.toUpperCase();
                    rest.add(cs);
                }
                count++;
            } else {
                List<NestedString> list = cur.getList();
                for (int i = list.size() - 1; i >= 0; i--) {
                    stack.offerFirst(list.get(i));
                }
            }
        }
    }

    private static boolean isNumber(String s) {
        return s.charAt(0) >= '0' && s.charAt(0) <= '9';
    }

    private static int addDigits(StringBuilder sb, List<String> rest, int count) {
        for (int i = 0; i < sb.length(); i++) {
            if (count % 2 == 0) {
                rest.add(String.valueOf(sb.charAt(i)));
            }
            count++;
        }
        sb.setLength(0);
        return count;
    }

}
