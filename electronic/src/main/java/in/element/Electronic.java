package in.element;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

import javax.persistence.*;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;

@Entity
@Table(name = "electronics")
@Getter
@Setter
@NoArgsConstructor
@ToString
public class Electronic {

    @Id
    @GeneratedValue(strategy=GenerationType.AUTO)
    private Long id;

    @NotNull
    @Column(nullable = false, unique = false, name = "coverage")
    @Min(value = 500)
    @Max(value = 10000)
    private double coverage;

    @Column(nullable = false, unique = false, name = "risk")
    private double risk = 0.35;

    @Column(nullable = false, unique = false, name = "price")
    private double price;

}

