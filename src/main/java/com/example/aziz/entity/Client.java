package com.example.aziz.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.util.Date;

@Entity
public class Client {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private Long numMat;
    private String comment;

    @NotNull(message = "Name cannot be null")
    private String name;

    @NotNull(message = "Insurance number cannot be null")
    private String insuranceNumber;

    private String dateOfBirth;

    @Column(nullable = true)
    private Boolean question1;  // Nullable
    @Column(nullable = true)
    private Boolean question2;  // Nullable
    @Column(nullable = true)
    private Boolean question3;  // Nullable
    @Column(nullable = true)
    private Boolean question4;  // Nullable
    @Column(nullable = true)
    private Boolean question5;  // Nullable
    @Column(nullable = true)
    private Boolean question6;  // Nullable
    @Column(nullable = true)
    private Boolean question7;  // Nullable
    @Column(nullable = true)
    private Boolean question8;  // Nullable
    @Column(nullable = true)
    private Boolean question9;  // Nullable
    @Column(nullable = true)
    private Boolean question10; // Nullable
    @Column(nullable = true)
    private Boolean question11; // Nullable
    @Column(nullable = true)
    private Boolean question12; // Nullable
    @Column(nullable = true)
    private Boolean question13; // Nullable
    @Column(nullable = true)
    private Boolean question14; // Nullable
    @Column(nullable = true)
    private Boolean question15; // Nullable
    @Column(nullable = true)
    private Boolean question16; // Nullable
    @Column(nullable = true)
    private Boolean question17; // Nullable
    @Column(nullable = true)
    private Boolean question18; // Nullable
    @Column(nullable = true)
    private Boolean question19; // Nullable
    @Column(nullable = true)
    private Boolean question20; // Nullable
    @Column(nullable = true)
    private Boolean question21; // Nullable
    @Column(nullable = true)
    private Boolean question22; // Nullable
    @Column(nullable = true)

    private Boolean question23; // Nullable
    @Column(nullable = true)
    private Boolean question24; // Nullable

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToOne(mappedBy = "client", cascade = CascadeType.ALL, orphanRemoval = true)
    private Intervention intervention;

    // Getters and setters
    public Intervention getIntervention() {
        return intervention;
    }

    public void setIntervention(Intervention intervention) {
        this.intervention = intervention;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getInsuranceNumber() {
        return insuranceNumber;
    }

    public void setInsuranceNumber(String insuranceNumber) {
        this.insuranceNumber = insuranceNumber;
    }

    public String getDateOfBirth() {
        return dateOfBirth;
    }

    public void setDateOfBirth(String dateOfBirth) {
        this.dateOfBirth = dateOfBirth;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Boolean getQuestion1() {
        return question1;
    }

    public void setQuestion1(Boolean question1) {
        this.question1 = question1;
    }

    public Boolean getQuestion2() {
        return question2;
    }

    public void setQuestion2(Boolean question2) {
        this.question2 = question2;
    }

    public Boolean getQuestion3() {
        return question3;
    }

    public void setQuestion3(Boolean question3) {
        this.question3 = question3;
    }

    public Boolean getQuestion4() {
        return question4;
    }

    public void setQuestion4(Boolean question4) {
        this.question4 = question4;
    }

    public Boolean getQuestion5() {
        return question5;
    }

    public void setQuestion5(Boolean question5) {
        this.question5 = question5;
    }

    public Boolean getQuestion6() {
        return question6;
    }

    public void setQuestion6(Boolean question6) {
        this.question6 = question6;
    }

    public Boolean getQuestion7() {
        return question7;
    }

    public void setQuestion7(Boolean question7) {
        this.question7 = question7;
    }

    public Boolean getQuestion8() {
        return question8;
    }

    public void setQuestion8(Boolean question8) {
        this.question8 = question8;
    }

    public Boolean getQuestion9() {
        return question9;
    }

    public void setQuestion9(Boolean question9) {
        this.question9 = question9;
    }

    public Boolean getQuestion10() {
        return question10;
    }

    public void setQuestion10(Boolean question10) {
        this.question10 = question10;
    }

    public Boolean getQuestion11() {
        return question11;
    }

    public void setQuestion11(Boolean question11) {
        this.question11 = question11;
    }

    public Boolean getQuestion12() {
        return question12;
    }

    public void setQuestion12(Boolean question12) {
        this.question12 = question12;
    }

    public Boolean getQuestion13() {
        return question13;
    }

    public void setQuestion13(Boolean question13) {
        this.question13 = question13;
    }

    public Boolean getQuestion14() {
        return question14;
    }

    public void setQuestion14(Boolean question14) {
        this.question14 = question14;
    }

    public Boolean getQuestion15() {
        return question15;
    }

    public void setQuestion15(Boolean question15) {
        this.question15 = question15;
    }

    public Boolean getQuestion16() {
        return question16;
    }

    public void setQuestion16(Boolean question16) {
        this.question16 = question16;
    }

    public Boolean getQuestion17() {
        return question17;
    }

    public void setQuestion17(Boolean question17) {
        this.question17 = question17;
    }

    public Boolean getQuestion18() {
        return question18;
    }

    public void setQuestion18(Boolean question18) {
        this.question18 = question18;
    }

    public Boolean getQuestion19() {
        return question19;
    }

    public void setQuestion19(Boolean question19) {
        this.question19 = question19;
    }

    public Boolean getQuestion20() {
        return question20;
    }

    public void setQuestion20(Boolean question20) {
        this.question20 = question20;
    }

    public Boolean getQuestion21() {
        return question21;
    }

    public void setQuestion21(Boolean question21) {
        this.question21 = question21;
    }

    public Boolean getQuestion22() {
        return question22;
    }

    public void setQuestion22(Boolean question22) {
        this.question22 = question22;
    }

    public Boolean getQuestion23() {
        return question23;
    }

    public void setQuestion23(Boolean question23) {
        this.question23 = question23;
    }

    public Boolean getQuestion24() {
        return question24;
    }

    public void setQuestion24(Boolean question24) {
        this.question24 = question24;
    }

    public Long getNumMat() {
        return numMat;
    }

    public void setNumMat(Long numMat) {
        this.numMat = numMat;
    }

    public String getComment() {
        return comment;
    }

    public void setComment(String comment) {
        this.comment = comment;
    }

}
