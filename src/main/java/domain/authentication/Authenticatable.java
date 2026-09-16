package domain.authentication;

public interface Authenticatable {
    boolean authenticate(String password);
}
