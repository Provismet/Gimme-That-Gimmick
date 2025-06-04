package com.provismet.cobblemon.gimmick.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;

import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Options {
    private static final String FILE = "./config/gimme-that-gimmick.json";

    private static boolean overrideShowdown = true;
    private static boolean powerSpotRequired = true;
    private static int powerSpotRange = 30;

    static {
        load();
    }

    public static boolean shouldOverrideShowdown () {
        return overrideShowdown;
    }

    public static int getPowerSpotRange () {
        return powerSpotRange;
    }

    public static boolean isPowerSpotRequired () {
        return powerSpotRequired;
    }

    public static void save () {
        JsonObject json = new JsonObject();
        json.addProperty("override_showdown", overrideShowdown);
        json.addProperty("dynamax_power_spot_range", powerSpotRange);
        json.addProperty("dynamax_power_spot_required", powerSpotRequired);

        try (FileWriter writer = new FileWriter(FILE)) {
            Files.createDirectories(Path.of("./config"));
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(json));
        }
        catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Gimme That Gimmick failed to write settings file due to error: ", e);
        }
    }

    public static void load () {
        try {
            JsonElement element = JsonParser.parseReader(new FileReader(FILE));
            if (element instanceof JsonObject json) {
                if (json.has("override_showdown")) {
                    overrideShowdown = json.getAsJsonPrimitive("override_showdown").getAsBoolean();
                }
                if (json.has("dynamax_power_spot_range")) {
                    powerSpotRange = json.getAsJsonPrimitive("dynamax_power_spot_range").getAsInt();
                }
                if (json.has("dynamax_power_spot_required")) {
                    powerSpotRequired = json.getAsJsonPrimitive("dynamax_power_spot_required").getAsBoolean();
                }
            }
        }
        catch (FileNotFoundException e) {
            GimmeThatGimmickMain.LOGGER.info("Could not find Gimme That Gimmick config, constructing default.");
        }
        catch (Exception e) {
            GimmeThatGimmickMain.LOGGER.error("Could read Gimme That Gimmick config due to error:", e);
        }
        save();
    }
}
