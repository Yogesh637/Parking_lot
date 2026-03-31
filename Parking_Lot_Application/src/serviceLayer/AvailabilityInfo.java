package serviceLayer;

import java.util.*;

public class AvailabilityInfo {

    Map<Integer, Map<String, Integer>> data = new HashMap<>();

    public void add(int floor, String type) {
        data.putIfAbsent(floor, new HashMap<>());
        Map<String, Integer> inner = data.get(floor);
        inner.put(type, inner.getOrDefault(type, 0) + 1);
    }

    @Override
    public String toString() {

        StringBuilder sb = new StringBuilder();

        for (int floor : data.keySet()) {
            sb.append("Floor ").append(floor).append(":\n");

            Map<String, Integer> slots = data.get(floor);

            for (String type : slots.keySet()) {
                sb.append("  ").append(type)
                  .append(": ")
                  .append(slots.get(type))
                  .append("\n");
            }
        }

        return sb.toString();
    }
}