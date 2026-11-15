package ma.ilyas;
import org.springframework.web.bind.annotation.*;import org.springframework.http.HttpStatus;import org.springframework.web.server.ResponseStatusException;import java.util.List;
 @RestController @RequestMapping("/products") @CrossOrigin(origins="http://localhost:4200") public class ProductController {private final ProductRepository r;public ProductController(ProductRepository r){this.r=r;}
 @GetMapping public List<Product> all(){return r.findAll();}
 @GetMapping("/{id}") public Product one(@PathVariable Long id){return r.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Product add(@RequestBody Product x){x.setId(null);return r.save(x);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){r.deleteById(id);}
 }