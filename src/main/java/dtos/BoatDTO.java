package dtos;

import entities.Boat;
import entities.Owner;

import java.util.ArrayList;
import java.util.List;

public class BoatDTO {
    private Integer id;
    private String brand;
    private String make;
    private String name;
    private String image;
    private HarbourDTO harbourDTO;
    private List<OwnerDTO> ownerList;
    private OwnerDTO ownerDTO;

    public BoatDTO(String brand, String make, String name, String image, HarbourDTO harbourDTO, List<OwnerDTO> ownerDTO) {
        this.brand = brand;
        this.make = make;
        this.name = name;
        this.image = image;
        this.harbourDTO = harbourDTO;
        this.ownerList = ownerDTO;
    }

    public List<OwnerDTO> getOwnerList() {
        return ownerList;
    }

    public void setOwnerList(List<OwnerDTO> ownerList) {
        this.ownerList = ownerList;
    }

    public BoatDTO(Boat boat){
        this.id = boat.getId();
        this.brand = boat.getBrand();
        this.make = boat.getMake();
        this.name = boat.getName();
        this.image = boat.getImage();
        this.harbourDTO = new HarbourDTO(boat.getHarbour());
        this.ownerList = OwnerDTO.getOwnerDTO(boat.getOwnerList());
    }

    public static List<BoatDTO> getBoatDTO(List<Boat> boat){
        List<BoatDTO> bDTO = new ArrayList<>();
        boat.forEach(bes->bDTO.add(new BoatDTO(bes)));
        return bDTO;
    }

    public OwnerDTO getOwnerDTO() {
        return ownerDTO;
    }

    public void setOwnerDTO(OwnerDTO ownerDTO) {
        this.ownerDTO = ownerDTO;
    }

    public HarbourDTO getHarbourDTO() {
        return harbourDTO;
    }

    public void setID(int id) {
        this.id = id;
    }

    public void setHarbourDTO(HarbourDTO harbourDTO) {
        this.harbourDTO = harbourDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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
}
