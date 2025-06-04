package com.provismet.cobblemon.gimmick.util.phase;

import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import net.fabricmc.fabric.api.event.Event;
import net.minecraft.util.Identifier;

public interface EventPhases {
    Identifier EARLIEST = GimmeThatGimmickMain.identifier("earliest");
    Identifier PRE_DEFAULT = GimmeThatGimmickMain.identifier("pre_default");
    Identifier POST_DEFAULT = GimmeThatGimmickMain.identifier("post_default");
    Identifier LATEST = GimmeThatGimmickMain.identifier("latest");

    Identifier[] PHASE_ORDERING = new Identifier[]{EARLIEST, PRE_DEFAULT, Event.DEFAULT_PHASE, POST_DEFAULT, LATEST};
}
