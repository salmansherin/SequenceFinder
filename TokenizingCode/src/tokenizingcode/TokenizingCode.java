/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package tokenizingcode;

import ca.tokenizing_parser.tokenizer.Tokenizer;
import ca.tokenizing_parser.tokenizer.languages.JavaKeywords;
import com.sun.org.apache.xpath.internal.FoundIndex;
import java.io.File;
import java.io.IOException;
import java.io.PrintWriter;
import java.io.StringReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author salman sherin
 */
public class TokenizingCode {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        try {
            // TODO code application logic here
            Tokenizer tokenizer;
            HashMap tokenFound = new HashMap();
            String[] files = new String[2];
            String input = "";
            ArrayList<ArrayList> allTokens = new ArrayList<ArrayList>();
            ArrayList<String> mList;
            ArrayList<String> cList;
            ArrayList<String> commonTokens = new ArrayList<String>();

            files[0] = "C:\\Users\\salman\\Downloads\\Compressed\\Task 2\\"
                    + "WebDriverBackedEmbeddedBrowser.java";
            files[1] = "C:\\Users\\salman\\Downloads\\Compressed\\Task 2\\AbstractJavaSource.java";

            for (String filePath : files) {
                tokenizer = new Tokenizer(JavaKeywords.getInstance());;
                input = new String(Files.readAllBytes(Paths.get(filePath)));
                allTokens.add(tokenizer.tokenizeInput(input));

            }

            int totalLists = allTokens.size() - 1;
            int m = 0, c = 0;
            int lastIndex = 0;
            String lastToken = "";
            String tokenSequence = "";
            int mtSize = 0;
            int ctSize = 0;

            for (int i = 0; i <= totalLists; i++) {
                mList = allTokens.get(i);
                mtSize = mList.size();
                for (int j = 0; j <= totalLists; j++) {
                    if (i == j) {
                        continue;
                    }
                    cList = allTokens.get(j);
                    ctSize = cList.size();
                    lastToken = "";
                    lastIndex = 0;

                    while (m < mtSize) {
                        tokenSequence = "";
                        lastToken = mList.get(m);
                        lastIndex = cList.indexOf(lastToken);
                        if (mList.size() <= m + 1 || cList.size() <= lastIndex + 1) {
                            break;
                        }
                        if (lastIndex >= 0 && mList.get(m + 1).equals(cList.get(lastIndex + 1))) {

                            while (true) {
                                tokenSequence += " " + mList.get(m);
                                lastIndex++;
                                m++;
                                if (mList.size() <= m || cList.size() <= lastIndex) {
                                    break;
                                }
                                try {
                                    if (!mList.get(m).equals(cList.get(lastIndex))) {
                                        break;
                                    }
                                } catch (Exception e) {
                                    System.out.println(e.getMessage());
                                }

                            }
                            commonTokens.add(tokenSequence);
                            System.out.println(tokenSequence);
                            continue;
                        }
                        m++;
                    }

                }
            }

            int totalCommon = commonTokens.size() - 1;
            String token = "";
            int occurrance = 0;
            for (int i = 0; i <= totalCommon; i++) {
                token = commonTokens.get(i);
                occurrance = (int) tokenFound.getOrDefault(token, 0);
                occurrance += 1;
                tokenFound.put(token, occurrance);
            }

            // CSV Writer
            PrintWriter pw = new PrintWriter(new File("test.csv"));
            StringBuilder sb = new StringBuilder();
            Iterator it = tokenFound.entrySet().iterator();
            Double score = 0.0;
            while (it.hasNext()) {
                Map.Entry pair = (Map.Entry) it.next();

                score = Math.log(pair.getKey().toString().split(" ").length) / Math.log(2);
                score = score * Math.log(Integer.parseInt(pair.getValue().toString())) / Math.log(2);

                if (score < 1) {
                    it.remove(); // avoids a ConcurrentModificationException

                    continue;
                }

                sb.append(String.format("%.2f", score));
                sb.append(',');
                sb.append(pair.getKey().toString().split(" ").length);
                sb.append(',');
                sb.append(pair.getValue());
                sb.append(',');
                sb.append(pair.getKey());
                sb.append('\n');

                it.remove(); // avoids a ConcurrentModificationException
            }

            pw.write(sb.toString());
            pw.close();
            System.out.println("Done");

//
//            ArrayList<String> commonTokens = new ArrayList<String>();
//
//            int i = 0;
//            int t = tokenizeInput1.size();
//            String lastToken = "";
//            String stringTokens = "";
//            int lastIndex = 0;
//            System.out.println(t);
        } catch (Exception ex) {
            Logger.getLogger(TokenizingCode.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
