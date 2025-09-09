/*
 * Copyright (C) 2023 Cobblemon Contributors
 *
 * This Source Code Form is subject to the terms of the Mozilla Public
 * License, v. 2.0. If a copy of the MPL was not distributed with this
 * file, You can obtain one at https://mozilla.org/MPL/2.0/.
 */

/*-----------------------------------------------------------------------------------------------------------------
NOTE: The functions in this file are used by GraalShowdownService, the default Showdown environment for Cobblemon.
For the corresponding SocketShowdownService methods, see cobbled-debug-server.ts. For where the interface is 
defined and configured, See ShowdownService.kt on the  main Cobblemon repo .
-----------------------------------------------------------------------------------------------------------------*/

// eslint-disable-next-line strict
const BS = require('./sim/battle-stream');
const Cobblemon = require('./sim/cobblemon/cobblemon').Cobblemon
const Dex = require('./sim/dex').Dex;

const battleMap = new Map();
const toID = Dex.toID;

const moves = require("./data/moves");
const abilities = require("./data/abilities");

function startBattle(graalShowdown, battleId, requestMessages) {
    const battleStream = new BS.BattleStream();
    battleMap.set(battleId, battleStream);

    // Join messages with new line
    try {
        for (const element of requestMessages) {
            battleStream.write(element);
        }
    } catch (err) {
        graalShowdown.log(err.stack);
    }

    // Any battle output then gets written to the execution helper logging mechanism
    (async () => {
        for await (const output of battleStream) {
            graalShowdown.sendFromShowdown(battleId, output);
        }
    })();
}

function sendBattleMessage(battleId, messages) {
    const battleStream = battleMap.get(battleId);
    for (const element of messages) {
        battleStream.write(element);
    }
}

function getTypeChart() {
    return JSON.stringify(Dex.data.TypeChart);
}

function resetData(type) {
    const registry = Cobblemon.getRegistry(type);
    registry.reset();
}

function resetAll() {
    for (const key of Cobblemon.registryKeys) {
        Cobblemon.registries[key].reset();
    }
}

function receiveData(data, type) {
  const registry = Cobblemon.getRegistry(type);
  const obj = () => { 
    try {
        // at the moment we only (re)serialize Species on the mod side, but prefer neat JSON first
        return JSON.parse(data);
    } catch {
        // loose unstructured JS objects with embedded functions and stuff (abilities, moves, bag items, etc.)
        return eval(`(${data})`);
    }}
  for (const [key, value] of Object.entries(obj())) {
    registry.register(value, toID(key));
  }
  registry.invalidate();
}

function receiveEntry(data, type) {
    const registry = Cobblemon.getRegistry(type);
    registry.register(data, toID(key));
    registry.invalidate();
}

function invalidate(type) {
    const registry = Cobblemon.getRegistry(type);
    registry.invalidate();
}

function getData(type) {
    const registry = Cobblemon.getRegistry(type);
    return JSON.stringify(registry.all());
}

function afterSpeciesInit() {
    Dex.modsLoaded = false;
    Dex.includeMods();
}

moves.Moves["terablast"] = {
  num: 851,
  accuracy: 100,
  basePower: 80,
  basePowerCallback(pokemon, target, move) {
    if (pokemon.terastallized === "Stellar") {
      return 100;
    }
    return move.basePower;
  },
  category: "Special",
  name: "Tera Blast",
  pp: 10,
  priority: 0,
  flags: { protect: 1, mirror: 1, metronome: 1, mustpressure: 1 },
  onPrepareHit(target, source, move) {
    if (source.terastallized) {
      this.attrLastMove("[anim] Tera Blast " + source.teraType);
    }
  },
  onModifyType(move, pokemon, target) {
    if (pokemon.terastallized) {
      move.type =
        pokemon.teraType.charAt(0).toUpperCase() + pokemon.teraType.slice(1);
    }
  },
  onModifyMove(move, pokemon) {
    if (
      pokemon.terastallized &&
      pokemon.getStat("atk", false, true) > pokemon.getStat("spa", false, true)
    ) {
      move.category = "Physical";
    }
    if (pokemon.terastallized === "Stellar") {
      move.self = { boosts: { atk: -1, spa: -1 } };
    }
  },
  secondary: null,
  target: "normal",
  type: "Normal",
};

abilities.Abilities["battlebond"] = {
  onSourceAfterFaint(length, target, source, effect) {
    if (effect?.effectType !== "Move")
      return;
    if (source.abilityState.battleBondTriggered)
      return;
    if (source.species.id === "greninjabond" && source.hp && !source.transformed && source.side.foePokemonLeft()) {
      this.boost({ atk: 1, spa: 1, spe: 1 }, source, source, this.effect);
      source.formeChange("Greninja-Ash")
      source.abilityState.battleBondTriggered = true;
    }
  },
  flags: { failroleplay: 1, noreceiver: 1, noentrain: 1, notrace: 1, failskillswap: 1, cantsuppress: 1 },
  name: "Battle Bond",
  rating: 3.5,
  num: 210
};