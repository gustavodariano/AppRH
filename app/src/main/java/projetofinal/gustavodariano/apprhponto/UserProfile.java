package projetofinal.gustavodariano.apprhponto;

public class UserProfile {
    public String userId;
    public String userAge;
    public String userEmail;
    public String userName;

    public UserProfile(String userId, String userAge, String userEmail, String userName) {
        this.userId = userId;
        this.userAge = userAge;
        this.userEmail = userEmail;
        this.userName = userName;
    }

    public UserProfile() {

    }

    public UserProfile(String age, String email, String name) {

    }


    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getUserAge() {
        return userAge;
    }

    public void setUserAge(String userAge) {
        this.userAge = userAge;
    }

    public String getUserEmail() {
        return userEmail;
    }

    public void setUserEmail(String userEmail) {
        this.userEmail = userEmail;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }
}
