package dtos;

import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class OwnerDTO {
    private Integer id;
    private String name;
    private String address;
    private int phone;
    private BoatDTO boatDTO;

    public OwnerDTO(String name, String address, int phone) {
        this.name = name;
        this.address = address;
        this.phone = phone;
    }

    public OwnerDTO(Owner owner) {
        this.id = owner.getId();
        this.name = owner.getName();
        this.address = owner.getAddress();
        this.phone = owner.getPhone();
    }

    public static List<OwnerDTO> getOwnerDTO(List<Owner> owner){
        List<OwnerDTO> oDTO = new ArrayList<>();
        owner.forEach(oes->oDTO.add(new OwnerDTO(oes)));
        return oDTO;
    }

    public BoatDTO getBoatDTO() {
        return boatDTO;
    }

    public void setBoatDTO(BoatDTO boatDTO) {
        this.boatDTO = boatDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
