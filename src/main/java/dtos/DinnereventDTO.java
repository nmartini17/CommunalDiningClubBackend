package dtos;

import entities.Dinnerevent;
import entities.Member;

import java.util.ArrayList;
import java.util.List;

public class DinnereventDTO {
    private Integer id;
    private int time;
    private String location;
    private String dish;
    private int price;

    public DinnereventDTO(int time, String location, String dish, int price) {
        this.time = time;
        this.location = location;
        this.dish = dish;
        this.price = price;
    }

    public DinnereventDTO(Dinnerevent dinnerevent){
        this.id = dinnerevent.getId();
        this.time = dinnerevent.getTime();
        this.location = dinnerevent.getLocation();
        this.dish = dinnerevent.getDish();
        this.price = dinnerevent.getPrice();
    }

    public static List<DinnereventDTO> getDinnerDTO(List<Dinnerevent> dinnerevent){
        List<DinnereventDTO> dDTO = new ArrayList<>();
        dinnerevent.forEach(des->dDTO.add(new DinnereventDTO(des)));
        return dDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
