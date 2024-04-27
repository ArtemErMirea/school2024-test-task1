import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;

public class Reporter {

    public static String reportBiggestMonth(List<Order> orderList) {
        float minTotal = 0;
        HashMap<LocalDateTime, BigDecimal> monthMap = new HashMap<>();
        orderList.stream()
                .filter(order -> order.getStatus().equals("COMPLETED"))
                .max(Comparator.comparing(Order::getTotal));
        return "";
    }
}
