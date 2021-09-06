import java.io.*;
import java.util.*;

public class test {
    static String[] array;

    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        Map<Integer, String> map = new TreeMap<>();

        map.put(1, "6");
        map.put(3, "5");
        map.put(2, "9");
        map.put(0, "1");

        System.out.println(map);
    }
}