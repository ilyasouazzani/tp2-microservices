package ma.ilyas;
import jakarta.persistence.*;import lombok.*;import java.util.*;
@Entity @Data public class Bill {@Id @GeneratedValue(strategy=GenerationType.IDENTITY) private Long id;private Long customerId;private Date createdAt=new Date();@ElementCollection private List<Line> lines=new ArrayList<>();}
