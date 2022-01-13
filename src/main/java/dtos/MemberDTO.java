package dtos;

import entities.Assignment;
import entities.Member;

import java.util.ArrayList;
import java.util.List;

public class MemberDTO {
    private Integer id;
    private String address;
    private int phone;
    private String email;
    private int bdayyear;
    private int account;
    private AssignmentDTO assignmentDTO;

    public MemberDTO(String address, int phone, String email, int bdayyear, int account) {
        this.address = address;
        this.phone = phone;
        this.email = email;
        this.bdayyear = bdayyear;
        this.account = account;
    }

    public MemberDTO(Member member){
        this.id = member.getId();
        this.address = member.getAddress();
        this.phone = member.getPhone();
        this.email = member.getEmail();
        this.bdayyear = member.getBdayyear();
        this.account = member.getAccount();
    }

    public static List<MemberDTO> getMemberDTO(List<Member> member){
        List<MemberDTO> mDTO = new ArrayList<>();
        member.forEach(mes->mDTO.add(new MemberDTO(mes)));
        return mDTO;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
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

    public AssignmentDTO getAssignmentDTO() {
        return assignmentDTO;
    }

    public void setAssignmentDTO(AssignmentDTO assignmentDTO) {
        this.assignmentDTO = assignmentDTO;
    }
}
