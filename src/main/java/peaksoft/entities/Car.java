package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "cars")
@ToString
public class Car {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "car_gen")
    @SequenceGenerator(name = "car_gen", sequenceName = "car_seq")
    private Long id;
    private String brand;
    private String model;
    private String year;
    @ManyToOne
    private User user;

    public Car(String brand, String model, String year) {
        this.brand = brand;
        this.model = model;
        this.year = year;
    }
}
