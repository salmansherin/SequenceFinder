## Introduction
This application tokenize Java source code files and compile a list of longest common tokenizer sequences.

## Installation & configuration
1. [Download this application](https://github.com/salmansherin29/Task-2/archive/master.zip).
2. Extract the content of the downloaded zip file into a directory.
3. Open Terminal or Command prompt in windows.
4. CD to the application directory like `cd TokenizingCode/src`

## Running the code
1. Run the following command to compile the application
2. `javac tokenizingcode/TokenizingCode.java`
3. After compiling, You can run the application with the follwing command
4. `java tokenizingcode.TokenizingCode file1.java file2.java file3.java`
5. You should provide absolute file URLs like
6. `java tokenizingcode.TokenizingCode /Users/Demo/app/file1.java /Users/Demo/app/file2.java`
7. Or provide relative URLs like
8. `java tokenizingcode.TokenizingCode ./app/file.java ./app/file2.java`

## Results
After running the application, it takes all the provided arguments and tokenize it. Then it compares the tokanized code and match the longest sequence and compile a CSV file with token counts, score and the source code. The CSV file will be generated in the project main directory, for example, 'TokenizingCode' in this case.

##Testing
This application is already run on some of the existing java files i.e. AbstractJavaSource.java and WebDriverBackedEmbeddedBrowser.java, which are already included in this repository. The resultant CSV file can be seen [here](https://github.com/salmansherin29/Task-2/tree/master/TokenizingCode) with the name test.csv.
