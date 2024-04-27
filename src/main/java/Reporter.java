import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.math.BigDecimal;
import java.time.format.TextStyle;
import java.util.Arrays;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

public class Reporter {

    // формирует отчёт
    public static String reportBiggestMonth(ObjectMapper objectMapper, List<Order> orderList) {
        // Map, хранящий месяц как ключ и сумму заказов за него как значение
        Map<String, BigDecimal> monthMap = orderList.stream()
                // Выбирает только завершённые заказы
                .filter(order -> order.getStatus().equals("COMPLETED"))
                // Собирает элементы в коллекцию
                .collect(
                        //Группировка по месяцам
                        Collectors.groupingBy(
                                // Месяц в текстовом формате, маленькми буквами
                                order -> order.getOrderedAt().getMonth().getDisplayName(
                                        TextStyle.FULL, Locale.UK).toLowerCase(),
                        // Сложение сумм заказов путём свёртки
                        Collectors.reducing (BigDecimal.ZERO, Order::getTotal, BigDecimal::add)
                        )
                );

        // Наибольшая сумма
        BigDecimal maxTotal = monthMap.values().stream()
                .max(BigDecimal::compareTo)
                .orElse(BigDecimal.ZERO);


        //  Проходит по наборам ключ-значение
        //  Месяц, за который сумма заказов равна максимальной, попадает в список
        List<String> monthsWithMaxTotal = monthMap.entrySet().stream()
                .filter(entry -> entry.getValue().compareTo(maxTotal) == 0)
                .map(Map.Entry::getKey)
                .toList();

        // Создаём экземпляр класса, содержащего список месяцев
        MonthReported monthReport = new MonthReported(monthsWithMaxTotal);

        try {
            // С помощью objectMapper получаем String в формате JSON
            return objectMapper.writeValueAsString(monthReport);
        } catch (JsonProcessingException e) {
            return Arrays.toString(e.getStackTrace());
        }
    }
}
