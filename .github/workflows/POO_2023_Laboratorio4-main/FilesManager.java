import java.io.*;
import java.util.ArrayList;

public interface FilesManager {

    public static ArrayList<ArrayList<String>> readFilesCSV(String path) {
        ArrayList<ArrayList<String>> newT = new ArrayList<ArrayList<String>>();
        
        try (BufferedReader reader = new BufferedReader(new FileReader(path))) {
            String line;
            while ((line = reader.readLine()) != null) {
                String[] splitWords = line.split(",");
                ArrayList<String> list = new ArrayList<String>();
                for (String word : splitWords) {
                    list.add(word.trim());
                }
                newT.add(list);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        
        return newT;
    }

    public static void writeFilesCSV(String path, ArrayList<String> newT) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(path))) {
            for (String word : newT) {
                writer.write(word);
                writer.write(",");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void appendlineCSV(String path, ArrayList<String> newT){
        try {
            FileWriter fw = new FileWriter(path, true);
            for (String word : newT) {
                fw.write(word);
                fw.write(",");
            }
            fw.write("\n");
            fw.close();
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}