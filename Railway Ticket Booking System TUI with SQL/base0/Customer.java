package base0;

public class Customer {
    String customerID;
    String username;
    String email;
    String password;
    String address;
    String contactNum;

    private void generateUserID() {
        int min = 10000;
        int max = 99999;
        int range = max - min + 1;
        int rand = (int) (Math.random() * range) + min;
        this.customerID = String.valueOf(rand);
    }

    public Customer(String username, String email, String password, String address, String contactNum) {
        this.username = username;
        this.email = email;
        this.password = password;
        this.address = address;
        this.contactNum = contactNum;
        this.generateUserID();
    }

    public String getCustomerID() {
        return this.customerID;
    }

    public String getUsername() {
        return this.username;
    }

    public String getEmail() {
        return this.email;
    }

    public String getPassword() {
        return this.password;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public void setContactNum(String contactNum) {
        this.contactNum = contactNum;
    }

    public void getDetail() {
        System.out.println(this.customerID);
        System.out.println(this.username);
        System.out.println(this.email);
        System.out.println(this.password);
        System.out.println(this.address);
        System.out.println(this.contactNum);
    }
}