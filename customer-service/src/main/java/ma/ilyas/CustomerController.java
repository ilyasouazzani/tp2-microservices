package ma.ilyas;
import org.springframework.web.bind.annotation.*;import org.springframework.http.HttpStatus;import org.springframework.web.server.ResponseStatusException;import java.util.List;
 @RestController @RequestMapping("/customers") @CrossOrigin(origins="http://localhost:4200") public class CustomerController {private final CustomerRepository r;public CustomerController(CustomerRepository r){this.r=r;}
 @GetMapping public List<Customer> all(){return r.findAll();}
 @GetMapping("/{id}") public Customer one(@PathVariable Long id){return r.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));}
 @PostMapping @ResponseStatus(HttpStatus.CREATED) public Customer add(@RequestBody Customer x){x.setId(null);return r.save(x);}
 @DeleteMapping("/{id}") public void delete(@PathVariable Long id){r.deleteById(id);}
 }