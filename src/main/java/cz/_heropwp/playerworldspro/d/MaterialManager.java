/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.Material
 */
package cz._heropwp.playerworldspro.d;

import org.bukkit.Material;

public class MaterialManager {
    private final boolean a = Material.getMaterial((String)"RED_WOOL") != null;
    private final String b = "1752137859";

    private String b(a a2) {
        switch (a2) {
            case OAK_SAPLING: {
                return "SAPLING";
            }
            case PLAYER_HEAD: {
                return "SKULL_ITEM";
            }
            case COMMAND_BLOCK: {
                return "COMMAND";
            }
            case CLOCK: {
                return "WATCH";
            }
            case RED_BED: {
                return "BED";
            }
            case WRITABLE_BOOK: {
                return "BOOK_AND_QUILL";
            }
            case WOODEN_SWORD: {
                return "WOOD_SWORD";
            }
            case DEAD_BUSH: {
                return "DOUBLE_PLANT";
            }
        }
        return "BEDROCK";
    }

    public Material a(a a2) {
        if (this.a) {
            return Material.getMaterial((String)String.valueOf((Object)a2));
        }
        return Material.getMaterial((String)this.b(a2));
    }

    public static enum a {
        OAK_SAPLING,
        PLAYER_HEAD,
        COMMAND_BLOCK,
        CLOCK,
        RED_BED,
        WRITABLE_BOOK,
        WOODEN_SWORD,
        DEAD_BUSH;
        
    }

}

