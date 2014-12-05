package com.vl.sf.core;
import java.io.*;
public class UnIndentToIndent {
    public static void printSpaces(int depth, FileOutputStream out)
        throws IOException {
            out.write('\n');
            for (int i = 0; i < depth; i++) {
                out.write(' ');
            }
        }

    public static void main(String args[]) throws IOException {
        FileInputStream in = null;
        FileOutputStream out = null;

        try {
            //in = new FileInputStream("unIndented.txt");
            //TODO: Check that args[0] is passed
            in = new FileInputStream(args[0]);
            //TODO: Check that args[1] is passed
            out = new FileOutputStream(args[1]);

            int current;
            int depth = 0;
            int newLineCount=0;
            boolean skipSpace = true;
            while ((current = in.read()) != -1) {
                if (current == ' ' && skipSpace) {
                    continue;
                } else if (current == '{') {
                    if(newLineCount<2){
                        printSpaces(depth, out);
                    }
                    out.write(current);
                    depth+=4;
                    skipSpace = true;
                    newLineCount=0;
                } else if (current == '}') {
                    depth-=4;
                        printSpaces(depth, out);
                    skipSpace = true;
                    out.write(current);
                    newLineCount=0;
                } else if(current=='\n' || current==13){
                    skipSpace = true;
                    ++newLineCount;
                    if(newLineCount<2){
                        printSpaces(depth, out);
                    }
                }else{
                    out.write(current);
                    skipSpace = false;
                    newLineCount=0;
                }
            }
        } finally {
            if (in != null) {
                in.close();
            }
            if (out != null) {
                out.close();
            }
        }
    }
}

