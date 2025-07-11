package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.properties.CustomPokemonProperty;
import com.cobblemon.mod.common.pokemon.Pokemon;
import com.provismet.cobblemon.gimmick.api.data.registry.EffectsData;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.registry.Registries;
import net.minecraft.util.Identifier;

public class GenericFormChangeHeldItem extends PolymerHeldItem {
    private final Identifier species;
    private final CustomPokemonProperty apply;
    private final CustomPokemonProperty remove;

    public GenericFormChangeHeldItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData, int tooltipLines, Identifier species, CustomPokemonProperty apply, CustomPokemonProperty remove) {
        super(settings, baseVanillaItem, modelData, tooltipLines);
        this.species = species;
        this.apply = apply;
        this.remove = remove;
    }

    public void giveToPokemon (Pokemon pokemon) {
        if (pokemon.getSpecies().getResourceIdentifier().equals(this.species)) {
            this.apply.apply(pokemon);
            pokemon.updateAspects();

            if (pokemon.getEntity() != null) {
                EffectsData.run(pokemon.getEntity(), Registries.ITEM.getId(this).withSuffixedPath("_apply"));
            }
        }
    }

    public void removeFromPokemon (Pokemon pokemon) {
        if (pokemon.getSpecies().getResourceIdentifier().equals(this.species)) {
            this.remove.apply(pokemon);
            pokemon.updateAspects();

            if (pokemon.getEntity() != null) {
                EffectsData.run(pokemon.getEntity(), Registries.ITEM.getId(this).withSuffixedPath("_remove"));
            }
        }
    }
}
