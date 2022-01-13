package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "boat")
public class Boat implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String brand;
    private String make;
    private String name;
    private String image;
    @ManyToMany(mappedBy = "boatList", cascade = {CascadeType.PERSIST, CascadeType.MERGE})
    private List<Owner> ownerList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.MERGE)
    private Harbour harbour;

    public List<Owner> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<Owner> ownerList) {
        this.ownerList = ownerList;
    }

    public Boat() {
    }

    public Boat(String brand, String make, String name, String image, Harbour harbour, List<Owner> ownerList) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.harbour = harbour;
        this.ownerList = ownerList;
    }

    public String getBrand() {
        return brand;
    }

    public void setBrand(String brand) {
        this.brand = brand;
    }

    public String getMake() {
        return make;
    }

    public void setMake(String make) {
        this.make = make;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getImage() {
        return image;
    }

    public void setImage(String image) {
        this.image = image;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Harbour getHarbour() {
        return harbour;
    }

    public void setHarbour(Harbour harbour) {
        this.harbour = harbour;
    }
}