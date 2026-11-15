package ma.ilyas;
import org.springframework.web.bind.annotation.*;import org.springframework.web.server.ResponseStatusException;import org.springframework.http.HttpStatus;import java.util.*;
@RestController @RequestMapping("/bills") @CrossOrigin(origins="http://localhost:4200") public class BillingController {
private final BillRepository r;private final CustomerClient customers;private final ProductClient products;
public BillingController(BillRepository r,CustomerClient c,ProductClient p){this.r=r;customers=c;products=p;}
@GetMapping public List<Bill> all(){return r.findAll();}
@PostMapping @ResponseStatus(HttpStatus.CREATED) public Bill create(@RequestBody Bill bill){
if(bill.getCustomerId()==null||bill.getLines().isEmpty())throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Client et lignes requis");
customers.get(bill.getCustomerId());bill.setId(null);for(Line l:bill.getLines()){if(l.getQuantity()<=0)throw new ResponseStatusException(HttpStatus.BAD_REQUEST,"Quantité positive requise");l.setUnitPrice(((Number)products.get(l.getProductId()).get("price")).doubleValue());}return r.save(bill);}
@GetMapping("/{id}") public Map<String,Object> detail(@PathVariable Long id){Bill b=r.findById(id).orElseThrow(()->new ResponseStatusException(HttpStatus.NOT_FOUND));return Map.of("bill",b,"customer",customers.get(b.getCustomerId()),"products",b.getLines().stream().map(l->products.get(l.getProductId())).toList(),"total",b.getLines().stream().mapToDouble(l->l.getQuantity()*l.getUnitPrice()).sum());}}
