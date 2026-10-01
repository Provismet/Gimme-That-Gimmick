"use strict";
var __defProp = Object.defineProperty;
var __getOwnPropDesc = Object.getOwnPropertyDescriptor;
var __getOwnPropNames = Object.getOwnPropertyNames;
var __hasOwnProp = Object.prototype.hasOwnProperty;
var __export = (target, all) => {
  for (var name in all)
    __defProp(target, name, { get: all[name], enumerable: true });
};
var __copyProps = (to, from, except, desc) => {
  if (from && typeof from === "object" || typeof from === "function") {
    for (let key of __getOwnPropNames(from))
      if (!__hasOwnProp.call(to, key) && key !== except)
        __defProp(to, key, { get: () => from[key], enumerable: !(desc = __getOwnPropDesc(from, key)) || desc.enumerable });
  }
  return to;
};
var __toCommonJS = (mod) => __copyProps(__defProp({}, "__esModule", { value: true }), mod);
var conditions_exports = {};
__export(conditions_exports, {
  Conditions: () => Conditions
});
module.exports = __toCommonJS(conditions_exports);
const Conditions = {
  alphaboost: {
    name: "alphaboost",
    onStart(target, source, sourceEffect) {
      const stats = ["atk", "def", "spa", "spd", "spe"];
      const boost = { atk: 0, def: 0, spa: 0, spd: 0, spe: 0 };
      const pool = stats.slice();
      const count = Math.floor(target.level / 10) + 1;
      for (let i = 0; i < count; i++) {
        if (!pool.length)
          break;
        const idx = this.random(pool.length);
        const stat = pool[idx];
        boost[stat] = (boost[stat] ?? 0) + 1;
        if ((boost[stat] ?? 0) >= 6) {
          pool[idx] = pool[pool.length - 1];
          pool.pop();
        }
      }
      let boostName;
      for (boostName in boost) {
        target.alphaBoosts[boostName] = boost[boostName];
      }
      this.add("-start", target, "alphaboost");
    }
  }
};
//# sourceMappingURL=conditions.js.map
