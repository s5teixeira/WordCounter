import javax.swing.*;
import java.io.*;
import java.util.*;

public class WordCounter {
    public static void main(String[] args) {
        //choose file for input
        JFileChooser fileChooser = new JFileChooser();
        fileChooser.setCurrentDirectory(new File(System.getProperty("user.dir")));
        int result = fileChooser.showOpenDialog(null);
        //if user chose file
        if (result == JFileChooser.APPROVE_OPTION) {
            //get the file
            File selectedFile = fileChooser.getSelectedFile();

            //create hashmap for storing words and counts
            HashMap<String, Integer> words = new HashMap<>();

            //try block in case file doesn't exist
            try {
                //try to open file
                Scanner inFile = new Scanner(selectedFile);
                //read file line by line
                while (inFile.hasNext()) {
                    //read line
                    String line = inFile.nextLine().trim();
                    //remove punctuation
                    line= line.replaceAll("\\p{Punct}","");
                    //split byu space
                    String[] s = line.split(" ");
                    //add words to hashmap
                    for(String word:s) {
                        //do not take empty spaces
                        if(word.trim().length()==0) continue;

                        if(words.containsKey(word)) {
                            //if exists, increment count
                            words.put(word,words.get(word)+1);
                        } else {
                            //if doesn't exist, add with count 1
                            words.put(word,1);
                        }
                    }
                }
                //close file
                inFile.close();
            } catch (FileNotFoundException e) {
                System.out.println("File "+selectedFile.getName()+" not found !");
                return;
            }
            //create list from hashmap
            List<Map.Entry<String, Integer>> list = new ArrayList<>(words.entrySet());
            //sort list by word frequency in descending order
            Collections.sort(list, (i1, i2) -> i2.getValue().compareTo(i1.getValue()));
            //display top 20 (or fewer if all doesn't exceed 20) word and counts
            for(int i=0;i<Math.min(20,list.size());i++) {
                //display
                System.out.printf("%s - %d\n",list.get(i).getKey(), list.get(i).getValue());
            }
        }

    }
}
