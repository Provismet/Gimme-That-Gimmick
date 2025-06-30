package com.provismet.cobblemon.gimmick.api.data;

import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;

public record MegaEvolution (PokemonRequirements pokemon, PokemonFeatures onApply, PokemonFeatures onRemove) {
    public static final MegaEvolution DEFAULT = MegaEvolution.create("none");

    public static final Codec<MegaEvolution> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        PokemonRequirements.CODEC.fieldOf("pokemon").forGetter(MegaEvolution::pokemon),
        PokemonFeatures.CODEC.optionalFieldOf("appliedFeatures", PokemonFeatures.single("mega_evolution", "mega")).forGetter(MegaEvolution::onApply),
        PokemonFeatures.CODEC.optionalFieldOf("defaultFeatures", PokemonFeatures.single("mega_evolution", "none")).forGetter(MegaEvolution::onRemove)
    ).apply(instance, MegaEvolution::new));

    public static final PacketCodec<RegistryByteBuf, MegaEvolution> PACKET_CODEC = PacketCodec.tuple(
        PokemonRequirements.PACKET_CODEC,
        MegaEvolution::pokemon,
        PokemonFeatures.PACKET_CODEC,
        MegaEvolution::onApply,
        PokemonFeatures.PACKET_CODEC,
        MegaEvolution::onRemove,
        MegaEvolution::new
    );

    public static MegaEvolution create (String speciesId) {
        return create(speciesId, "normal");
    }

    public static MegaEvolution create (String speciesId, String formId) {
        return create(speciesId, formId, "mega");
    }

    public static MegaEvolution create (String speciesId, String formId, String megaAspect) {
        return new MegaEvolution(
            PokemonRequirements.speciesForm(speciesId, formId),
            PokemonFeatures.single("mega_evolution", megaAspect),
            PokemonFeatures.single("mega_evolution", "none")
        );
    }
}
