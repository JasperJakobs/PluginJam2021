package nl.jasperjakobs.pluginjam.mob;

import org.bukkit.entity.Entity;
import org.bukkit.entity.EntityType;

public enum MobTypes {

    PLAYER,

    /*
                PASSIVE MOBS
     */
    AXOLOTL,
    BAT,
    MOOSHROOM, BROWN_MOOSHROOM, //
    CHICKEN,
    TROPICAL_FISH, COD, SALMON, PUFFERFISH,
    COW,
    HORSE, MULE, DONKEY, SKELETON_HORSE, ZOMBIE_HORSE,
    PARROT,
    SQUID, GLOW_SQUID,
    RABBIT,
    SHEEP,
    SNOW_GOLEM,
    STRIDER,

    /*
                NEUTRAL MOBS
     */
    BEE,
    SPIDER, CAVE_SPIDER,
    DOLPHIN,
    ENDERMAN,
    GOAT,
    IRON_GOLEM,
    LLAMA, TRADER_LLAMA,

    /*
                HOSTILE MOBS
     */
    BLAZE,
    CREEPER,
    DROWNED,
    ELDER_GUARDIAN,
    GHAST,
    GUARDIAN,
    ZOMBIE, HUSK, ZOMBIE_VILLAGER,
    PHANTOM,
    SKELETON, WITHER_SKELETON,

    /*
                BOSS MOBS
     */
    ENDER_DRAGON,
    WITHER;


    public Boolean canFly() {
        return switch (this) {
            case BAT, BEE, PARROT, PHANTOM, BLAZE, ENDER_DRAGON, WITHER, GHAST -> true;
            default -> false;
        };
    }

    public Boolean shouldBurn() {
        return switch (this) {
            case ZOMBIE, SKELETON, PHANTOM, HUSK, ZOMBIE_VILLAGER, WITHER_SKELETON -> true;
            default -> false;
        };
    }

    public Boolean revertDrown() {
        return switch (this) {
            case AXOLOTL, SQUID, GLOW_SQUID, TROPICAL_FISH, COD, SALMON, PUFFERFISH, GUARDIAN, ELDER_GUARDIAN, DROWNED -> true;
            default -> false;
        };
    }

    public Boolean milkable() {
        return switch (this) {
            case COW, MOOSHROOM, BROWN_MOOSHROOM, GOAT -> true;
            default -> false;
        };
    }

    public Boolean shouldWalkFast() {
        return switch (this) {
            case HORSE, MULE, DONKEY, SKELETON_HORSE, ZOMBIE_HORSE -> true;
            default -> false;
        };
    }

    public Boolean shouldWalkSlow() {
        return switch (this) {
            case DOLPHIN -> true;
            default -> false;
        };
    }

    public Boolean shouldJumpHigher() {
        return switch (this) {
            case HORSE, MULE, DONKEY, SKELETON_HORSE, ZOMBIE_HORSE, GOAT, RABBIT -> true;
            default -> false;
        };
    }

    public Boolean shouldSwimFast() {
        return switch (this) {
            case DOLPHIN, AXOLOTL, TROPICAL_FISH, COD, SALMON, PUFFERFISH -> true;
            default -> false;
        };
    }

    public Boolean canSpit() {
        return switch (this) {
            case LLAMA -> true;
            default -> false;
        };
    }

    public Boolean canWalkUpWalls() {
        return switch (this) {
            case SPIDER, CAVE_SPIDER -> true;
            default -> false;
        };
    }

    public String getEntityNameString() {
        return switch (this) {
            case PLAYER -> "Player";

            case AXOLOTL -> "Axolotl";
            case BAT -> "Bat";
            case MOOSHROOM -> "Mooshroom";
            case BROWN_MOOSHROOM -> "Brown Mooshroom";
            case CHICKEN -> "Chicken";
            case TROPICAL_FISH -> "Tropical Fish";
            case COD -> "Cod";
            case SALMON -> "Salmon";
            case PUFFERFISH -> "Pufferfish";
            case COW -> "Cow";
            case HORSE -> "Horse";
            case MULE -> "Mule";
            case DONKEY -> "Donkey";
            case SKELETON_HORSE -> "Skeleton Horse";
            case ZOMBIE_HORSE -> "Zombie Horse";
            case PARROT -> "Parrot";
            case SQUID -> "Squid";
            case GLOW_SQUID -> "Glow Squid";
            case RABBIT -> "Rabbit";
            case SHEEP -> "Sheep";
            case SNOW_GOLEM -> "Snow Golem";
            case STRIDER -> "Strider";

            case BEE -> "Bee";
            case SPIDER -> "Spider";
            case CAVE_SPIDER -> "Cave Spider";
            case DOLPHIN -> "Dolphin";
            case ENDERMAN -> "Enderman";
            case GOAT -> "Goat";
            case IRON_GOLEM -> "Iron Golem";
            case LLAMA -> "Llama";
            case TRADER_LLAMA -> "Trader Llama";

            case BLAZE -> "Blaze";
            case CREEPER -> "Creeper";
            case DROWNED -> "Drowner";
            case ELDER_GUARDIAN -> "Elder Guardian";
            case GHAST -> "Ghast";
            case GUARDIAN -> "Guardian";
            case ZOMBIE -> "Zombie";
            case HUSK -> "Husk";
            case ZOMBIE_VILLAGER -> "Zombie Villager";
            case PHANTOM -> "Phantom";
            case SKELETON -> "Skeleton";
            case WITHER_SKELETON -> "Wither Skeleton";

            case ENDER_DRAGON -> "Ender Dragon";
            case WITHER -> "Wither";
        };
    }
}
