package annotation.validator;

import java.lang.annotation.Inherited;
import java.lang.annotation.Retention;

public class Player {

    public Player(){

    }

    @NotEmpty(message = "Field Must be non-empty value")
    public String name;
    @Range(totalMatch =  5, message ="Total match needs to exceed at least 5 matches")
    public int totalMatch;

    public Player(String name, int totalMatch) {
        this.name = name;
        this.totalMatch = totalMatch;
    }

    @Override
    public String toString() {
        return "Player{" +
                "name='" + name + '\'' +
                ", totalMatch=" + totalMatch +
                '}';
    }
}
