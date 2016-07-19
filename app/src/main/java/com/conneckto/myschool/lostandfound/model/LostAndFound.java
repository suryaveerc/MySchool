package com.conneckto.myschool.lostandfound.model;

import java.util.Date;

/**
 * Created by suryaveer on 2016-05-23.
 */
public class LostAndFound {

    long itemId;
    String itemType;
    String description;
    String activityType;
    Date reportedDate;
    String reportedPlace;
    String reporterType;
    long reporterId;
    String status;
    long claimId;
    String name;
    String section;
    long studentClass;

    public long getItemId() {
        return itemId;
    }

    public void setItemId(long itemId) {
        this.itemId = itemId;
    }

    public String getItemType() {
        return itemType;
    }

    public void setItemType(String itemType) {
        this.itemType = itemType;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getActivityType() {
        return activityType;
    }

    public void setActivityType(String activityType) {
        this.activityType = activityType;
    }

    public Date getReportedDate() {
        return reportedDate;
    }

    public void setReportedDate(Date reportedDate) {
        this.reportedDate = reportedDate;
    }

    public String getReportedPlace() {
        return reportedPlace;
    }

    public void setReportedPlace(String reportedPlace) {
        this.reportedPlace = reportedPlace;
    }

    public String getReporterType() {
        return reporterType;
    }

    public void setReporterType(String reporterType) {
        this.reporterType = reporterType;
    }

    public long getReporterId() {
        return reporterId;
    }

    public void setReporterId(long reporterId) {
        this.reporterId = reporterId;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId(long claimId) {
        this.claimId = claimId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSection() {
        return section;
    }

    public void setSection(String section) {
        this.section = section;
    }

    public long getStudentClass() {
        return studentClass;
    }

    public void setStudentClass(long studentClass) {
        this.studentClass = studentClass;
    }

    @Override
    public String toString() {
        return "LostAndFound{" +
                "itemId=" + itemId +
                ", itemType='" + itemType + '\'' +
                ", activityType='" + activityType + '\'' +
                ", reportedDate=" + reportedDate +
                ", reportedPlace='" + reportedPlace + '\'' +
                ", reporterId=" + reporterId +
                '}';
    }
}
