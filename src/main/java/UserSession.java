public class UserSession {
    private final UserCredentials credentials;
    private final UserProfile profile;

    public UserSession(UserCredentials credentials, UserProfile profile) {
        this.credentials = credentials;
        this.profile = profile;
    }

    public String getUsername() {
        return credentials.getUsername();
    }

    public UserProfile getProfile() {
        return profile;
    }
}
