import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.file.Files;
import java.nio.file.Path;

import static java.nio.file.StandardOpenOption.CREATE;
import javax.swing.JFileChooser;

public class Main {
    public static void main(String[] args) throws IOException
    {
        JFileChooser chooser = new JFileChooser();
        File selectedFile;

        int lineC = 0;
        int characterC = 0;
        int wordC = 0;
        String eachLine;

        try {

            if(chooser.showOpenDialog(null) == JFileChooser.APPROVE_OPTION)
            {
                selectedFile = chooser.getSelectedFile();
                Path file = selectedFile.toPath();
                InputStream in = new BufferedInputStream(Files.newInputStream(file, CREATE));
                BufferedReader reader = new BufferedReader(new InputStreamReader(in));

                System.out.println("The file name is: " + selectedFile.getName());
                while (reader.ready()) {
                    eachLine = reader.readLine();
                    String[] words = eachLine.split(" ");
                    wordC = wordC + words.length;
                    lineC = lineC + 1;
                    characterC = characterC + eachLine.length();
                }
                reader.close(); // must close the file to seal it and flush buffer

                System.out.printf("\nLine count = %5d", lineC);
                System.out.printf("\nWord count = %5d", wordC);
                System.out.printf("\nCharacter count = %5d", characterC);
                System.out.println("\n\nData file read!");
            }
        } catch (FileNotFoundException e) {
            System.out.println("File not found!!!");
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

    }
}