import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.TextStyle;
import java.util.*;
import java.util.stream.Collectors;

public class Reporter {

    public static String reportBiggestMonth(List<Order> orderList) {
        float minTotal = 0;
        Map<String, BigDecimal> monthMap = orderList.stream()
                .filter(order -> order.getStatus().equals("COMPLETED"))
                .collect(
                        Collectors.groupingBy(
                                order -> order.getOrderedAt().getMonth().getDisplayName(
                                        TextStyle.FULL, Locale.UK),
                                Collectors.reducing (BigDecimal.ZERO, Order::getTotal, BigDecimal::add)
                        )
                );
        BigDecimal maxTotal = monthMap.values().stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);

        List<String> monthsWithMaxTotal = monthMap.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(maxTotal) == 0)
                .map(Map.Entry::getKey)
                .toList();



        String mapAsString = monthMap.keySet().stream()
                .map(key -> key + "=" + monthMap.get(key))
                .collect(Collectors.joining(", ", "{", "}"));

        return monthsWithMaxTotal.toString();
        //return "";
    }
}
