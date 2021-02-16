package ghana7.extracoals;

import ghana7.extracoals.torches.*;
import net.minecraft.block.Block;
import net.minecraft.client.renderer.RenderType;
import net.minecraft.client.renderer.RenderTypeLookup;
import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraft.item.WallOrFloorItem;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.event.lifecycle.FMLClientSetupEvent;
import net.minecraftforge.fml.javafmlmod.FMLJavaModLoadingContext;
import net.minecraftforge.registries.DeferredRegister;
import net.minecraftforge.registries.ForgeRegistries;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.util.ArrayList;
import java.util.stream.Collectors;

// The value here should match an entry in the META-INF/mods.toml file
@Mod(ExtraCoals.MODID)
public class ExtraCoals
{
    // Directly reference a log4j logger.
    public static final Logger LOGGER = LogManager.getLogger();
    public static final String MODID = "extracoals";

    private static final DeferredRegister<Item> ITEMS = DeferredRegister.create(ForgeRegistries.ITEMS, MODID);
    private static final DeferredRegister<Block> BLOCKS = DeferredRegister.create(ForgeRegistries.BLOCKS, MODID);

    public static final RegistryObject<Item> DIAMOND_COAL = ITEMS.register("diamond_coal", () ->
            new Item(new Item.Properties()
            .group(ItemGroup.MATERIALS)
            .maxStackSize(64))
    );
    public static final RegistryObject<Item> EMERALD_COAL = ITEMS.register("emerald_coal", () ->
            new Item(new Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64))
    );

    public static final RegistryObject<Item> GOLD_COAL = ITEMS.register("gold_coal", () ->
            new Item(new Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64))
    );

    public static final RegistryObject<Item> IRON_COAL = ITEMS.register("iron_coal", () ->
            new Item(new Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64))
    );

    public static final RegistryObject<Item> LAPIS_COAL = ITEMS.register("lapis_coal", () ->
            new Item(new Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64))
    );

    public static final RegistryObject<Item> REDSTONE_COAL = ITEMS.register("redstone_coal", () ->
            new Item(new Item.Properties()
                    .group(ItemGroup.MATERIALS)
                    .maxStackSize(64))
    );

    public static final RegistryObject<Block> DIAMOND_TORCH = BLOCKS.register("diamond_torch", () ->
            new DiamondTorch()
    );

    public static final RegistryObject<Block> EMERALD_TORCH = BLOCKS.register("emerald_torch", () ->
            new EmeraldTorch()
    );

    public static final RegistryObject<Block> GOLD_TORCH = BLOCKS.register("gold_torch", () ->
            new GoldTorch()
    );

    public static final RegistryObject<Block> LAPIS_TORCH = BLOCKS.register("lapis_torch", () ->
            new LapisTorch()
    );

    public static final RegistryObject<Block> IRON_TORCH = BLOCKS.register("iron_torch", () ->
            new IronTorch()
    );

    public static final RegistryObject<Block> DIAMOND_WALL_TORCH = BLOCKS.register("diamond_wall_torch", () ->
            new DiamondWallTorch()
    );

    public static final RegistryObject<Block> EMERALD_WALL_TORCH = BLOCKS.register("emerald_wall_torch", () ->
            new EmeraldWallTorch()
    );

    public static final RegistryObject<Block> GOLD_WALL_TORCH = BLOCKS.register("gold_wall_torch", () ->
            new GoldWallTorch()
    );

    public static final RegistryObject<Block> LAPIS_WALL_TORCH = BLOCKS.register("lapis_wall_torch", () ->
            new LapisWallTorch()
    );

    public static final RegistryObject<Block> IRON_WALL_TORCH = BLOCKS.register("iron_wall_torch", () ->
            new IronWallTorch()
    );

    public static final RegistryObject<Item> DIAMOND_TORCH_BI = ITEMS.register("diamond_torch", () ->
            new WallOrFloorItem(DIAMOND_TORCH.get(), DIAMOND_WALL_TORCH.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );

    public static final RegistryObject<Item> EMERALD_TORCH_BI = ITEMS.register("emerald_torch", () ->
            new WallOrFloorItem(EMERALD_TORCH.get(), EMERALD_WALL_TORCH.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );

    public static final RegistryObject<Item> GOLD_TORCH_BI = ITEMS.register("gold_torch", () ->
            new WallOrFloorItem(GOLD_TORCH.get(), GOLD_WALL_TORCH.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );

    public static final RegistryObject<Item> LAPIS_TORCH_BI = ITEMS.register("lapis_torch", () ->
            new WallOrFloorItem(LAPIS_TORCH.get(), LAPIS_WALL_TORCH.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );

    public static final RegistryObject<Item> IRON_TORCH_BI = ITEMS.register("iron_torch", () ->
            new WallOrFloorItem(IRON_TORCH.get(), IRON_WALL_TORCH.get(), (new Item.Properties()).group(ItemGroup.DECORATIONS))
    );


    public ExtraCoals() {
        BLOCKS.register(FMLJavaModLoadingContext.get().getModEventBus());
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());

        FMLJavaModLoadingContext.get().getModEventBus().addListener(this::onClientSetup);
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
    }

    private void onClientSetup(final FMLClientSetupEvent event) {
        RenderTypeLookup.setRenderLayer(DIAMOND_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(EMERALD_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GOLD_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LAPIS_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(IRON_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(DIAMOND_WALL_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(EMERALD_WALL_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(GOLD_WALL_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(LAPIS_WALL_TORCH.get(), RenderType.getCutout());
        RenderTypeLookup.setRenderLayer(IRON_WALL_TORCH.get(), RenderType.getCutout());
    }
    @Mod.EventBusSubscriber
    public static class FuelEvents{
        @SubscribeEvent
        public static void onFuelRegister(FurnaceFuelBurnTimeEvent event) {
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(DIAMOND_COAL.get()))) {
                event.setBurnTime(200*64+4);
            }
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(EMERALD_COAL.get()))) {
                event.setBurnTime(200*64+2);
            }
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(GOLD_COAL.get()))) {
                event.setBurnTime(200*16+8);
            }
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(IRON_COAL.get()))) {
                event.setBurnTime(200*32);
            }
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(LAPIS_COAL.get()))) {
                event.setBurnTime(200*32+2);
            }
            if(ItemStack.areItemsEqual(event.getItemStack(), new ItemStack(REDSTONE_COAL.get()))) {
                event.setBurnTime(200*32+4);
            }
        }
    }
}
