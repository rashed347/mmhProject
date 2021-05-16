package com.mmh.models.chiefComplaints;

import java.util.List;

public class ChiefComplaints {
    public List<BodyPart> bodyParts;

    public ChiefComplaints(List<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }

    public List<BodyPart> getBodyParts() {
        return bodyParts;
    }

    public void setBodyParts(List<BodyPart> bodyParts) {
        this.bodyParts = bodyParts;
    }
}

