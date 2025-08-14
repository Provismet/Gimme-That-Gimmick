package com.provismet.cobblemon.gimmick.commands;

import com.provismet.cobblemon.gimmick.config.Options;
import net.fabricmc.fabric.api.command.v2.CommandRegistrationCallback;
import net.minecraft.server.command.CommandManager;
import net.minecraft.text.Text;

public class GTGReloadCommand {
    public static void register () {
        CommandRegistrationCallback.EVENT.register((dispatcher, registryAccess, environment) -> {
            dispatcher.register(CommandManager.literal("gimme-that-gimmick")
                .requires(source -> source.hasPermissionLevel(2))
                .then(CommandManager.literal("reload")
                    .executes(context -> {
                        Options.load();
                        context.getSource().sendFeedback(() -> Text.translatable("command.gimme-that-gimmick.reload.success"), true);
                        return 0;
                    })
                )
            );
        });
    }
}
