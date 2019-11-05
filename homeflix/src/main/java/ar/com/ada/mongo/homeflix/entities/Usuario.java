package ar.com.ada.mongo.homeflix.entities;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * Usuario
 */
@Document(collection = "Usuarios")
public class Usuario {

    @Id
    private ObjectId _id;
    private String fullname;
    private String username;
    private String password;
    private String email;

    public Usuario (String username, String password, String email){
        this.username = username;
        this.password = password;
        this.email = email;
    }

    public Usuario(){

    }

    public Usuario (String password){
        this.password = password;
    }

    public ObjectId get_id() {
        return _id;
    }

    public void set_id(ObjectId _id) {
        this._id = _id;
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getFullname() {
        return fullname;
    }

    public void setFullname(String fullname) {
        this.fullname = fullname;
    }

}