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
    public static int checkPlagiarismWrapper(int[][] DP) {
        int plagiarismCounter = 0;
        for (int i = 1; i < DP.length; i++) {
            for (int j = 1; j < DP[0].length; j++) {
                if (DP[i - 1][j - 1] > 2 && (DP[i - 1][j - 1] > DP[i][j])) {
                    plagiarismCounter += DP[i - 1][j - 1];
                }
            }
        }
        return plagiarismCounter;
    }
    public static int checkPlagiarism(String[] inputArrayFile1, String[] inputArrayFile2, int inputArrayFile1Len, int inputArrayFile2Len) {
        int response = 0;
        int DP[][] = new int[inputArrayFile1Len + 1][inputArrayFile2Len + 1];

        for (int i = 0; i <= inputArrayFile1Len; i++) {
            for (int j = 0; j <= inputArrayFile2Len; j++) {
                if (i == 0 || j == 0) {
                    DP[i][j] = 0;
                } else if (inputArrayFile1[i - 1].equals(inputArrayFile2[j - 1])) {
                    DP[i][j] = DP[i - 1][j - 1] + 1;
                    response = Math.max(response, DP[i][j]);
                } else {
                    DP[i][j] = 0;
                }
            }
        }

        return checkPlagiarismWrapper(DP);
    }

    public static int filePreProcessing(File file1, File file2) throws IOException {

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

            String inputStringFile1 = strFile1.toString()
                    .replaceAll("[^a-zA-Z0-9]", " ")
                    .replaceAll("\\s+", " ")
                    .toLowerCase();
            String[] inputArrayFile1 = inputStringFile1.split(" ");

            String inputStringFile2 = strFile2.toString()
                    .replaceAll("[^a-zA-Z0-9]", " ")
                    .replaceAll("\\s+", " ")
                    .toLowerCase();
            String[] inputArrayFile2 = inputStringFile2.split(" ");

            int resultPlagiarism = checkPlagiarism(inputArrayFile1, inputArrayFile2, inputArrayFile1.length, inputArrayFile2.length);
            float finalResult = ((float) resultPlagiarism * 2 * 100) / (inputArrayFile1.length + inputArrayFile2.length);

            int plagiarism = (finalResult < 26) ? 0 : 1;
            return plagiarism;

        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        } catch (IOException e) {
            throw new RuntimeException(e);
        } finally {
            readFile1.close();
            readFile2.close();
        }
    }

    public static void main(String[] args) throws Exception {
        File file1, file2;

        try {
            if (args.length == 2) {
                file1 = new File(args[0]);
                file2 = new File(args[1]);
            } else {
                file1 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/okay02/1.txt");
                file2 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/okay02/2.txt");

                //file1 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/plagiarism07/1.txt");
                //file2 = new File("/Users/gurpreetsingh/Concordia/Fall2022/Algorithms/Project/sample_data_and_submission/data/plagiarism07/2.txt");
            }

            int response = filePreProcessing(file1, file2);
            System.out.println(response);

        } catch (Exception e) {
            System.out.println("ERR " + e.getMessage());
        }
    }
}
