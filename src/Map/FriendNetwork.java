package Map;

import java.util.*;
import java.io.*;

public class FriendNetwork {
    public static void main(String[] args) throws IOException {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        int N = Integer.parseInt(br.readLine());

        while (N-- > 0) {
            int F = Integer.parseInt(br.readLine());
            Map<String, Integer> net = new HashMap<>();

            for (int i = 0; i < F; i++) {
                String[] friends = br.readLine().split(" ");
                int result = 0;
                net.put(friends[0], net.get(friends[0]) == null ? 1 : net.get(friends[0]) + 1);
                net.put(friends[1], net.get(friends[1]) == null ? 1 : net.get(friends[1]) + 1);
                result += net.get(friends[0]);
                result += net.get(friends[1]);

                System.out.println(result);
            }
        }

    }
}