package dtos;

import entities.Assignment;
import entities.Dinnerevent;

import java.util.List;

public class AssignmentDTO {
    private Integer id;
    private String famName;
    private int createDate;
    private String contactInfo;
    private DinnereventDTO dinnereventDTO;
    private List<MemberDTO> memberDTOList;
    private MemberDTO memberDTO;

    public AssignmentDTO(String famName, int createDate, String contactInfo, List<MemberDTO> memberDTOList, DinnereventDTO dinnereventDTO){
        this.famName = famName;
        this.createDate = createDate;
        this.contactInfo = contactInfo;
        this.memberDTOList = memberDTOList;
        this.dinnereventDTO = dinnereventDTO;
    }

    public AssignmentDTO(Assignment assignment){
        this.id = assignment.getId();
        this.famName = assignment.getFamName();
        //this.createDate = assignment.getCreateDate();
        this.contactInfo = assignment.getContactInfo();
        this.memberDTOList = MemberDTO.getMemberDTO(assignment.getMemberList());
        this.dinnereventDTO = new DinnereventDTO(assignment.getDinnerevent());
    }


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public int getCreateDate() {
        return createDate;
    }

    public void setCreateDate(int createDate) {
        this.createDate = createDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public MemberDTO getMemberDTO() {
        return memberDTO;
    }

    public void setMemberDTO(MemberDTO memberDTO) {
        this.memberDTO = memberDTO;
    }

    public List<MemberDTO> getMemberDTOList() {
        return memberDTOList;
    }

    public void setMemberDTOList(List<MemberDTO> memberDTOList) {
        this.memberDTOList = memberDTOList;
    }
}
