package kz.enu.epam.azimkhan.tour.entity;

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
	 * role name
	 */
	private Role role;

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

	public Role getRole() {
		return role;
	}

	public void setRole(Role role) {
		this.role = role;
	}

	@Override
	public boolean equals(Object o) {
		if (this == o) return true;
		if (o == null || getClass() != o.getClass()) return false;

		User user = (User) o;

		if (!password.equals(user.password)) return false;
		if (!role.equals(user.role)) return false;
		if (!username.equals(user.username)) return false;

		return true;
	}

	@Override
	public int hashCode() {
		int result = username.hashCode();
		result = 31 * result + password.hashCode();
		result = 31 * result + role.hashCode();
		return result;
	}

	@Override
	public String toString(){
		return String.format("User[id: %d, username: %s, role: %s]", getId(), getUsername(), getRole().getRolename());
	}
}
