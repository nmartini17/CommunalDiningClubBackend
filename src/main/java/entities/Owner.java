package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "owner")
public class Owner implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String name;
    private String address;
    private int phone;
    @ManyToMany(cascade = { CascadeType.PERSIST, CascadeType.MERGE })
    private List<Boat> boatList = new ArrayList<>();

    public Owner() {
    }

    public Owner(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    //Hvis boatList ikke indeholder en boat, så bliver den tilføjet til boatList.
    //Hvis OwnerList ikke har en Owner, så bliver Owner tilføjet til ownerList.
    public void addBoat(Boat boat){
        if(!boatList.contains(boat)){
            boatList.add(boat);
        }
        if(!boat.getOwnerList().contains(this)){
            boat.getOwnerList().add(this);
        }
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public int getPhone() {
        return phone;
    }

    public void setPhone(int phone) {
        this.phone = phone;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public List<Boat> getBoatList() {
        return boatList;
    }

    public void setBoatList(List<Boat> boatList) {
        this.boatList = boatList;
    }
}