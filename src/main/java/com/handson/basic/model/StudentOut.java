package com.handson.basic.model;

import com.fasterxml.jackson.annotation.JsonAutoDetect;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import java.time.LocalDateTime;

@JsonInclude(JsonInclude.Include.NON_NULL)
@JsonAutoDetect(fieldVisibility = JsonAutoDetect.Visibility.NONE, getterVisibility = JsonAutoDetect.Visibility.PUBLIC_ONLY)
public class StudentOut {

    private Long id;

    @JsonIgnore
    private LocalDateTime createdat;

    public String getCreatedat() {
        if (createdat == null) return null;
        return createdat.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss"));
    }

    private String fullname;
    
    @JsonIgnore
    private LocalDateTime birthdate;

    public String getBirthdate() {
        if (birthdate == null) return null;
        return birthdate.format(java.time.format.DateTimeFormatter.ofPattern("yyyy-MM-dd"));
    }

    private Integer satscore;
    private Double graduationscore;

    private String phone;
    private String profilepicture;

    public Integer getSatScore() {
        return satscore;
    }

    public Double getGraduationScore() {
        return graduationscore;
    }

    public String getFullname() {
        return fullname;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone() {
        return phone;
    }

    public String getProfilePicture() {
        return profilepicture;
    }
}
