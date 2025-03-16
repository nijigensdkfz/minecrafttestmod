package com.iza.milienhance.block;

import com.iza.milienhance.Milienhance;
import net.minecraft.block.*;
import net.minecraft.block.enums.NoteBlockInstrument;
import net.minecraft.block.AbstractBlock.Settings;
import net.minecraft.block.piston.PistonBehavior;
import net.minecraft.registry.Registries;
import net.minecraft.registry.Registry;
import net.minecraft.registry.RegistryKey;
import net.minecraft.registry.RegistryKeys;
import net.minecraft.sound.BlockSoundGroup;
import net.minecraft.state.property.Properties;
import net.minecraft.util.Identifier;

import java.util.function.Function;
import java.util.function.ToIntFunction;


public class ModBlocks {
    private static Block register(String id, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        return register(keyOf(id), factory, settings);
    }
    private static Block register(String id, AbstractBlock.Settings settings) {
        return register(id, Block::new, settings);
    }
    public static Block register(RegistryKey<Block> key, Function<AbstractBlock.Settings, Block> factory, AbstractBlock.Settings settings) {
        Block block = (Block)factory.apply(settings.registryKey(key));
        return (Block)Registry.register(Registries.BLOCK, key, block);
    }
    private static RegistryKey<Block> keyOf(String id) {
        return RegistryKey.of(RegistryKeys.BLOCK, Identifier.of(Milienhance.MOD_ID,id));
    }

    public static ToIntFunction<BlockState> createLightLevelFromLitBlockState(int litLevel) {
        return (state) -> {
            return (Boolean)state.get(Properties.LIT) ? litLevel : 0;
        };
    }

    public static final Block ANTI_BOMB_BRICKS = register("anti_bomb_bricks", Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL).allowsSpawning(Blocks::never));
    public static final Block ANTI_BOMB_PILLAR = register((String)"anti_bomb_pillar",PillarBlock::new,Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL));
    public static final Block ANTI_BOMB_GLASS = register("anti_bomb_glass", TransparentBlock::new, Settings.create().instrument(NoteBlockInstrument.HAT).strength(0.5F,1200.0F).sounds(BlockSoundGroup.GLASS).nonOpaque().allowsSpawning(Blocks::never).solidBlock(Blocks::never).suffocates(Blocks::never).blockVision(Blocks::never));
    public static final Block ANTI_BOMB_GLASS_PANE = register((String)"anti_bomb_glass_pane", PaneBlock::new, Settings.create().instrument(NoteBlockInstrument.HAT).strength(0.5F,1200.0F).sounds(BlockSoundGroup.GLASS).nonOpaque());
    public static final Block ANTI_BOMB_STAIRS = register("anti_bomb_stairs", (settings) -> {
        return new StairsBlock(ANTI_BOMB_BRICKS.getDefaultState(), settings);
    }, Settings.copyShallow(ANTI_BOMB_BRICKS));
    public static final Block ANTI_BOMB_WALL = register((String)"anti_bomb_wall", WallBlock::new, Settings.copyShallow(ANTI_BOMB_BRICKS).solid());
    public static final Block ANTI_BOMB_SLAB = register((String)"anti_bomb_slab", SlabBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F, 1200.0F));
    public static final Block ANTI_BOMB_TRAPDOOR = register((String)"anti_bomb_trapdoor", (settings) -> {
        return new TrapdoorBlock(BlockSetType.IRON, settings);
    },Settings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F, 1200.0F).nonOpaque().allowsSpawning(Blocks::never));
    public static final Block ANTI_BOMB_DOOR = register((String)"anti_bomb_door", (settings) -> {
        return new DoorBlock(BlockSetType.IRON, settings);
    },Settings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F, 1200.0F).nonOpaque().allowsSpawning(Blocks::never).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block ANTI_BOMB_LAMP = register("anti_bomb_lamp", Block::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.HAT).strength(0.5F,1200F).sounds(BlockSoundGroup.GLASS).luminance((state) -> {return 15;}).solidBlock(Blocks::never));
    public static final Block ANTI_BOMB_BUTTON = register((String)"anti_bomb_button", (settings) -> {
        return new ButtonBlock(BlockSetType.STONE, 20, settings);
    }, Settings.create().noCollision().strength(0.5F).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block ANTI_BOMB_PRESSURE_PLATE = register((String)"anti_bomb_pressure_plate",  (settings) -> {
        return new PressurePlateBlock(BlockSetType.STONE, settings);
    }, Settings.create().mapColor(MapColor.IRON_GRAY).solid().instrument(NoteBlockInstrument.IRON_XYLOPHONE).noCollision().strength(0.5F,1200.0F).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block ANTI_BOMB_LEVER = register((String)"anti_bomb_lever", LeverBlock::new, Settings.create().noCollision().strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL).pistonBehavior(PistonBehavior.DESTROY));
    public static final Block ANTI_BOMB_LADDER = register((String)"anti_bomb_ladder", LadderBlock::new, Settings.create().notSolid().strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL).nonOpaque().allowsSpawning(Blocks::never).pistonBehavior(PistonBehavior.DESTROY));

    public static final Block ANTI_BOMB_ROD = register((String)"anti_bomb_rod", ABRodBlock::new, Settings.create().notSolid().breakInstantly().luminance((state) -> {
        return 15;
    }).sounds(BlockSoundGroup.METAL).nonOpaque());
    public static final Block ANTI_BOMB_LANTERN = register((String)"anti_bomb_lantern", LanternBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).solid().requiresTool().strength(3.5F,1200F).sounds(BlockSoundGroup.LANTERN).luminance((state) -> {
        return 15;
    }).nonOpaque().pistonBehavior(PistonBehavior.DESTROY));
    public static final Block STEEL_COIL = register((String)"steel_coil",IngotCoilBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block GOLD_BARS = register((String)"gold_bars",IngotCoilBlock::new, Settings.create().mapColor(MapColor.GOLD).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,6.0F).sounds(BlockSoundGroup.METAL));
    public static final Block COPPER_COIL = register((String)"copper_coil",IngotCoilBlock::new, Settings.create().mapColor(MapColor.ORANGE).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,6.0F).sounds(BlockSoundGroup.METAL));

    public static final Block IRON_CHEST = register((String)"iron_chest", IronChestBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).nonOpaque().sounds(BlockSoundGroup.METAL));
    public static final Block PLANT_POT = register((String)"plant_pot", PlantPotBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).luminance((state) -> {
        return 15;
    }).nonOpaque().sounds(BlockSoundGroup.METAL));
    public static final Block CHIMERA = register((String)"chimera", ChimeraBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).nonOpaque().sounds(BlockSoundGroup.METAL));
    public static final Block MAINTAINER = register((String)"maintainer", MaintainBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).nonOpaque().sounds(BlockSoundGroup.METAL));
    public static final Block GRIND_OBSIDIAN = register((String)"grind_obsidian", GrindObsidianBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F, 1200.0F).sounds(BlockSoundGroup.STONE).pistonBehavior(PistonBehavior.BLOCK));
    public static final Block MINER = register((String)"miner", MinerBlock::new, Settings.create().mapColor(MapColor.IRON_GRAY).strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL));
    public static final Block BROKEN_MINER = register("broken_miner", Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL).allowsSpawning(Blocks::never));
    public static final Block DOOMED_MINER = register("doomed_miner", Settings.create().mapColor(MapColor.IRON_GRAY).instrument(NoteBlockInstrument.IRON_XYLOPHONE).strength(0.5F,1200.0F).sounds(BlockSoundGroup.METAL).allowsSpawning(Blocks::never));

    public static void initialize(){}
}
