package ccrp.businesssystem.data;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.util.HashMap;
import java.util.TreeMap;

import org.bukkit.ChatColor;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;

public class Config {

    private File file;
    private JSONObject json;
    private JSONParser parser = new JSONParser();
    private HashMap<String, Object> defaults = new HashMap<String, Object>();

    public Config(File file) {
        this.file = file;
        reload();
    }

    public void reload() {
        try {
            if (!file.getParentFile().exists()) {
                file.getParentFile().mkdirs();
            }
            if (!file.exists()) {
                PrintWriter pw = new PrintWriter(file, "UTF-8");
                pw.print("{");
                pw.print("}");
                pw.flush();
                pw.close();
            }
            json = (JSONObject) parser.parse(new InputStreamReader(Files.newInputStream(file.toPath()), StandardCharsets.UTF_8));


            JSONObject trainee = new JSONObject();
            trainee.put("name","trainee");
            trainee.put("priority",3);
            trainee.put("payout-percentage", 2);
            defaults.put("trainee",trainee);

            JSONObject employee = new JSONObject();
            employee.put("name","employee");
            employee.put("priority",2);
            employee.put("payout-percentage", 5);
            defaults.put("employee",employee);

            JSONObject assistant = new JSONObject();
            assistant.put("name","assistant");
            assistant.put("priority",1);
            assistant.put("payout-percentage", 8);
            defaults.put("assistant",assistant);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    @SuppressWarnings("unchecked")
    public boolean save() {
        try {
            JSONObject toSave = new JSONObject();

            for (String s : defaults.keySet()) {
                Object o = defaults.get(s);
                if (o instanceof String) {
                    toSave.put(s, getString(s));
                } else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

    public String getRawData(String key) {
        return json.containsKey(key) ? json.get(key).toString()
                : (defaults.containsKey(key) ? defaults.get(key).toString() : key);
    }

    public String getString(String key) {
        return ChatColor.translateAlternateColorCodes('&', getRawData(key));
    }

    public boolean getBoolean(String key) {
        return Boolean.parseBoolean(getRawData(key));
    }

    public double getDouble(String key) {
        try {
            return Double.parseDouble(getRawData(key));
        } catch (Exception ignored) {
        }
        return -1;
    }

    public double getInteger(String key) {
        try {
            return Integer.parseInt(getRawData(key));
        } catch (Exception ignored) {
        }
        return -1;
    }

    public JSONObject getObject(String key) {
        return json.containsKey(key) ? (JSONObject) json.get(key)
                : (defaults.containsKey(key) ? (JSONObject) defaults.get(key) : new JSONObject());
    }

    public JSONArray getArray(String key) {
        return json.containsKey(key) ? (JSONArray) json.get(key)
                : (defaults.containsKey(key) ? (JSONArray) defaults.get(key) : new JSONArray());
    }

    public boolean save(HashMap<String,Object> values) {
        try {
            JSONObject toSave = new JSONObject();

            for (String s : values.keySet()) {
                Object o = values.get(s);
                if (o instanceof String) {
                    toSave.put(s, getString(s));
                } else if (o instanceof Double) {
                    toSave.put(s, getDouble(s));
                } else if (o instanceof Integer) {
                    toSave.put(s, getInteger(s));
                } else if (o instanceof JSONObject) {
                    toSave.put(s, getObject(s));
                } else if (o instanceof JSONArray) {
                    toSave.put(s, getArray(s));
                }
            }

            TreeMap<String, Object> treeMap = new TreeMap<String, Object>(String.CASE_INSENSITIVE_ORDER);
            treeMap.putAll(toSave);

            Gson g = new GsonBuilder().setPrettyPrinting().create();
            String prettyJsonString = g.toJson(treeMap);

            FileWriter fw = new FileWriter(file);
            fw.write(prettyJsonString);
            fw.flush();
            fw.close();

            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
            return false;
        }
    }

}