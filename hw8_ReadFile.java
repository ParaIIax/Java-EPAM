package ua.univer.task8;

import java.io.FileInputStream;
import java.io.IOException;

public class ReadFile {
    public static String readFromFile(String filename) {
        try(FileInputStream fin=new FileInputStream(filename))
        {
            byte[] buffer = new byte[fin.available()];
            fin.read(buffer, 0, buffer.length);
            String text = "";
            for (int i = 0; i < buffer.length; ++i)
                text += (char)buffer[i];
            return text;
        }
        catch(IOException ex){
            System.out.println(ex.getMessage());
        }
        return "";
    }
}
