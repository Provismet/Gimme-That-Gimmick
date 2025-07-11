package com.provismet.cobblemon.gimmick.datagen.debug;

import com.cobblemon.mod.common.util.MiscUtilsKt;
import com.provismet.cobblemon.gimmick.GimmeThatGimmickMain;
import com.provismet.cobblemon.gimmick.api.data.particle.SnowstormEffect;
import com.provismet.cobblemon.gimmick.api.data.particle.VanillaParticleEffect;
import com.provismet.cobblemon.gimmick.api.data.particle.VanillaParticleEffect.VanillaParticleLayer;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.api.datagen.EffectsDataProvider;
import net.fabricmc.fabric.api.datagen.v1.FabricDataOutput;
import net.minecraft.particle.ParticleTypes;
import net.minecraft.registry.RegistryWrapper;
import net.minecraft.sound.SoundEvents;
import net.minecraft.util.Identifier;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.function.BiConsumer;

public class DebugEffectsGenerator extends EffectsDataProvider {
    public DebugEffectsGenerator (FabricDataOutput output, CompletableFuture<RegistryWrapper.WrapperLookup> registriesFuture) {
        super(output, registriesFuture);
    }

    @Override
    protected void generate (RegistryWrapper.WrapperLookup wrapperLookup, BiConsumer<Identifier, EffectsData> consumer) {
        consumer.accept(
            GimmeThatGimmickMain.identifier("fake_snowstorm"),
            new EffectsData(
                Optional.of(new SnowstormEffect(MiscUtilsKt.cobblemonResource("fake_snowstorm"), List.of("target"), Optional.empty())),
                Optional.of("cry"),
                Optional.of(2f),
                Optional.of(SoundEvents.BLOCK_AMETHYST_BLOCK_CHIME)
            )
        );

        consumer.accept(
            GimmeThatGimmickMain.identifier("layered_vanilla"),
            new EffectsData(
                Optional.of(new VanillaParticleEffect(
                    List.of(
                        new VanillaParticleLayer(ParticleTypes.CLOUD, 10, 4),
                        new VanillaParticleLayer(ParticleTypes.HAPPY_VILLAGER, 10, 1)
                    )
                )),
                Optional.of("cry"),
                Optional.empty(),
                Optional.of(SoundEvents.BLOCK_BEACON_ACTIVATE)
            )
        );

        consumer.accept(
            GimmeThatGimmickMain.identifier("mega_evolution"),
            new EffectsData(
                Optional.of(new VanillaParticleEffect(
                    List.of(
                        new VanillaParticleLayer(ParticleTypes.HEART, 3, 1),
                        new VanillaParticleLayer(ParticleTypes.CRIT, 50, 3)
                    )
                )),
                Optional.of("cry"),
                Optional.of(2.5f),
                Optional.of(SoundEvents.ITEM_TOTEM_USE)
            )
        );
    }
}
