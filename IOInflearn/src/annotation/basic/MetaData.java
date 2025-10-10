package annotation.basic;


@AnnoMeta(value ="Hello")
public class MetaData {
    public static void main(String[] args) throws Exception {


        System.out.println("Class AnnoMeta value: "+ MetaData.class.getAnnotation(AnnoMeta.class).value());
        System.out.println("Method AnnoMeta value: "+ MetaData.class.getDeclaredMethod("call").getAnnotation(AnnoMeta.class).value());

    }

    @AnnoMeta(value = "Hi")
    public void call(){

    }
}
