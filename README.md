# Gimme That Gimmick
Gimme That Gimmick is a fully server-sided mod that implements generational gimmicks and related items for Cobblemon.  
Note that the items are only implemented, GTG does not make any items obtainable through gameplay nor does it create the associated visuals.

Gimme That Gimmick made specifically for Fabric servers and ports functionality from [Mega Showdown](https://github.com/yajatkaul/Mega_Showdown)
where necessary. If playing on single-player, a smaller private server, or on NeoForge, you should use
[Mega Showdown](https://github.com/yajatkaul/Mega_Showdown) instead for a complete and fully-integrated experience.

## Attribution
Gimme That Gimmick relies on art assets, Showdown scripts, and mixins provided by [Mega Showdown](https://github.com/yajatkaul/Mega_Showdown).  
Please support the original as Gimme That Gimmick would not exist without their efforts.

### Resource Authors from Mega Showdown
- YajatKaul
  - Showdown scripts
  - Mixins
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
