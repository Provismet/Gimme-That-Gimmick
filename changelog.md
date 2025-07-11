Large update to include an entire suite of form change mechanisms to the mod. The highlight of this update is a
fully data-driven feature to allow servers and addons to create their own effects, items, and form changes.

When using the new data components to create custom items, it is recommended to use Filament (or addons) as this will grant those items a proper id.
However, users may create their items entirely though GTG's data-driven system instead if they prefer.

## Additions
- Arceus plates
- Ogerpon masks
- Silvally memories
- Genesect drives
- Zygarde Cube
- Deoxys Meteorite
  - Sneak to place the block.
  - Both the block and the item can be used to open the form change menu.
- Rotom Catalog
- Gracidea Flowers
- Fusion mechanics for Calyrex, Kyurem, and Necrozma.
- Added data-driven systems.
  - In-battle form changes.
  - Fusion items.
  - Form changing items.
  - Mega Stones.
  - Special Effects
    - Particles
    - Animations
    - Sounds
  - Held items.
  - All of the above come with data generators.
- Dynamax Level now appears as a progress bar in the summary.
- Max Mushrooms are now growable crops.
- Added basic glowing effect for Z-Moves, Tera, and Dynamax.
- Added size changing for Dynamax.
- Out of combat mega evolution.

### Data Components
New data components have been added to grant more power to servers.

`gimme-that-gimmick:showdown_id`  
Allows any item to be used as a held item matching the given id.  
Note that certain items (such as bones and snowballs) natively have Showdown ids and will bypass this feature.

`gimme-that-gimmick:mega_evolution`  
Adds mega evolution data to an item. Outside of battle, this is used to determine if the mega stone matches the Pokémon holding it.
Both in and out of battle, this determines what aspect is applied to the Pokémon when mega evolving and when mega devolving.

`gimme-that-gimmick:data_item`  
Used exclusively by the new technical items `gimme-that-gimmick:data_driven_toggle` and `gimme-that-gimmick:data_driven_fusion`.
This component describes how the client representation of the item should appear. Serverside the item ids are as aforementioned,
but clientside they are as described by this component.

`gimme-that-gimmick:fusion`  
Used exclusively by the `gimme-that-gimmick:data_driven_fusion` item. This component describes the procedure for fusing two Pokémon.

`gimme-that-gimmick:form_toggle`  
Used exclusively by the `gimme-that-gimmick:data_driven_toggle` item. This component describes the procedure for toggling a Pokémon between two forms.

## Bugfixes
- Z-Crystals now change Arceus' type.
- Regional forms can no longer use megastones belonging to a different form.