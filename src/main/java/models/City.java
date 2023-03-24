package models;

import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@EqualsAndHashCode
@Table(name = "city")
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "city_id")
    private Integer cityId;
    @Column(name = "city_name")
    private String cityName;

    @OneToMany(mappedBy = "city", cascade = CascadeType.ALL, fetch = FetchType.EAGER)
    private List<Stuff> stuffList;

    public City(Integer city_id, String city_name) {
        this.cityId = city_id;
        this.cityName = city_name;
    }

    public City(Integer city_id) {
        this.cityId = city_id;
    }

    public City() {
    }

    @Override
    public String toString() {
        return "City{" +
                "city_id=" + cityId +
                ", city_name='" + cityName + '\'' + stuffList +
                '}';
    }
}