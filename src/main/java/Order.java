import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fasterxml.jackson.datatype.jsr310.deser.LocalDateTimeDeserializer;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.math.BigDecimal;
import java.time.LocalDateTime;

@Getter
@Setter
@ToString
public class Order {
    @JsonProperty("user_id")
    private String userId;

    @JsonProperty("ordered_at")
    @JsonDeserialize(using = LocalDateTimeDeserializer.class)
    private LocalDateTime orderedAt;

    @JsonProperty("status")
    private String status;

    @JsonProperty("total")
    private BigDecimal total;
}
