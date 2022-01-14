package entities;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "assignment")
@NamedQuery(name = "Assignment.deleteAllRows", query = "DELETE from Assignment ")
public class Assignment implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    private String famName;
    private String contactInfo;
    @Temporal(TemporalType.DATE)
    private Date createDate;
    @ManyToMany(mappedBy = "assignmentList")
    private List<Member> memberList = new ArrayList<>();
    @ManyToOne(cascade = CascadeType.PERSIST)
    private Dinnerevent dinnerevent;

    public Assignment() {
    }

    public Assignment(String famName, String contactInfo, List<Member> memberList, Dinnerevent dinnerevent) {
        this.famName = famName;
        this.contactInfo = contactInfo;
        this.memberList = memberList;
        this.dinnerevent = dinnerevent;
        this.createDate = new Date();
    }


    public String getFamName() {
        return famName;
    }

    public void setFamName(String famName) {
        this.famName = famName;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public String getContactInfo() {
        return contactInfo;
    }

    public void setContactInfo(String contactInfo) {
        this.contactInfo = contactInfo;
    }

    public List<Member> getMemberList() {
        return memberList;
    }

    public void setMemberList(List<Member> memberList) {
        this.memberList = memberList;
    }

    public Dinnerevent getDinnerevent() {
        return dinnerevent;
    }

    public void setDinnerevent(Dinnerevent dinnerevent) {
        this.dinnerevent = dinnerevent;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }
}