package currencyexchangeservice;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@Log4j2
public class CurrencyExchangeController {
    
    private final Environment env;
    private final ExchangeValueRepository exRepo;
    
    @GetMapping("currency-exchange/from/{from}/to/{to}")
    public ExchangeValue retrieveExchangeValue(@PathVariable String from, @PathVariable String to) {
    
//        ExchangeValue exchangeValue = new ExchangeValue(10L, "USD", "AZN", BigDecimal.valueOf(1.7));
        ExchangeValue exchangeValue = exRepo.findByFromAndTo(from, to);
    
        log.info("{}", exchangeValue);
        
        exchangeValue.setPort(Integer.parseInt(env.getProperty("local.server.port")));
        return exchangeValue;
    }
    
}
