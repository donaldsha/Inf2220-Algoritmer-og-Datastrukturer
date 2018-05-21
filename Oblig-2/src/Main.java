import java.io.File;
public class Main {

    public static void main(String[] args) {
        /*
        if (args[0] == null || (args[0] instanceof String) || !(args.length > 0)){
            System.out.println("Feil Input, proev --> java Main <projectName>.txt");
            //System.exit(-1);
        }*/ //parameters check not needed
        String filename = args[0].trim();

        FileReader reader = new FileReader();
        TaskGenerator generator = new TaskGenerator();
        reader.readFile(filename, generator);
        reader.update();
        reader.sort();
        //generator.update();
    }
}



















