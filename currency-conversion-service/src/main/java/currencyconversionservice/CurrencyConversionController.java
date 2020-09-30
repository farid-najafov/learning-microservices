package currencyconversionservice;

import lombok.AllArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.math.BigDecimal;

@RestController
@AllArgsConstructor
@Log4j2
public class CurrencyConversionController {
    
    private final CurrencyExchangeServiceProxy proxy;
    
    @GetMapping("/currency-converter/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convert(@PathVariable String from, @PathVariable String to,
                                          @PathVariable BigDecimal quantity) {
        
        ResponseEntity<CurrencyConversionBean> responseEntity = new RestTemplate().getForEntity(
                String.format("http://localhost:8000/currency-exchange/from/%s/to/%s", from, to),
                CurrencyConversionBean.class);
        
        CurrencyConversionBean response = responseEntity.getBody();
        
        return new CurrencyConversionBean(response.getId(), from, to,
                response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
    
    @GetMapping("/currency-converter-feign/from/{from}/to/{to}/quantity/{quantity}")
    public CurrencyConversionBean convertFeign(@PathVariable String from, @PathVariable String to,
                                          @PathVariable BigDecimal quantity) {
        
        CurrencyConversionBean response = proxy.retrieveExValue(from,to);
        
        log.info("{}", response);
        
        return new CurrencyConversionBean(response.getId(), from, to,
                response.getConversionMultiple(), quantity,
                quantity.multiply(response.getConversionMultiple()), response.getPort());
    }
    
    
}
