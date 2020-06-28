/*
 * Decompiled with CFR 0.145.
 * 
 * Could not load the following classes:
 *  org.bukkit.World
 *  org.bukkit.generator.ChunkGenerator
 *  org.bukkit.generator.ChunkGenerator$BiomeGrid
 *  org.bukkit.generator.ChunkGenerator$ChunkData
 */
package cz._heropwp.playerworldspro.CoreManagers;

import java.util.Random;
import org.bukkit.World;
import org.bukkit.generator.ChunkGenerator;

public class EmptyChunkGenerator
extends ChunkGenerator {
    public ChunkGenerator.ChunkData generateChunkData(World world, Random random, int n, int n2, ChunkGenerator.BiomeGrid biomeGrid) {
        return this.createChunkData(world);
    }
}

