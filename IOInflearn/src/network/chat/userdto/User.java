package network.chat.userdto;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class User implements Serializable {

    @Serial
    private static final long serialVersionUID = -3193706148923869299L;
    private String name;

    private UUID userId;

    public User(){

    }
    public User(String name){
        this.userId=UUID.randomUUID();
        this.name=name;
    }

    public User(String name, UUID userId){
        this.userId=userId;
        this.name=name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public UUID getUserId() {
        return userId;
    }

    public void setUserId(UUID userId) {
        this.userId = userId;
    }

    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", userId=" + userId +
                '}';
    }
}
