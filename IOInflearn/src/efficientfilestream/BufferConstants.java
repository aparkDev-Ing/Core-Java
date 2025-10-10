package efficientfilestream;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;

public class BufferConstants {

    public static final String FILE_NAME = "IOInflearn/temp/inefficientfile.dat";
    public final static int FILE_SIZE = 1024 * 1024* 10;

    public final static int FILE_SIZE2 = 1024 * 1024* 200;
    public static final int BUFFER_SIZE = 8192; // 8KB

    public static final Charset UTF_8 = StandardCharsets.UTF_8;

}
