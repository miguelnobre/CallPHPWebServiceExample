package pt.iscte.dam.callphpwebserviceexample.model;

import java.io.Serializable;

/**
 * Created by Miguel on 17/05/16.
 */
public class User implements Serializable {

//    private Integer userId;
//    private String name;
//    private Integer age;
//    private String email;
//    private String password;
//    private Integer driverRank;
//    private Integer passengerRank;

    private Integer id;
    private String nome;
    private String username;
    private String password;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }
}