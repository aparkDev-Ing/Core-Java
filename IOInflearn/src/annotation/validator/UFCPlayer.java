package annotation.validator;

public class UFCPlayer extends Player {

    @NotEmpty(message = "Field Must be non-empty value")
    public String name;
    @Range(totalMatch =  5, message ="Total match needs to exceed at least {{totalMatch}} matches")
    public int totalMatch;
    public UFCPlayer(String name, int totalMatch){
//        super(name,totalMatch);
        this.name=name;
        this.totalMatch=totalMatch;
    }

    @Override
    public String toString() {
        return "UFCPlayer{" +
                "name='" + name + '\'' +
                ", totalMatch=" + totalMatch +
                '}';
    }
}
