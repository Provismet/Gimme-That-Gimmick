package com.provismet.cobblemon.gimmick.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.lilylib.util.json.JsonBuilder;
import com.provismet.lilylib.util.json.JsonReader;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;

public abstract class Options {
    private static final String FILE = "./config/gimme-that-gimmick.json";

    private static boolean overrideShowdown = true;
    private static boolean powerSpotRequired = true;
    private static int powerSpotRange = 30;
    private static float dynamaxScaleFactor = 4;
    private static boolean breakableTeraOrbs = true;
    private static boolean applyBasicZGlow = true;
    private static boolean applyBasicDynamaxGlow = true;
    private static boolean applyBasicTeraGlow = true;
    private static boolean showDynamaxLevel = true;

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

    public static int getDynamaxScaleDuration () {
        return (int)(dynamaxScaleFactor / 0.1f);
    }

    public static boolean canBreakTeraOrb () {
        return breakableTeraOrbs;
    }

    public static boolean shouldApplyBasicZGlow () {
        return applyBasicZGlow;
    }

    public static boolean shouldApplyBasicDynamaxGlow () {
        return applyBasicDynamaxGlow;
    }

    public static boolean shouldApplyBasicTeraGlow () {
        return applyBasicTeraGlow;
    }

    public static boolean shouldShowDynamaxLevel () {
        return showDynamaxLevel;
    }

    public static void save () {
        JsonObject json = new JsonBuilder()
            .append("override_showdown", overrideShowdown)
            .append("dynamax_power_spot_range", powerSpotRange)
            .append("dynamax_power_spot_required", powerSpotRequired)
            .append("dynamax_scale_factor", dynamaxScaleFactor)
            .append("breakable_tera_orbs", breakableTeraOrbs)
            .append("use_default_z_glow_visual", applyBasicZGlow)
            .append("use_default_dynamax_glow_visual", applyBasicDynamaxGlow)
            .append("use_default_tera_glow_visual", applyBasicTeraGlow)
            .append("show_dynamax_level", showDynamaxLevel)
            .getJson();

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
            JsonReader reader = JsonReader.file(new File(FILE));
            if (reader != null) {
                reader.getBoolean("override_showdown").ifPresent(val -> overrideShowdown = val);
                reader.getInteger("dynamax_power_spot_range").ifPresent(val -> powerSpotRange = val);
                reader.getBoolean("dynamax_power_spot_required").ifPresent(val -> powerSpotRequired = val);
                reader.getFloat("dynamax_scale_factor").ifPresent(val -> dynamaxScaleFactor = val);
                reader.getBoolean("breakable_tera_orbs").ifPresent(val -> breakableTeraOrbs = val);
                reader.getBoolean("use_default_z_glow_visual").ifPresent(val -> applyBasicZGlow = val);
                reader.getBoolean("use_default_dynamax_glow_visual").ifPresent(val -> applyBasicDynamaxGlow = val);
                reader.getBoolean("use_default_tera_glow_visual").ifPresent(val -> applyBasicTeraGlow = val);
                reader.getBoolean("show_dynamax_level").ifPresent(val -> showDynamaxLevel = val);
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
