import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileReader;
import java.io.FileWriter;
//import java.util.Scanner;

public class DictionaryManagement {
    public static Dictionary dic = new Dictionary();

    //private static Scanner sc = new Scanner(System.in);

    /*
    public void insertFromCommandline() {
        System.out.println("Enter number of words: ");
        int num = sc.nextInt();
        sc.nextLine();
        for (int i = 0; i < num; i++) {
            System.out.println("Enter target word: ");
            String target = sc.nextLine();
            System.out.println("Enter explain word: ");
            String explain = sc.nextLine();
            dic.addWord(target, explain);
        }
    }
    */

    public void insertFromFile() throws  Exception {
        String fileName = "dictionaries.txt";
        BufferedReader bReader = new BufferedReader(new FileReader(fileName));
        String line;
        while ((line = bReader.readLine()) != null) {
            String[] data;
            data = line.split("\t");
            String value1 = data[0];
            StringBuilder value2 = new StringBuilder( data[1]);
            while((line = bReader.readLine()) != null){
                if(line.equals("")){
                    break;
                }
                value2.append("\n").append(line);
            }
            dic.addWord(value1, value2.toString());
        }
        bReader.close();
    }

    /*
    public void showAllWords() {
        System.out.println("NO" + "\t" + "|English" + "\t\t\t\t" + "|Vietnamese");
        for (int i = 0; i < dic.count; i++) {
            System.out.println((i + 1) + "\t|" + dic.words[i].getWord_target() + "\t\t\t\t\t|" + dic.words[i].getWord_explain());
        }
    }
    */

    public String dictionaryLookup(String keyword) {
        for (int i = 0; i < dic.count; i++) {
            if (dic.words[i].getWord_target().equals(keyword)) {
                return dic.words[i].getWord_explain();
            }
        }
        return "Word not found !!!";
    }

    public void removeWord(String word) {
        for (int i = 0; i < dic.count; i++) {
            if (dic.words[i].getWord_target().equals(word)) {
                if (dic.count - 1 - i >= 0)
                    System.arraycopy(dic.words, i + 1, dic.words, i, dic.count - 1 - i);
                dic.count--;
            }
        }
    }

    public void modifyWord( String key, String newTargetWord, String newExplainWord) {
        //System.out.println("Enter the word you want to modify: ");
        //String key = sc.nextLine();
        for (int i = 0; i < dic.count; i++) {
            if (dic.words[i].getWord_target().equals(key)) {
                //System.out.println("Enter new explanation: ");
                //String newExplainWord = sc.nextLine();
                dic.words[i].setWord_target(newTargetWord);
                dic.words[i].setWord_explain(newExplainWord);
                return;
            }
        }
    }

    public int[] dictionarySearcher(String key) {
        int[] IndexSearch = new int[2];
        boolean StartCheck = false;
        for (int i = 0; i < dic.count; i++) {
            if (dic.words[i].getWord_target().startsWith(key)) {
               if (IndexSearch[0] == 0 && !StartCheck)
                   IndexSearch[0] = i;
                   StartCheck = true;

            } else if (StartCheck) {
                IndexSearch[1] = i;
                break;
            }
        }
        return IndexSearch;
    }

    public void dictionaryExportToFile(String fileName) throws Exception {
        BufferedWriter bWriter = new BufferedWriter(new FileWriter(fileName, true));
        for (int i = 0; i < dic.count; i++) {
            bWriter.write(dic.words[i].getWord_target() + "\t" + dic.words[i].getWord_explain() + "\n");
        }
        bWriter.close();
    }
}
