import java.io.IOException;

public interface IAuthService {
    public UserCredentials authenticate(String username, String password) throws IOException;
    public boolean registerUser(UserCredentials user, UserProfile profile) throws IOException;
    public UserProfile getUserProfile(String username) throws IOException;
    public boolean updateUserProfile(String username, UserProfile profile) throws IOException;
}
