package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "member")
public class Member implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String address;
    private int phone;
    private String email;
    private int bdayyear;
    private int account;
    @ManyToMany()
    private List<Assignment> assignmentList = new ArrayList<>();

    public Member() {
    }

    public Member(String address, int phone, String email, int bdayyear, int account) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.bdayyear = bdayyear;
        this.account = account;
    }

    public Member(String address, int phone, String email, int bdayyear, int account, List<Assignment> assignmentList){
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.bdayyear = bdayyear;
        this.account = account;
        this.assignmentList = assignmentList;
    }

    public void addAssignment(Assignment assignment){
        if(!assignmentList.contains(assignment)){
            assignmentList.add(assignment);
        }
    }

    public List<Assignment> getAssignmentList(){
        return assignmentList;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getBdayyear() {
        return bdayyear;
    }

    public void setBdayyear(int bdayyear) {
        this.bdayyear = bdayyear;
    }

    public int getAccount() {
        return account;
    }

    public void setAccount(int account) {
        this.account = account;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}