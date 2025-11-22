package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.battles.runner.graal.GraalShowdownUnbundler;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.config.Options;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Unique;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

/**
 * Code adapted from to YajatKaul @ MegaShowdown.
 */
@Mixin(GraalShowdownUnbundler.class)
public abstract class GraalShowdownUnbundlerMixin {
    @Unique private boolean loaded = false;

    @Inject(method = "attemptUnbundle", at = @At("TAIL"), remap = false)
    private void replaceScripts (CallbackInfo info) {
        if(!loaded){
            loaded = true;
            if (Options.shouldOverrideShowdown()) {
                Path showdown_sim = Path.of("./showdown/sim");
                Path showdown_data = Path.of("./showdown/data");
                Path showdown_dir = Path.of("./showdown");

                try {
                    Files.createDirectories(showdown_sim);
                    Files.createDirectories(showdown_data);

                    yoink("/showdown_scripts/battle-actions.js", showdown_sim.resolve("battle-actions.js"));
                    yoink("/showdown_scripts/pokemon.js", showdown_sim.resolve("pokemon.js"));
                    yoink("/showdown_scripts/conditions.js", showdown_data.resolve("conditions.js"));
                    yoink("/showdown_scripts/index.js", showdown_dir.resolve("index.js"));
                    yoink("/showdown_scripts/side.js", showdown_sim.resolve("side.js"));

                    GimmeThatGimmickMain.LOGGER.info("All files are ready!");
                } catch (IOException e) {
                    GimmeThatGimmickMain.LOGGER.error("Failed to prepare required files: {}", e.getMessage());
                }
            }
        }
    }

    @Unique
    private void yoink (String resourcePath, Path targetPath) {
        try (InputStream inputStream = this.getClass().getResourceAsStream(resourcePath)) {
            if (inputStream == null) {
                GimmeThatGimmickMain.LOGGER.error("Fallback file not found: {}", resourcePath);
                return;
            }
            Files.copy(inputStream, targetPath, StandardCopyOption.REPLACE_EXISTING);
            GimmeThatGimmickMain.LOGGER.info("Loaded Showdown override file: {}", targetPath);
        } catch (IOException e) {
            GimmeThatGimmickMain.LOGGER.error("Failed to copy Showdown override file {}: {}", resourcePath, e.getMessage());
        }
    }
}
