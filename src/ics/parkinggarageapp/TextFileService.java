package ics.parkinggarageapp;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;

/**
 *
 * @author Isaac
 */
public class TextFileService {
    private TextFileFormatter formatter;
    
    public TextFileService(TextFileFormatter formatter) {
        setFormatter(formatter);
    }
    
    public final void writeFile(List<Map<String, String>> rawData, File file) throws IOException, EncodingException {
        String formattedData = formatter.encode(rawData);    
        PrintWriter out = new PrintWriter(new BufferedWriter(new FileWriter(file, false)));        
        out.println(formattedData);      
        out.close();
    }
    
    public final List readFileToList(File file) throws FileNotFoundException, IOException {
        FileReader fileReader = new FileReader(file);
        BufferedReader bufferedReader = new BufferedReader(fileReader);
        List<String> list = new ArrayList<>();
        String line = "";
        
        while((line = bufferedReader.readLine()) != null) {
            list.add(line);
        }
            
        bufferedReader.close();
        
        return list;
    }

    public final TextFileFormatter getFormatter() {
        return formatter;
    }

    public final void setFormatter(TextFileFormatter formatter) throws IllegalArgumentException {
        if(formatter == null){
            throw new IllegalArgumentException("Sorry, you cannot use a null formatter");
        }
        this.formatter = formatter;
    }

    @Override
    public final int hashCode() {
        int hash = 7;
        hash = 17 * hash + Objects.hashCode(this.formatter);
        return hash;
    }

    @Override
    public final boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final TextFileService other = (TextFileService) obj;
        if (!Objects.equals(this.formatter, other.formatter)) {
            return false;
        }
        return true;
    }

    @Override
    public final String toString() {
        return "TextFileService{" + "formatter=" + formatter + '}';
    }
}