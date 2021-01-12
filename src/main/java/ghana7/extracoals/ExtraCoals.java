package ghana7.extracoals;

import net.minecraft.item.Item;
import net.minecraft.item.ItemGroup;
import net.minecraft.item.ItemStack;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.event.furnace.FurnaceFuelBurnTimeEvent;
import net.minecraftforge.eventbus.api.SubscribeEvent;
import net.minecraftforge.fml.RegistryObject;
import net.minecraftforge.fml.common.Mod;
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
    public ExtraCoals() {
        ITEMS.register(FMLJavaModLoadingContext.get().getModEventBus());
        // Register ourselves for server and other game events we are interested in
        MinecraftForge.EVENT_BUS.register(this);
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
