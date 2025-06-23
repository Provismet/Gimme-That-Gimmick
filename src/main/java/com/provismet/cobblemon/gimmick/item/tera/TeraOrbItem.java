package com.provismet.cobblemon.gimmick.item.tera;

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

public class TeraOrbItem extends PolymerHeldItem {
    public TeraOrbItem (Item.Settings settings, Item polymerItem, PolymerModelData modelData) {
        super(settings, polymerItem, modelData, 1);
    }

    @Override
    public ActionResult useOnEntity (ItemStack stack, PlayerEntity user, LivingEntity entity, Hand hand) {
        if (entity instanceof PokemonEntity pokemonEntity) {
            user.sendMessage(Text.translatable("message.overlay.gimmethatgimmick.tera", pokemonEntity.getPokemon().getTeraType().getDisplayName()), true);
            return ActionResult.SUCCESS;
        }

        return super.useOnEntity(stack, user, entity, hand);
    }
}
