package com.provismet.cobblemon.gimmick.config;

import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.lilylib.util.json.JsonConfig;
import com.provismet.lilylib.util.json.JsonReader;
import net.fabricmc.loader.api.FabricLoader;

import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.nio.file.Path;

public abstract class Options {
    private static final Path FILE = FabricLoader.getInstance().getConfigDir().resolve("gimme-that-gimmick.json");

    private static boolean autoUpdateShowdown = true;
    private static boolean megaEvolution = true;
    private static boolean zMoves = true;
    private static boolean dynamax = true;
    private static boolean terastal = true;
    private static boolean powerSpotRequired = true;
    private static int powerSpotRange = 30;
    private static float dynamaxScaleFactor = 4;
    private static boolean breakableTeraOrbs = true;
    private static boolean applyBasicZGlow = true;
    private static boolean applyBasicDynamaxGlow = true;
    private static boolean applyBasicTeraGlow = true;
    private static boolean showDynamaxLevel = true;
    private static boolean allowMultipleMega = false;
    private static boolean gimmickEnchantments = true;

    private static final JsonConfig CONFIG = new JsonConfig()
        .addBoolean("auto_update_showdown", Options::shouldAutoUpdateShowdown, val -> autoUpdateShowdown = val)
        .addBoolean("enable_mega_evolution", Options::enabledMegaEvolution, val -> megaEvolution = val)
        .addBoolean("enable_z-moves", Options::enabledZMoves, val -> zMoves = val)
        .addBoolean("enable_dynamax", Options::enabledDynamax, val -> dynamax = val)
        .addBoolean("enable_terastallization", Options::enabledTerastal, val -> terastal = val)
        .addInteger("dynamax_power_spot_range", Options::getPowerSpotRange, val -> powerSpotRange = val)
        .addBoolean("dynamax_power_spot_required", Options::isPowerSpotRequired, val -> powerSpotRequired = val)
        .addFloat("dynamax_scale_factor", () -> dynamaxScaleFactor, val -> dynamaxScaleFactor = val)
        .addBoolean("breakable_tera_orbs", Options::canBreakTeraOrb, val -> breakableTeraOrbs = val)
        .addBoolean("use_default_z_glow_visual", Options::shouldApplyBasicZGlow, val -> applyBasicZGlow = val)
        .addBoolean("use_default_dynamax_glow_visual", Options::shouldApplyBasicDynamaxGlow, val -> applyBasicDynamaxGlow = val)
        .addBoolean("use_default_tera_glow_visual", Options::shouldApplyBasicTeraGlow, val -> applyBasicTeraGlow = val)
        .addBoolean("show_dynamax_level", Options::shouldShowDynamaxLevel, val -> showDynamaxLevel = val)
        .addBoolean("allow_multiple_out_of_battle_megas", Options::shouldAllowMultipleOutOfCombatMegas, val -> allowMultipleMega = val)
        .addBoolean("enable_gimmick_enchantments", Options::includeGimmickEnchantments, val -> gimmickEnchantments = val);

    static {
        load();
    }

    public static boolean shouldAutoUpdateShowdown () {
        return autoUpdateShowdown;
    }

    public static boolean enabledMegaEvolution () {
        return megaEvolution;
    }

    public static boolean enabledZMoves () {
        return zMoves;
    }

    public static boolean enabledDynamax () {
        return dynamax;
    }

    public static boolean enabledTerastal () {
        return terastal;
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

    public static boolean shouldAllowMultipleOutOfCombatMegas () {
        return allowMultipleMega;
    }

    public static boolean includeGimmickEnchantments () {
        return gimmickEnchantments;
    }

    public static void save () {
        JsonObject json = CONFIG.createJson();

        try (FileWriter writer = new FileWriter(FILE.toFile())) {
            writer.write(new GsonBuilder().setPrettyPrinting().create().toJson(json));
        }
        catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Gimme That Gimmick failed to write settings file due to error: ", e);
        }
    }

    public static void load () {
        try {
            JsonReader reader = JsonReader.file(FILE.toFile());
            if (reader != null) {
                CONFIG.loadFromJson(reader);
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
