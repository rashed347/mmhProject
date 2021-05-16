package com.mmh.models.chiefComplaints;

import java.util.List;

public class BodyPart {
    public String bodyPartId;
    public List<Section> sections;

    public BodyPart(String bodyPartId, List<Section> sections) {
        this.bodyPartId = bodyPartId;
        this.sections = sections;
    }

    public String getBodyPartId() {
        return bodyPartId;
    }

    public void setBodyPartId(String bodyPartId) {
        this.bodyPartId = bodyPartId;
    }

    public List<Section> getSections() {
        return sections;
    }

    public void setSections(List<Section> sections) {
        this.sections = sections;
    }
}
