This program takes in a list of source code files as an arguments and then proceeds to quickly tokenize the tokens from the source code and report the longest common token subsequences across all the files. The program generates CSV file with the column names: Score (calculated as score = log2(tokens) * log2(count)), Tokens, Count, Source code.
The application uses an existing [tokenizer-parser](https://github.com/kevinjalbert/tokenizing-parser) to parse the java code.
The application is already run/tested on the files from an [existing github repository](https://github.com/crawljax/crawljax) and the results are reported by the application in file 'devs.csv'.
### How to Run the application:
- Clone the github repository
- Run the application via java command line or Netbeans by passing the list of source code files (separated by space) as arguments.

### How the program works:
- The program fisrt parses all the java source code files and tokenize it into multidimentional array list.
- Than with the help of nested loops the longest sequences are extracted, which are common in the list of files.
- Finally it generates a csv file as a result.