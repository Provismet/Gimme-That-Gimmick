package com.provismet.cobblemon.gimmick.config;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;

import java.io.File;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;
import java.util.List;

public abstract class ShowdownMod {
    private static final String SCRIPTS_FOLDER = "./showdown_addons";
    private static final String SHOWDOWN_MODS_FOLDER = "./showdown/data/mods/cobblemon";
    private static final List<String> FILES = List.of(
        "abilities.js",
        "items.js",
        "pokedex.js",
        "moves.js",
        "scripts.js",
        "tags.js"
    );

    public static void apply () {
        try {
            Files.createDirectories(Path.of(SCRIPTS_FOLDER));

            for (String file : FILES) {
                load(file);
            }
        }
        catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Gimme That Gimmick failed to setup custom Showdown scripts due to error: ", e);
        }
    }

    private static void load (String filename) {
        try {
            Files.createDirectories(Path.of(SHOWDOWN_MODS_FOLDER));
            File sourceFile = new File(SCRIPTS_FOLDER, filename);
            File destinationFile = new File(SHOWDOWN_MODS_FOLDER, filename);
            if (!sourceFile.canRead()) return;

            Files.copy(sourceFile.toPath(), destinationFile.toPath(), StandardCopyOption.REPLACE_EXISTING);
            GimmeThatGimmickMain.LOGGER.info("Gimme That Gimmick successfully copied {} to Showdown.", filename);
        }
        catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Gimme That Gimmick failed to copy {} to Showdown due to error: ", filename, e);
        }
    }
}
