package reflection.data;

public class Chef {

    @Override
    public String toString() {
        return "Chef{" +
                "chefName='" + chefName + '\'' +
                ", chefAge=" + chefAge +
                '}';
    }

    public String getChefName() {
        return chefName;
    }

    public void setChefName(String chefName) {
        this.chefName = chefName;
    }

    public Integer getChefAge() {
        return chefAge;
    }

    public void setChefAge(Integer chefAge) {
        this.chefAge = chefAge;
    }

    private String chefName;
    private Integer chefAge;

    public Chef(String chefName, Integer chefAge) {
        this.chefName = chefName;
        this.chefAge = chefAge;
    }


}
