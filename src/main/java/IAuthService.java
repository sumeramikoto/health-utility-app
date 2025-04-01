import java.io.IOException;

public interface IAuthService {
    UserCredentials authenticate(String username, String password) throws IOException;
    boolean registerUser(UserCredentials user, UserProfile profile) throws IOException;
    UserProfile getUserProfile(String username) throws IOException;
    boolean updateUserProfile(String username, UserProfile profile) throws IOException;
}
