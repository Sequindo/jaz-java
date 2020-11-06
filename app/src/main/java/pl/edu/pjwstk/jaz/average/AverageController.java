package pl.edu.pjwstk.jaz.average;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.math.RoundingMode;

@RestController
public class AverageController {

    @GetMapping("average")
    public String getAverage(@RequestParam(value = "numbers", required = true) String numbers){
        if(numbers.isEmpty()) return "Please put parameters.";
        else
        {
            String[] parameters = numbers.split(",");
            double sum = 0;
            double paramCount = parameters.length;
            for(String str: parameters)
            {
                sum=sum+Integer.parseInt(str);
            }
            BigDecimal resultBD = new BigDecimal(sum/paramCount);
            resultBD = resultBD.setScale(2, RoundingMode.HALF_UP).stripTrailingZeros();
            String resultString = resultBD.toString();

            return "Average is: "+resultString;
        }
    }
}
