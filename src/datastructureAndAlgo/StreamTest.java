package datastructureAndAlgo;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class StreamTest {
    public static void main(String[] args) {
        List<String> rest = Stream.of("a", "b", "c", "d").map(String::toUpperCase).collect(Collectors.toList());
        System.out.println(rest);

        List<Integer> rest2 = Stream.of(new int[][]{
                {1},{2,3},
                {4,5}, {6,7},
                {8}, {9}
        }).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
        System.out.println(rest2);

        List<Integer> rest3 = Stream.of(new int[][][]{
                {{1},{2,3}},
                {{4,5}, {6,7}},
                {{8}, {9}}
        }).flatMap(Arrays::stream).flatMapToInt(Arrays::stream).boxed().collect(Collectors.toList());
        System.out.println(rest3);

    }
}
