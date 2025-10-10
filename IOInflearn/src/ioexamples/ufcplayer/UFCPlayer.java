package ioexamples.ufcplayer;

import java.io.Serial;
import java.io.Serializable;
import java.util.UUID;

public class UFCPlayer implements Serializable {

    @Serial
    private static final long serialVersionUID = -899202369566678667L;

    public UFCPlayer(){

    }

    public UFCPlayer(String name, String weightClass) {
        this.name = name;
        this.playerId= UUID.randomUUID();
        this.weightClass = weightClass;
    }

    public String name;

    public UUID playerId;

    public String weightClass;

    //public int winStreak;

    @Override
    public String toString() {
        return "UFCPlayer{" +
                "name='" + name + '\'' +
                ", playerId='" + playerId + '\'' +
                ", weightClass='" + weightClass + '\'' +
                '}';
    }
}
