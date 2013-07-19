package kz.enu.epam.azimkhan.auth.entity;

/**
 * User
 */
public class User extends Entity {

    /**
     * username
     */
    private String username;

    /**
     * hashed password
     */
    private String password;

    /**
     * Default constructor
     */
    public User(){

    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof User)) return false;

        User user = (User) o;

        if (getId() != user.getId()) return false;
        if (!password.equals(user.password)) return false;
        if (!username.equals(user.username)) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = getId();
        result = 31 * result + username.hashCode();
        result = 31 * result + password.hashCode();
        return result;
    }

    @Override
    public String toString(){
        return "User#"+getId()+"[username="+username+"]";
    }
}
