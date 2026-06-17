package org.peakx.models;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Pet {
    private Long id;
    private Category category;
    private String name;
    private String[] photoUrls;
    private Tag[] tags;
    private PetStatus status;

    public Pet() {}

    public Pet(Long id, Category category, String name, String[] photoUrls, Tag[] tags, PetStatus status) {
        this.id = id;
        this.category = category;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public Long getId() {
        return id;
    }
    public Category getCategory() {
        return category;
    }
    public String getName() {
        return name;
    }
    public String[] getPhotoUrls() {
        return photoUrls;
    }
    public Tag[] getTags() {
        return tags;
    }
    public PetStatus getStatus() {
        return status;
    }

    public void setId(Long id) {
        this.id = id;
    }
    public void setCategory(Category category) {
        this.category = category;
    }
    public void setName(String name) {
        this.name = name;
    }
    public void setPhotoUrls(String[] photoUrls) {
        this.photoUrls = photoUrls;
    }
    public void setTags(Tag[] tags) {
        this.tags = tags;
    }
    public void setStatus(PetStatus status) {
        this.status = status;
    }
}
