package com.provismet.cobblemon.gimmick.mixin;

import com.cobblemon.mod.common.entity.pokemon.PokemonEntity;
import net.minecraft.entity.EntityType;
import net.minecraft.entity.passive.AnimalEntity;
import net.minecraft.entity.passive.TameableEntity;
import net.minecraft.scoreboard.Team;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfoReturnable;

// This fixes the glow color issue
@Mixin(TameableEntity.class)
public abstract class TamableEntityMixin extends AnimalEntity {
    protected TamableEntityMixin(EntityType<? extends AnimalEntity> entityType, World level) {
        super(entityType, level);
    }

    @Inject(method = "getScoreboardTeam", at = @At("HEAD"), cancellable = true)
    private void getTeamInject(CallbackInfoReturnable<Team> cir) {
        TameableEntity tamableAnimal = (TameableEntity) (Object) this;

        if (tamableAnimal instanceof PokemonEntity) {
            cir.setReturnValue(super.getScoreboardTeam());
            cir.cancel();
        }
    }
}