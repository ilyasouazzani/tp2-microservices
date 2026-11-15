package ma.ilyas;
import jakarta.persistence.*;import lombok.*;
@Embeddable @Data public class Line {private Long productId;private int quantity;private double unitPrice;}
