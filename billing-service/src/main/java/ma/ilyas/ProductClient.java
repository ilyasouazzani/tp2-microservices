package ma.ilyas;
import org.springframework.cloud.openfeign.FeignClient;import org.springframework.web.bind.annotation.*;import java.util.Map;
@FeignClient(name="inventory-service") public interface ProductClient {@GetMapping("/products/{id}") Map<String,Object> get(@PathVariable Long id);}
