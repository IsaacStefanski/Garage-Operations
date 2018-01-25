package ics.parkinggarageapp;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Isaac
 */
public class RunningTotalsFormatter implements TextFileFormatter {
    @Override
    public final String encode(List<Map<String, String>> rawData) throws EncodingException {
        if(rawData == null){
            throw new EncodingException();
        }
        String encodedData = "";
        for(Map<String, String> map : rawData){
            encodedData += map.get("time") + "\n";
            encodedData += map.get("revenue") + "\n";
        }
        return encodedData;
    }
}