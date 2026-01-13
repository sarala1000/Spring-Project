package com.handson.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.handson.basic.util.AWSService;
import jakarta.persistence.Entity;
import jakarta.persistence.EntityResult;
import jakarta.persistence.Id;
import jakarta.persistence.SqlResultSetMapping;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Date;

@Entity
@SqlResultSetMapping(
        name = "StudentOut",
        entities = @EntityResult(entityClass = StudentOut.class)
)
@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentOut {

    @Id
    private Long id;

    @JsonIgnore
    private LocalDateTime createdat;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonProperty("createdat")
    public LocalDateTime calcCreatedAt() { return createdat; }

    private String fullname;

    @JsonIgnore
    private LocalDateTime birthdate;

    @JsonFormat(shape = JsonFormat.Shape.STRING, pattern = "yyyy-MM-dd")
    @JsonProperty("birthdate")
    public LocalDateTime calcBirthDate() { return birthdate; }

    public static StudentOut of(Student student, AWSService awsService) {
        StudentOut res = new StudentOut();
        res.id = student.getId();
        res.createdat = student.getCreatedAt();
        res.fullname = student.getFullname();
        res.birthdate = student.getBirthDate();
        res.satscore = student.getSatScore();
        res.graduationscore = student.getGraduationScore();
        res.phone = student.getPhone();
        res.profilepicture = awsService.generateLink(student.getProfilePicture());
        res.avgscore = null;
        return res;
    }

    private Integer satscore;
    private Double graduationscore;
    private String phone;
    private String profilepicture;
    private Double avgscore;

    public StudentOut() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getFullname() {
        return fullname;
    }

    public Integer getSatscore() {
        return satscore;
    }

    public Double getGraduationscore() {
        return graduationscore;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfilepicture() {
        return profilepicture;
    }

    public Double getAvgscore() {
        return avgscore;
    }

    // Allow AVG(BigDecimal) to populate the Double field
    public void setAvgscore(Object value) {
        if (value instanceof BigDecimal bd) {
            this.avgscore = bd.doubleValue();
        } else if (value instanceof Number n) {
            this.avgscore = n.doubleValue();
        } else {
            this.avgscore = null;
        }
    }
}
