package com.example.freelancerflow;

public class ClientModel {

    private String clientId;
    private String name;
    private String email;
    private String companyName;
    private String phoneNumber;
    private String country;
    private String profileImageUrl;
    private long createdAt;

    public ClientModel() {}

    public ClientModel(String clientId, String name, String email,
                       String companyName, String phoneNumber,
                       String country, String profileImageUrl,
                       long createdAt) {
        this.clientId = clientId;
        this.name = name;
        this.email = email;
        this.companyName = companyName;
        this.phoneNumber = phoneNumber;
        this.country = country;
        this.profileImageUrl = profileImageUrl;
        this.createdAt = createdAt;
    }

    public String getClientId() { return clientId; }
    public void setClientId(String clientId) { this.clientId = clientId; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getCompanyName() { return companyName; }
    public void setCompanyName(String companyName) { this.companyName = companyName; }

    public String getPhoneNumber() { return phoneNumber; }
    public void setPhoneNumber(String phoneNumber) { this.phoneNumber = phoneNumber; }

    public String getCountry() { return country; }
    public void setCountry(String country) { this.country = country; }

    public String getProfileImageUrl() { return profileImageUrl; }
    public void setProfileImageUrl(String profileImageUrl) { this.profileImageUrl = profileImageUrl; }

    public long getCreatedAt() { return createdAt; }
    public void setCreatedAt(long createdAt) { this.createdAt = createdAt; }
}
