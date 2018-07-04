package apps.onkar.android.basiclogin;

public class Person {
    private String name;
    private String email;
    private String password;
    private String mobileno;

    public Person(){

    }

    public Person(String name, String email, String password, String mobileno){
        this.name = name;
        this.email = email;
        this.password = password;
        this.mobileno = mobileno;
    }

    public String getMobileno() {
        return mobileno;
    }

    public void setMobileno(String mobileno) {
        this.mobileno = mobileno;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {

        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getName() {

        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
