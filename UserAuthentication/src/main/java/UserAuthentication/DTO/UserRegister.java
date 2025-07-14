package UserAuthentication.DTO;

public class UserRegister {
    private String name;
    private String email;
    private String password;
    private boolean active;
    private String phone;
    private String address;


    public UserRegister(){}

    public UserRegister(String name, String email, String password, boolean active, String phone, String address) {
        this.name = name;
        this.email = email;
        this.password = password;
        this.active = active;
        this.phone = phone;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
