import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.time.Month;
import java.util.List;

@AllArgsConstructor
@Getter
@Setter
public class MonthReported {
    private List<String> months;
}
