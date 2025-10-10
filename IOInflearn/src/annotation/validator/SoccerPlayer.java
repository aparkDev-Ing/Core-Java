package annotation.validator;

public class SoccerPlayer extends Player{

    @NotEmpty(message = "Field Must be non-empty value")
    public String name;
    @Range(totalMatch = 10, message ="Total match needs to exceed at least {{totalMatch}} matches")
    public int totalMatch;
    public SoccerPlayer(String name, int totalMatch){
//        super(name,totalMatch);
        this.name=name;
        this.totalMatch=totalMatch;
    }

    @Override
    public String toString() {
        return "SoccerPlayer{" +
                "name='" + name + '\'' +
                ", totalMatch=" + totalMatch +
                '}';
    }
}
