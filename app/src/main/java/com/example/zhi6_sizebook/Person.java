package com.example.zhi6_sizebook;

import java.util.Calendar;

/**
 * This is the class for person object.
 * Created by Zhi Li on 2017/1/30.7
 */
public class Person {
    private String personName;
    private Calendar dateOfRecord;
    private double neckCircumference;
    private double bustCircumference;
    private double chestCircumference;
    private double waistCircumference;
    private double hipSizeCircumference;
    private double inseamLength;
    private String comment;

    /**
     * Creates a new Person object.
     */
    public Person() {}

    /**
     * Gets person name.
     *
     * @return the person name
     */
    public String getPersonName() {
        return personName;
    }

    /**
     * Sets person name.
     *
     * @param personName the person name
     */
    public void setPersonName(String personName) {
        this.personName = personName;
    }

    /**
     * Gets date of record.
     *
     * @return the date of record
     */
    public Calendar getDateOfRecord() {
        return dateOfRecord;
    }

    /**
     * Sets date of record.
     *
     * @param dateOfRecord the date of record
     */
    public void setDateOfRecord(Calendar dateOfRecord) {
        this.dateOfRecord = dateOfRecord;
    }

    /**
     * Gets neck circumference.
     *
     * @return the neck circumference
     */
    public double getNeckCircumference() {
        return neckCircumference;
    }

    /**
     * Sets neck circumference.
     *
     * @param neckCircumference the neck circumference
     */
    public void setNeckCircumference(double neckCircumference) {
        this.neckCircumference = neckCircumference;
    }

    /**
     * Gets bust circumference.
     *
     * @return the bust circumference
     */
    public double getBustCircumference() {
        return bustCircumference;
    }

    /**
     * Sets bust circumference.
     *
     * @param bustCircumference the bust circumference
     */
    public void setBustCircumference(double bustCircumference) {
        this.bustCircumference = bustCircumference;
    }

    /**
     * Gets chest circumference.
     *
     * @return the chest circumference
     */
    public double getChestCircumference() {
        return chestCircumference;
    }

    /**
     * Sets chest circumference.
     *
     * @param chestCircumference the chest circumference
     */
    public void setChestCircumference(double chestCircumference) {
        this.chestCircumference = chestCircumference;
    }

    /**
     * Gets waist circumference.
     *
     * @return the waist circumference
     */
    public double getWaistCircumference() {
        return waistCircumference;
    }

    /**
     * Sets waist circumference.
     *
     * @param waistCircumference the waist circumference
     */
    public void setWaistCircumference(double waistCircumference) {
        this.waistCircumference = waistCircumference;
    }

    /**
     * Gets hip size circumference.
     *
     * @return the hip size circumference
     */
    public double getHipSizeCircumference() {
        return hipSizeCircumference;
    }

    /**
     * Sets hip size circumference.
     *
     * @param hipSizeCircumference the hip size circumference
     */
    public void setHipSizeCircumference(double hipSizeCircumference) {
        this.hipSizeCircumference = hipSizeCircumference;
    }

    /**
     * Gets inseam length.
     *
     * @return the inseam length
     */
    public double getInseamLength() {
        return inseamLength;
    }

    /**
     * Sets inseam length.
     *
     * @param inseamLength the inseam length
     */
    public void setInseamLength(double inseamLength) {
        this.inseamLength = inseamLength;
    }

    /**
     * Gets comment.
     *
     * @return the comment
     */
    public String getComment() {
        return comment;
    }

    /**
     * Sets comment.
     *
     * @param comment the comment
     */
    public void setComment(String comment) {
        this.comment = comment;
    }

    /**
     * Taken from http://stackoverflow.com/questions/4530531/java-return-object-value
     * 2017-02-02 20:53
     * Overrides the original toString method
     * @return name+bust+chest+waist+inseam as one string
     */
    @Override
    public String toString(){
        return "Name: " + this.personName + "\n"
                + "Bust Circumference is: " + this.bustCircumference + "\n"
                + "Chest Circumference is: " + this.chestCircumference + "\n"
                + "Waist Circumference is: " + this.waistCircumference + "\n"
                + "Inseam Length is: " + this.inseamLength;
    }
}
