package com.conneckto.myschool.lostandfound.model;

import java.util.Date;

/**
 * Created by suryaveer on 2016-05-23.
 */
public class Claims {
    private long claimId;

    private long itemId;

    private String claimerType;

    private long claimerId;

    private Date claimSubmission;

    private boolean claimApprovalFlag;

    private String claimApprovedBy;

    public long getClaimId() {
        return claimId;
    }

    public void setClaimId( long claimId) {
        this.claimId = claimId;
    }

    public long getItemId() {
        return itemId;
    }

    public void setItemId( long itemId) {
        this.itemId = itemId;
    }

    public String getClaimerType() {
        return claimerType;
    }

    public void setClaimerType( String claimerType) {
        this.claimerType = claimerType;
    }

    public long getClaimerId() {
        return claimerId;
    }

    public void setClaimerId( long claimerId) {
        this.claimerId = claimerId;
    }

    public Date getClaimSubmission() {
        return claimSubmission;
    }

    public void setClaimSubmission(Date claimSubmission) {
        this.claimSubmission = claimSubmission;
    }

    public boolean isClaimApprovalFlag() {
        return claimApprovalFlag;
    }

    public void setClaimApprovalFlag(boolean claimApprovalFlag) {
        this.claimApprovalFlag = claimApprovalFlag;
    }

    public String getClaimApprovedBy() {
        return claimApprovedBy;
    }

    public void setClaimApprovedBy( String claimApprovedBy) {
        this.claimApprovedBy = claimApprovedBy;
    }

    @Override
    public String toString() {
        return "Claims{" +
                "itemId=" + itemId +
                ", claimerId=" + claimerId +
                ", claimSubmission=" + claimSubmission +
                '}';
    }
}
