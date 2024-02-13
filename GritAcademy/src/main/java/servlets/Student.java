package servlets;

public class Student {
    private int id;
    private String firstName;
    private String lastName;
    private String ort;

    public Student(int id, String firstName, String lastName, String ort, String interests) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.ort = ort;
        this.interests = interests;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getort() {
        return ort;
    }

    public void setort(String ort) {
        this.ort = ort;
    }

    public String getInterests() {
        return interests;
    }

    public void setInterests(String interests) {
        this.interests = interests;
    }

    private String interests;

}