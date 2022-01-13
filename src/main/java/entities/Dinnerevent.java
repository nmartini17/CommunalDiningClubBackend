package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "dinnerevent")
public class Dinnerevent implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private int time;
    private String location;
    private String dish;
    private int price;
    @OneToMany(mappedBy = "dinnerevent")
    private List<Assignment> assignmentList = new ArrayList<>();

    public Dinnerevent() {
    }

    public Dinnerevent(int time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }

    public Dinnerevent(int time, String location, String dish, int price, List<Assignment> assignmentList) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
        this.assignmentList = assignmentList;
    }

    public int getTime() {
        return time;
    }

    public void setTime(int time) {
        this.time = time;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getDish() {
        return dish;
    }

    public void setDish(String dish) {
        this.dish = dish;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<Assignment> getAssignmentList() {
        return assignmentList;
    }

    public void setAssignmentList(List<Assignment> assignmentList) {
        this.assignmentList = assignmentList;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}