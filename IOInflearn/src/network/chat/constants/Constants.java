package network.chat.constants;

import java.util.Arrays;
import java.util.List;

public class Constants{
    public static final int port = 12345;

    public static final String localhost = "localhost";

    public static final List<Integer> selectionList= Arrays.asList(1,2,3,4,5,6);

    public static final int SUCESSFUL = 200;

    public static final int FAIL = 500;

    public static final String failedMessageChangeName= "Could not change a name";

    public static final String exitResponseMessage= "Exit request received. Server will be closed.";

    public static final String userNotFoundErrorMessage= "There is no user in the system yet. Please try again after registration.";

}
