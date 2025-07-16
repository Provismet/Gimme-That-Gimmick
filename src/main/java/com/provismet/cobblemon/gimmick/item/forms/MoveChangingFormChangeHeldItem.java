package com.provismet.cobblemon.gimmick.item.forms;

import com.cobblemon.mod.common.api.moves.BenchedMove;
import com.cobblemon.mod.common.api.moves.Move;
import com.cobblemon.mod.common.api.moves.MoveTemplate;
import com.cobblemon.mod.common.api.moves.Moves;
import com.cobblemon.mod.common.api.properties.CustomPokemonProperty;
import com.cobblemon.mod.common.pokemon.Pokemon;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.item.Item;
import net.minecraft.util.Identifier;

import java.util.List;

public class MoveChangingFormChangeHeldItem extends GenericFormChangeHeldItem {
    private final List<String> movesGainedOnGive;
    private final List<String> movesLostOnGive;
    private final List<String> movesGainedOnTake;
    private final List<String> movesLostOnTake;

    public MoveChangingFormChangeHeldItem (
        Settings settings,
        Item baseVanillaItem,
        PolymerModelData modelData,
        int tooltipLines,
        Identifier species,
        CustomPokemonProperty apply,
        CustomPokemonProperty remove,
        List<String> movesGainedOnGive,
        List<String> movesLostOnGive,
        List<String> movesGainedOnTake,
        List<String> movesLostOnTake
    ) {
        super(settings, baseVanillaItem, modelData, tooltipLines, species, apply, remove);
        this.movesGainedOnGive = movesGainedOnGive;
        this.movesLostOnGive = movesLostOnGive;
        this.movesGainedOnTake = movesGainedOnTake;
        this.movesLostOnTake = movesLostOnTake;
    }

    @Override
    public void giveToPokemon (Pokemon pokemon) {
        super.giveToPokemon(pokemon);
        this.swapMoves(pokemon, this.movesGainedOnGive, this.movesLostOnGive);
    }

    @Override
    public void removeFromPokemon (Pokemon pokemon) {
        super.removeFromPokemon(pokemon);
        this.swapMoves(pokemon, this.movesGainedOnTake, this.movesLostOnTake);
    }

    private void swapMoves (Pokemon pokemon, List<String> gainedMoves, List<String> lostMoves) {
        for (String move : lostMoves) {
            MoveTemplate template = Moves.INSTANCE.getByName(move);
            if (template != null) {
                for (int i = 0; i < pokemon.getMoveSet().getMovesWithNulls().size(); ++i) {
                    Move currentMove = pokemon.getMoveSet().get(i);
                    if (currentMove != null && currentMove.getTemplate().getName().equals(template.getName())) {
                        pokemon.getMoveSet().setMove(i, null);
                    }
                }

                BenchedMove removeThis = null;
                for (BenchedMove benchedMove : pokemon.getBenchedMoves()) {
                    if (benchedMove.getMoveTemplate().getName().equals(template.getName())) {
                        removeThis = benchedMove;
                        break;
                    }
                }

                if (removeThis != null) pokemon.getBenchedMoves().remove(removeThis);
            }
        }

        for (String move : gainedMoves) {
            MoveTemplate template = Moves.INSTANCE.getByName(move);
            if (template != null) {
                if (pokemon.getMoveSet().hasSpace()) pokemon.getMoveSet().add(template.create());
                else pokemon.getBenchedMoves().add(new BenchedMove(template, 0));
            }
        }
    }
}
