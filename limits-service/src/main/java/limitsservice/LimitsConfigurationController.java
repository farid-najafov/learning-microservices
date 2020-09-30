package limitsservice;

import lombok.AllArgsConstructor;
import org.springframework.core.env.Environment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@AllArgsConstructor
public class LimitsConfigurationController {
    
//    private final Environment env;
    private final Configuration conf;
    
    @GetMapping("/limits")
    public LimitConfiguration retrieveLimitsFromConfiguration() {
//        return new LimitConfiguration(
//                Integer.parseInt(env.getProperty("limits-service.maximum")),
//                Integer.parseInt(env.getProperty("limits-service.minimum")));
        
        return new LimitConfiguration(conf.getMaximum(), conf.getMinimum());
    }
}
