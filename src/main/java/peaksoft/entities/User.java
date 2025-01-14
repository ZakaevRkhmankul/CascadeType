package peaksoft.entities;

import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "users")
public class User {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE,generator = "user_gen")
    @SequenceGenerator(name = "user_gen",sequenceName = "user_seq")
    private Long id;
    private String fistName;
    private String lastName;
    @Column(unique=true,nullable = false)
    private String email;
    @OneToMany(mappedBy = "user",cascade = {CascadeType.PERSIST, CascadeType.REMOVE,CascadeType.MERGE,CascadeType.DETACH})
    private List<Car>cars;

    public User(String fistName, String lastName, String email) {
        this.fistName = fistName;
        this.lastName = lastName;
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" +
                "id=" + id +
                ", fistName='" + fistName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                '}';
    }
}
