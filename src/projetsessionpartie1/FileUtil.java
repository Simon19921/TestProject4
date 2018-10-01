package projetsessionpartie1;

import java.io.File;
import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import net.sf.json.JSONObject;
import org.apache.commons.io.FileUtils;

/**
 * This class is used to read, write and create the initial JSONObject from
 * the JSON file.
 * 
 * It's also used to convert parsed values to another type for
 * easier treatment.
 * 
 * Finally, all the keys for the properties that are to be parsed in the JSON
 * file are stored in this file to centralize the access to them.
 */
public class FileUtil {
    public static final String TERRAIN_TYPE_PROPERTY_NAME = "type_terrain";
    public static final String MIN_COST_PROPERTY_NAME = "prix_m2_min";
    public static final String MAX_COST_PROPERTY_NAME = "prix_m2_max";
    public static final String LOTS_PROPERTY_NAME = "lotissements";
    public static final String DESCRIPTION_PROPERTY_NAME = "description";
    public static final String NUMBER_OF_PASSAGES_PROPERTY_NAME = "nombre_droits_passage";
    public static final String NUMBER_OF_SERVICES_PROPERTY_NAME = "nombre_services";
    public static final String AREA_PROPERTY_NAME = "superficie";
    public static final String DATE_PROPERTY_NAME = "date_mesure";
    public static final String TOTAL_VALUE_PROPERTY_NAME = "valeur_fonciere_totale";
    public static final String SCHOOL_TAX_PROPERTY_NAME = "taxe_scolaire";
    public static final String CITY_TAX_PROPERTY_NAME = "taxe_municipale";
    public static final String VALUE_PER_LOT_PROPERTY_NAME = "valeur_par_lot";
    
    private static JSONObject _jsonRootObject = new JSONObject();
    
    public static JSONObject GetJsonObject(){
        return _jsonRootObject;
    }
        
    public static void ReadJsonFile(String jsonFile) throws IOException{
        if (FileExists(jsonFile)){
            File inputFile = new File(jsonFile);
            String fileContent = FileUtils.readFileToString(inputFile, "UTF-8");
            
            _jsonRootObject = JSONObject.fromObject(fileContent);
        }
    }
    
    public static void WriteJsonFile(String jsonFile, String fileContent) throws IOException{
            File outputFile = new File(jsonFile);
            FileUtils.writeStringToFile(outputFile, fileContent);
    }
    
    public static Boolean FileExists(String filePath){
        Boolean fileExists = false;
        
        if (!filePath.isEmpty()){
            File file = new File(filePath);
            fileExists = file.exists();
        }
        
        return fileExists;
    }
    
    public static String ConvertDoubleToStringCurrency(double value){
        return String.format("%s $", Double.toString(value));
    }
    
    public static Double ConvertStringCurrencyToDouble(String value){
        String amountWithoutSymbols = value.replaceAll("[^\\d.]+", "");
        return Double.parseDouble(amountWithoutSymbols);
    }
    
    public static Date ConvertStringToDate(String date) throws ParseException{
        SimpleDateFormat dateFormatter = new SimpleDateFormat("YYYY-MM-DD");
        return dateFormatter.parse(date);
    }
}
