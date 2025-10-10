package annotation.validator;

import annotation.validator.utility.ValidatorUtil;
import logging.MyLogger;

public class ValidatorMain {

    public static void main(String[] args) {
        SoccerPlayer soccerPlayer = new SoccerPlayer("Aaron",10 );
        UFCPlayer ufcPlayer = new UFCPlayer("Jon Jones",5 );

        try{
            ValidatorUtil.validate(soccerPlayer);

        }catch(Exception e){
            MyLogger.log(e);
        }

        try{
            ValidatorUtil.validate(ufcPlayer);
        }catch(Exception e){
            MyLogger.log(e);
        }


    }

    public static<T extends Player> void validate(T player) throws Exception{

        System.out.println("Player: "+ player);

        if(player.name == null || player.name.isEmpty()){
            throw new RuntimeException("Player name cannot be Empty!");
        }

        if(player.totalMatch <0 || player.totalMatch< 5){
            throw new RuntimeException("Player name cannot be Empty!");
        }
    }

    public static<T extends Player> void validateUfcPlayer(T player) throws Exception{

        System.out.println("Player: "+ player);

        if(player.name == null || player.name.isEmpty()){
            throw new RuntimeException("Player name cannot be Empty!");
        }

        if(player.totalMatch <0 || player.totalMatch< 5){
            throw new RuntimeException("Player name cannot be Empty!");
        }
    }
}
