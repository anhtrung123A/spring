package springwithreactnative.travelspot.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "spot")
public class SpotEntity {
    @Id
    @Column(name = "name", columnDefinition = "TEXT")
    private String name;
    @Column(name = "image", columnDefinition = "TEXT")
    private String image;
    @Column(name = "country_name", columnDefinition = "TEXT")
    private String country_name;
}
