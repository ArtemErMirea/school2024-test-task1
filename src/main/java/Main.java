import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.List;

public class Main {
    public static void main(String[] args) {
        try {
            File file = new File("format.json");
            ObjectMapper objectMapper = new ObjectMapper();
            List<Order> orderList = objectMapper.readValue(file, new TypeReference<List<Order>>() {});
            //for (Order order : orderList) { System.out.println(order); }
            System.out.println (
                    Reporter.reportBiggestMonth(orderList)
            );


        } catch (IOException e){
            System.out.println("No file found");
            e.printStackTrace();
        }
    }
}
