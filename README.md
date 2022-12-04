# Tool for Detecting Plagiarism

In order to detect Plagiarism between two input files namely 1.txt and 2.txt that compared the content between each other use the following commands:

a. For compiling the File use the javac command-
=>  javac File.java

b. For running the Java File use the command-
=>  java File

For the location of the Files, the file path is entered by the user on the command line for both files for detecting Plagiarism.

=>  make FILE1=1.txt FILE2=2.txt run
Above is the command that will take the input files and run will execute both the commands (a and b) and displays the result on the command line.

=>  Plagiarism Detection Approach

Files are first taken input using file Reader, which is then processed using the Buffered Read. This takes an object of the file reader. 

For the purpose of comparing the files, the input is converted to string format using the string builder object that converts the input files to string for easy processing. These strings, however, contain a lot of special characters, space and alphabets that are not useful and are removed by string preprocessing using string methods and Regular Expressions such as ReplaceAll, Spilt etc. The final result of the preprocessing gives the string free from unnecessary content. 

Then the string is converted into an Array of strings. The only reason is that the Longest Common SubString Approach used takes an Array of Strings as input. These two strings along with their lengths are passed to the check plagiarism method as input. 

=>  checkPlagiarism :: This method makes the DP table of 2D matrix of strings length as dimensions (2D Array). The table is created using the longest common substring to hold the matching words from the two strings. The default entry in the DP table is 0, and when a word from one string matches a word from the other to be compared, the value in the preceding row and column is increased by one. Instead of comparing the characters in a string of words, comparable words are matched by comparing them to one another. The number of common words in both input strings is counted once the DP array has been populated by checking the presence of those words in both files. This DP table is passed to checkPlagiarismWrapper method for keeping count of the Plagiarism. This method returns the count of Plagiarism.

=>  checkPlagiarismWrapper :: Now iterate over the DP table, the value recorded in the preceding row and column is compared to the value in the presently iterating DP table, and if it exceeds 2, it is added to the plagiarismCounter variable that keeps the track of the count of words frequently used in plagiarism. The overall ratio of plagiarism is calculated as the ratio of common words to the total number of words in two files, and it is used to detect whether or not anything is plagiarised.
This method returns the plagiarismCounter.

Now the result of the checkPlagiarism method is multiplied by two to consider the commonness between both the files. Then Percentage is taken. Meaning, in order to determine whether or not files constitute plagiarism, a ratio of common words to the total number of words in two files is used to calculate the overall plagiarism ratio. 

=> Output :: Now the limit is set to 26, if less than the limit no Plagiarism and displays 0 else Plagiarised and displays 1.

Finally, as the files are processed and results have been displayed open files are no longer required to open so close both files which are done in the Finally block.
