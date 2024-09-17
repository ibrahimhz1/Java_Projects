package base0;

public class Admin {
    int adminID;
    String username;
    String email;
    String adminName;
    String password;
    String role = "admin";

    public void generateAdminID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.adminID = rand;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public Admin(String username, String email, String adminName, String password) {
        this.username = username;
        this.email = email;
        this.adminName = adminName;
        this.password = password;
        this.generateAdminID();
    }

    public void getDetail() {
        System.out.println(this.adminID);
        System.out.println(this.username);
        System.out.println(this.email);
        System.out.println(this.password);
        System.out.println(this.role);
    }
}