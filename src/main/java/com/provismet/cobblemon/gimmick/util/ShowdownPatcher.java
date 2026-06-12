package com.provismet.cobblemon.gimmick.util;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.config.Options;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class ShowdownPatcher {
    private static final String SHOWDOWN_VERSION = "1.0.0";
    private static final Gson GSON = new Gson();

    public static void patch() {
        if (Options.shouldAutoUpdateShowdown()) {
            Path showdown_sim = Path.of("./showdown/sim");
            Path showdown_data = Path.of("./showdown/data");
            Path showdown_dir = Path.of("./showdown");
            Path showdown_mod_data = Path.of("./showdown/data/mods/cobblemon");
            Path versionFile = showdown_dir.resolve("GTGPatch.json");

            try {
                Files.createDirectories(showdown_sim);
                Files.createDirectories(showdown_data);

                if (isUpToDate(versionFile)) {
                    GimmeThatGimmickMain.LOGGER.info("Showdown files are up to date (v{}).", SHOWDOWN_VERSION);
                    return;
                }

                if (!Files.exists(showdown_mod_data.resolve("items.js"))) {
                    yoink("/showdown_scripts/mods/items.js", showdown_mod_data.resolve("items.js"));
                }
                if (!Files.exists(showdown_mod_data.resolve("conditions.js"))) {
                    yoink("/showdown_scripts/mods/conditions.js", showdown_mod_data.resolve("conditions.js"));
                }
                if (!Files.exists(showdown_mod_data.resolve("typechart.js"))) {
                    yoink("/showdown_scripts/mods/typechart.js", showdown_mod_data.resolve("typechart.js"));
                }

                yoink("/showdown_scripts/battle-actions.js", showdown_sim.resolve("battle-actions.js"));
                yoink("/showdown_scripts/pokemon.js", showdown_sim.resolve("pokemon.js"));
                yoink("/showdown_scripts/conditions.js", showdown_data.resolve("conditions.js"));
                yoink("/showdown_scripts/index.js", showdown_dir.resolve("index.js"));
                yoink("/showdown_scripts/side.js", showdown_sim.resolve("side.js"));

                writeVersionFile(versionFile);
                GimmeThatGimmickMain.LOGGER.info("All files are ready!");
            } catch (IOException e) {
                GimmeThatGimmickMain.LOGGER.error("Failed to prepare required files: {}", e.getMessage());
            }
        }
    }

    private static boolean isUpToDate(Path versionFile) {
        if (!Files.exists(versionFile)) return false;
        try (Reader reader = Files.newBufferedReader(versionFile)) {
            JsonObject json = GSON.fromJson(reader, JsonObject.class);
            return json != null && SHOWDOWN_VERSION.equals(json.get("version").getAsString());
        } catch (Exception e) {
            return false;
        }
    }

    private static void writeVersionFile(Path versionFile) throws IOException {
        JsonObject json = new JsonObject();
        json.addProperty("version", SHOWDOWN_VERSION);
        Files.writeString(versionFile, GSON.toJson(json));
    }

    private static void yoink(String resourcePath, Path targetPath) {
        try (InputStream inputStream = ShowdownPatcher.class.getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                GimmeThatGimmickMain.LOGGER.error("Fallback file not found: {}", resourcePath);
                return;
            }
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            GimmeThatGimmickMain.LOGGER.info("Loaded fallback file: {}", targetPath);
        } catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Failed to copy fallback file {}: {}", resourcePath, e.getMessage());
        }
    }
}