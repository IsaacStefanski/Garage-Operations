package ics.parkinggarageapp;

import java.util.List;
import java.util.Map;

/**
 *
 * @author Isaac
 */
public interface TextFileFormatter {
    public abstract String encode(List<Map<String, String>> rawData) throws EncodingException;
}