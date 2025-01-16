package users;

public class UserPremium extends User {

    private boolean premium;
    private String premiumExpiryDate;
    private String videoQuality; // Jakość wideo (np. 4K, Full HD, HD)
    private boolean adFree; // Brak reklam

    public UserPremium(String username, String password, String email, Boolean premium, String premiumExpiryDate) {
        super(username, password, email, premium, premiumExpiryDate);
        this.premium = premium;
        this.premiumExpiryDate = premiumExpiryDate;
        this.videoQuality = "HD"; // Domyślna jakość
        this.adFree = false; // Domyślnie reklamy są włączone
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public String getPremiumExpiryDate() {
        return premiumExpiryDate;
    }

    public void setPremiumExpiryDate(String premiumExpiryDate) {
        this.premiumExpiryDate = premiumExpiryDate;
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }

    public boolean isAdFree() {
        return adFree;
    }

    public void setAdFree(boolean adFree) {
        this.adFree = adFree;
    }

    @Override
    public String toString() {
        return "UserPremium{" +
                "username='" + getUsername() + '\'' +
                ", premium=" + premium +
                ", premiumExpiryDate='" + premiumExpiryDate + '\'' +
                ", videoQuality='" + videoQuality + '\'' +
                ", adFree=" + adFree +
                '}';
    }

    public void upgradeToPremium() {
        this.premium = true;
        this.adFree = true;
        this.videoQuality = "4K";
        this.premiumExpiryDate = "YYYY-MM-DD"; // Placeholder, wymaga implementacji logiki dat
        System.out.println("Konto zostało uaktualnione do Premium.");
    }

    public void downgradeFromPremium() {
        this.premium = false;
        this.adFree = false;
        this.videoQuality = "HD";
        this.premiumExpiryDate = null;
        System.out.println("Konto zostało zdegradowane z Premium.");
    }
}
