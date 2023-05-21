package Model;


/**
 * The Client class represents a client object with properties such as id, name, address, email, and age.
 */
public class Client {
    private int id;
    private String name;
    private String address;
    private String email;
    private int age;

    /**
     * Constructs an empty Client object.
     */
    public Client() {
    }

    /**
     * Constructs a Client object with the specified id.
     *
     * @param id the id of the client
     */
    public Client(int id) {
        this.id = id;
    }

    /**
     * Constructs a Client object with the specified name, address, email, and age.
     *
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email of the client
     * @param age     the age of the client
     */
    public Client(String name, String address, String email, int age) {
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }


    /**
     * Constructs a Client object with the specified id, name, address, email, and age.
     *
     * @param id      the id of the client
     * @param name    the name of the client
     * @param address the address of the client
     * @param email   the email of the client
     * @param age     the age of the client
     */
    public Client(int id, String name, String address, String email, int age) {
        this.id = id;
        this.name = name;
        this.address = address;
        this.email = email;
        this.age = age;
    }

    /**
     * @param age
     */
    public void setAge(int age) {
        this.age = age;
    }

    /**
     * Returns the id of the client.
     *
     * @return the id of the client
     */
    public int getId() {
        return id;
    }

    /**
     * Sets the id of the client.
     *
     * @param idClient the id of the client
     */
    public void setId(int idClient) {
        this.id = idClient;
    }

    /**
     * Returns the name of the client.
     *
     * @return the name of the client
     */
    public String getName() {
        return name;
    }

    /**
     * Sets the name of the client.
     *
     * @param name the name of the client
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * Returns the address of the client.
     *
     * @return the address of the client
     */
    public String getAddress() {
        return address;
    }

    /**
     * Sets the address of the client.
     *
     * @param address the address of the client
     */
    public void setAddress(String address) {
        this.address = address;
    }

    /**
     * Returns the email of the client.
     *
     * @return the email of the client
     */
    public String getEmail() {
        return email;
    }

    /**
     * Sets the email of the client.
     *
     * @param email the email of the client
     */
    public void setEmail(String email) {
        this.email = email;
    }

    /**
     * Returns the age of the client.
     *
     * @return the age of the client
     */
    public int getAge() {
        return age;
    }

    /**
     * @return
     */
    @Override
    public String toString() {
        return "Client{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", address='" + address + '\'' +
                ", email='" + email + '\'' +
                ", age=" + age +
                '}';
    }
}
