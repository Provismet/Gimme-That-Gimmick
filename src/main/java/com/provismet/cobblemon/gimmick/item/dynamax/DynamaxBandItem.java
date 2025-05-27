package com.provismet.cobblemon.gimmick.item.dynamax;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import com.provismet.cobblemon.gimmick.item.PolymerHeldItem;
import eu.pb4.polymer.resourcepack.api.PolymerModelData;
import net.minecraft.entity.LivingEntity;
import net.minecraft.entity.player.PlayerEntity;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.text.Text;
import net.minecraft.util.ActionResult;
import net.minecraft.util.Hand;

public class DynamaxBandItem extends PolymerHeldItem {
    public DynamaxBandItem (Settings settings, Item baseVanillaItem, PolymerModelData modelData) {
        super(settings, baseVanillaItem, modelData, 1);
    }

    @Override
    public ActionResult useOnEntity (ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof PokemonEntity pokemonEntity) {
            user.sendMessage(
                Text.translatable(
                    "message.overlay.gimmethatgimmick.dynamax",
                    pokemonEntity.getPokemon().getDmaxLevel(),
                    pokemonEntity.getPokemon().getGmaxFactor() ? Text.translatable("message.overlay.gimmethatgimmick.yes") : Text.translatable("message.overlay.gimmethatgimmick.no")
                ),
                true
            );
            return ActionResult.SUCCESS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }
}
