package users;


import java.time.LocalDate;

public class UserPremium extends User {

    private boolean premium;
    private LocalDate premiumExpiryDate;
    private String videoQuality;

    public UserPremium(String userId, String login, String password, String email, String name, boolean premium, LocalDate premiumExpiryDate) {
        // wywolanie kostruktoora klasy nadrzednej user bez bledow
        super(userId, login, password, email, name, premium);

        this.premiumExpiryDate = premiumExpiryDate;
        this.videoQuality = "HD";
    }

    public boolean isPremium() {
        return premium;
    }

    public void setPremium(boolean premium) {
        this.premium = premium;
    }

    public LocalDate getPremiumExpiryDate() {
        return premiumExpiryDate;
    }

    public void setPremiumExpiryDate() {
        this.premiumExpiryDate = LocalDate.now().plusDays(30);
    }

    public String getVideoQuality() {
        return videoQuality;
    }

    public void setVideoQuality(String videoQuality) {
        this.videoQuality = videoQuality;
    }


    @Override
    public String toString() {
        return "UserPremium{" +
                "username='" + getName() + '\'' +
                ", premium=" + premium +
                ", premiumExpiryDate='" + premiumExpiryDate + '\'' +
                ", videoQuality='" + videoQuality + '\'' +
                '}';
    }
    public void upgradeToPremium() {
        this.premium = true;
        this.videoQuality = "4K";
        setPremiumExpiryDate();
        System.out.println("Konto zostało uaktualnione do Premium.");
    }

    public void downgradeFromPremium() {
        this.premium = false;
        this.videoQuality = "HD";
        this.premiumExpiryDate = null;
        System.out.println("Konto zostało zdegradowane z Premium.");
    }
}
