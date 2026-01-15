package com.handson.basic.model;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.handson.basic.util.Dates;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Size;
import org.hibernate.validator.constraints.Length;
import java.time.LocalDate;

import java.io.Serializable;

import static com.handson.basic.model.Student.StudentBuilder.aStudent;

public class StudentIn implements Serializable {

    @Length(max = 60)
    private String fullname;

    @JsonFormat(pattern="yyyy-MM-dd")
    private LocalDate birthDate;

    @Min(100)
    @Max(800)
    private Integer satScore;

    @Min(30)
    @Max(110)
    private Double graduationScore;

    @Length(max = 20)
    private String phone;
    @Email(message = "Invalid email format")
    @Size(max = 255, message = "Email is too long")
    private String email;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

    public LocalDate getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(LocalDate birthDate) {
        this.birthDate = birthDate;
    }

    public Integer getSatScore() {
        return satScore;
    }

    public void setSatScore(Integer satScore) {
        this.satScore = satScore;
    }

    public Double getGraduationScore() {
        return graduationScore;
    }

    public void setGraduationScore(Double graduationScore) {
        this.graduationScore = graduationScore;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Student toStudent() {
        return aStudent().createdAt(Dates.nowUTC()).birthDate(Dates.atUtc(birthDate)).fullname(fullname)
                .satScore(satScore).graduationScore(graduationScore)
                .phone(phone).email(email)
                .build();
    }

    public void updateStudent(Student student) {
        student.setBirthDate(Dates.atUtc(birthDate));
        student.setFullname(fullname);
        student.setSatScore(satScore);
        student.setGraduationScore(graduationScore);
        student.setPhone(phone);
        student.setEmail(email);
    }

}