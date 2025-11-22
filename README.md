<div align="center">

# Gimme That Gimmick
[![](https://img.shields.io/jitpack/version/com.github.Provismet/gimme-that-gimmick?style=flat-square&logo=jitpack&color=F6F6F6)](https://jitpack.io/#Provismet/gimme-that-gimmick)
[![](https://img.shields.io/modrinth/dt/GsFuo2CX?style=flat-square&logo=modrinth&color=F6F6F6)](https://modrinth.com/mod/gimme-that-gimmick)
[![](https://img.shields.io/curseforge/dt/1282018?style=flat-square&logo=curseforge&color=F6F6F6)](https://www.curseforge.com/minecraft/mc-mods/gimme-that-gimmick)

</div>

Gimme That Gimmick is a fully server-sided mod that implements generational gimmicks, related items, and data-driven form changes for Cobblemon.  
Note that the items are only implemented, GTG does not make any items obtainable through gameplay nor does it create the associated visuals.

Gimme That Gimmick made specifically for Fabric servers and ports functionality from [Mega Showdown](https://github.com/yajatkaul/Mega_Showdown)
where necessary. If playing on single-player, a smaller private server, or on NeoForge, you should use
[Mega Showdown](https://github.com/yajatkaul/Mega_Showdown) instead for a complete and fully-integrated experience.

## Attribution
Gimme That Gimmick relies on art assets, Showdown scripts, and mixins provided by [Mega Showdown](https://github.com/yajatkaul/Mega_Showdown).  
Please support the original as Gimme That Gimmick would not exist without their efforts.

### Resource Authors from Mega Showdown
- YajatKaul
  - Mixins
- Treynami
  - Showdown scripts
- Sanji
  - Mega Stones assets
  - Z-Crystal assets
  - Tera Shard assets
  - Dynamax Band asset
  - Max Candy asset
- Blue
  - Mega Bracelet asset
  - Z-Ring asset
  - Tera Orb asset
  - Max Mushroom asset
- Beibeihulis
  - Max Soup asset 

## Items
### Key Items
- Mega Bracelets, Z-Rings, Dynamax Bands, and Tera Orbs are added as polymer items.
- Four enchantments: Key Stone, Z-Power, Dynamax, and Terastal allow enchanted items to act as key items.
- Four data-components corresponding to the gimmicks also exist, allowing applied items to act as key items.

### Mixin Convenience
Mega Stones, Z-Crystals for types, and Z-Crystal for species are all given dedicated classes for convenience.  
Dependent mods can mixin into these classes to apply custom functionality as required.

### Tera Shards
Tera shards are used in groups of 50 and change the tera type of a Pokemon. The type can be checked by using a Tera Orb on the Pokemon.

### Forme Changes
Unlike what the comments in the official 1.7 content creator preview would have you believe, Gimme That Gimmick _does_ have comprehensive
and fully data-driven forme changes in battle. Check the [wiki](https://github.com/Provismet/Gimme-That-Gimmick/wiki/BattleForm) for a comprehensive guide.
