package com.provismet.cobblemon.gimmick.api.data;

import com.cobblemon.mod.common.api.pokemon.feature.StringSpeciesFeature;
import com.cobblemon.mod.common.pokemon.FormData;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.mojang.serialization.Codec;
import com.mojang.serialization.codecs.RecordCodecBuilder;
import net.minecraft.network.RegistryByteBuf;
import net.minecraft.network.codec.PacketCodec;
import net.minecraft.network.codec.PacketCodecs;

/**
 * Represents a potential mega evolution.
 *
 * @param speciesFormId The complete species + form showdown ID of the Pokemon.
 * @param megaFeature The name of the StringSpeciesFeature to use.
 * @param megaAspect The value to grant the feature.
 *
 * @see FormData#showdownId()
 */
public record MegaEvolution (String speciesFormId, String megaFeature, String megaAspect) {
    public static final MegaEvolution DEFAULT = MegaEvolution.create("none");

    public static final Codec<MegaEvolution> CODEC = RecordCodecBuilder.create(instance -> instance.group(
        Codec.STRING.fieldOf("speciesFormId").forGetter(MegaEvolution::speciesFormId),
        Codec.STRING.optionalFieldOf("megaFeature", "mega_evolution").forGetter(MegaEvolution::megaFeature),
        Codec.STRING.optionalFieldOf("megaAspect", "mega").forGetter(MegaEvolution::megaAspect)
    ).apply(instance, MegaEvolution::new));

    public static final PacketCodec<RegistryByteBuf, MegaEvolution> PACKET_CODEC = PacketCodec.tuple(
        PacketCodecs.STRING,
        MegaEvolution::speciesFormId,
        PacketCodecs.STRING,
        MegaEvolution::megaFeature,
        PacketCodecs.STRING,
        MegaEvolution::megaAspect,
        MegaEvolution::new
    );

    public static MegaEvolution create (String speciesFormId) {
        return new MegaEvolution(speciesFormId, "mega_evolution", "mega");
    }

    public static MegaEvolution create (String speciesFormId, String aspect) {
        return new MegaEvolution(speciesFormId, "mega_evolution", aspect);
    }

    public boolean canApplyTo (Pokemon pokemon) {
        return pokemon.getForm().showdownId().equalsIgnoreCase(this.speciesFormId);
    }

    public void apply (Pokemon pokemon) {
        new StringSpeciesFeature(this.megaFeature, this.megaAspect);
    }

    public void remove (Pokemon pokemon) {
        pokemon.getFeatures().removeIf(feature -> feature.getName().equalsIgnoreCase(this.megaFeature));
    }
}
