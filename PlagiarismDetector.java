/*
 * Author       - Gurpreet Singh
 * Student Id   - 40205054
 * Course       - Algorithm Design Techniques (COMP 6651)
 * Description:
 *              - This Project checks for the plagiarism between files.
 * Input:
 *              - Takes two files as an input.
 * Output:
 *              - Return 0 for no plagiarism and 1 for plagiarism
 */

import java.io.*;

public class PlagiarismDetector {

    public static int filePreProcessing (File file1, File file2) throws IOException {

        BufferedReader readFile1 = null;
        BufferedReader readFile2 = null;
        try {
            readFile1 = new BufferedReader(new FileReader(file1));
            readFile2 = new BufferedReader(new FileReader(file2));

            StringBuilder strFile1 = new StringBuilder();
            StringBuilder strFile2 = new StringBuilder();
            String temp;

            while ((temp = readFile1.readLine()) != null) {
                strFile1.append(temp);
            }

            while ((temp = readFile2.readLine()) != null) {
                strFile2.append(temp);
            }

            String inputStringFile1 = strFile1.toString().toLowerCase().replaceAll("[^a-z0-9]", "");
            String inputStringFile2 = strFile2.toString().toLowerCase().replaceAll("[^a-z0-9]", "");

            System.out.println(inputStringFile1);
            return 0;
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            readFile1.close();
            readFile2.close();
        }
    }

    public static void main (String[] args) throws Exception{
        File file1, file2;

        try {
            if (args.length == 2) {
                file1 = new File(args[0]);
                file2 = new File(args[1]);
            } else {
                file1 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/okay01/1.txt");
                file2 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/okay01/2.txt");
            }

            int response = filePreProcessing(file1, file2);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERR " + e.getMessage());
        }
    }
}
