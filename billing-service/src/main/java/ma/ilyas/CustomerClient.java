package ma.ilyas;
import org.springframework.cloud.openfeign.FeignClient;import org.springframework.web.bind.annotation.*;import java.util.Map;
@FeignClient(name="customer-service") public interface CustomerClient {@GetMapping("/customers/{id}") Map<String,Object> get(@PathVariable Long id);}
