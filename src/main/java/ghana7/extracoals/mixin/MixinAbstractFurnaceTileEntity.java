package ghana7.extracoals.mixin;

import ghana7.extracoals.ExtraCoals;
import net.minecraft.block.AbstractFurnaceBlock;
import net.minecraft.inventory.IRecipeHelperPopulator;
import net.minecraft.inventory.IRecipeHolder;
import net.minecraft.inventory.ISidedInventory;
import net.minecraft.item.Item;
import net.minecraft.item.ItemStack;
import net.minecraft.item.crafting.AbstractCookingRecipe;
import net.minecraft.item.crafting.IRecipe;
import net.minecraft.item.crafting.IRecipeType;
import net.minecraft.tileentity.*;
import net.minecraft.util.NonNullList;
import net.minecraft.util.math.MathHelper;
import net.minecraft.world.World;
import org.spongepowered.asm.mixin.Mixin;
import org.spongepowered.asm.mixin.Shadow;
import org.spongepowered.asm.mixin.injection.At;
import org.spongepowered.asm.mixin.injection.Inject;
import org.spongepowered.asm.mixin.injection.callback.CallbackInfo;

import javax.annotation.Nullable;

@Mixin(AbstractFurnaceTileEntity.class)
public abstract class MixinAbstractFurnaceTileEntity extends LockableTileEntity implements ISidedInventory, IRecipeHolder, IRecipeHelperPopulator, ITickableTileEntity {

    @Shadow
    private int burnTime;
    @Shadow
    private int recipesUsed;
    @Shadow
    private int cookTime;
    @Shadow
    private int cookTimeTotal;
    @Shadow
    protected IRecipeType<? extends AbstractCookingRecipe> recipeType;
    @Shadow
    protected NonNullList<ItemStack> items;
    protected MixinAbstractFurnaceTileEntity(TileEntityType<?> typeIn) {
        super(typeIn);
    }

    private Item lastFuelUsed;
    @Inject(at = @At("HEAD"), method = "tick()V", cancellable = true)
    private void tick(CallbackInfo callback) {
        /*if(!this.world.isRemote) {
            if(this.isBurning()) {
                Item fuelItem = this.getStackInSlot(1).getItem();
                int speedIncrease = 0;
                if(fuelItem == ExtraCoals.GOLD_COAL.get()) {
                    speedIncrease = 3;
                }
                this.burnTime -= speedIncrease;
                if(this.burnTime <= 0) {
                    this.burnTime = 1;
                }
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).orElse(null);
                if(this.canSmelt(irecipe)) {
                    this.cookTime += speedIncrease;
                    if(this.cookTime >= this.cookTimeTotal) {
                        //set equal to cookTimeTotal - 1, so the vanilla increment makes it equal
                        //mojang if you see this make it a >=
                        this.cookTime = this.cookTimeTotal - 1;
                    }
                }
            }
        }*/

        boolean flag = this.isBurning();
        boolean flag1 = false;

        int speedIncrease = 1;
        if(!this.world.isRemote && lastFuelUsed != null) {
            if(lastFuelUsed == ExtraCoals.GOLD_COAL.get()) {
                speedIncrease = 8;
            }
            if(lastFuelUsed == ExtraCoals.REDSTONE_COAL.get()) {
                speedIncrease = 4;
            }
            if(lastFuelUsed == ExtraCoals.DIAMOND_COAL.get()) {
                speedIncrease = 4;
            }
            if(lastFuelUsed == ExtraCoals.EMERALD_COAL.get()) {
                speedIncrease = 2;
            }
            if(lastFuelUsed == ExtraCoals.LAPIS_COAL.get()) {
                speedIncrease = 2;
            }
        }
        if (this.isBurning()) {
            this.burnTime -= speedIncrease;
        }

        if (!this.world.isRemote) {
            ItemStack itemstack = this.items.get(1);
            if (this.isBurning() || !itemstack.isEmpty() && !this.items.get(0).isEmpty()) {
                IRecipe<?> irecipe = this.world.getRecipeManager().getRecipe((IRecipeType<AbstractCookingRecipe>)this.recipeType, this, this.world).orElse(null);
                if (!this.isBurning() && this.canSmelt(irecipe)) {
                    this.burnTime = this.getBurnTime(itemstack);
                    this.recipesUsed = this.burnTime;
                    if (this.isBurning()) {
                        flag1 = true;
                        if (itemstack.hasContainerItem())
                            this.items.set(1, itemstack.getContainerItem());
                        else
                        if (!itemstack.isEmpty()) {
                            Item item = itemstack.getItem();
                            lastFuelUsed = item;
                            itemstack.shrink(1);
                            if (itemstack.isEmpty()) {
                                this.items.set(1, itemstack.getContainerItem());
                            }
                        }
                    }
                }

                if (this.isBurning() && this.canSmelt(irecipe)) {
                    this.cookTime += speedIncrease;
                    if (this.cookTime >= this.cookTimeTotal) {
                        this.cookTime -= this.cookTimeTotal;
                        this.cookTimeTotal = this.getCookTime();
                        this.smelt(irecipe);
                        flag1 = true;
                    }
                } else {
                    this.cookTime = 0;
                }
            } else if (!this.isBurning() && this.cookTime > 0) {
                this.cookTime = MathHelper.clamp(this.cookTime - 2, 0, this.cookTimeTotal);
            }

            if (flag != this.isBurning()) {
                flag1 = true;
                this.world.setBlockState(this.pos, this.world.getBlockState(this.pos).with(AbstractFurnaceBlock.LIT, Boolean.valueOf(this.isBurning())), 3);
            }
        }

        if (flag1) {
            this.markDirty();
        }
        callback.cancel();
    }

    @Shadow
    public ItemStack getStackInSlot(int index) {
        throw new IllegalStateException("Mixin failed to shadow getStackInSlot()");
    }

    @Shadow
    public boolean isBurning() {
        throw new IllegalStateException("Mixin failed to shadow isBurning()");
    }

    @Shadow
    protected boolean canSmelt(@Nullable IRecipe<?> recipeIn) {
        throw new IllegalStateException("Mixin failed to shadow canSmelt()");
    }

    @Shadow
    protected int getBurnTime(ItemStack fuel) {
        throw new IllegalStateException("Mixin failed to shadow getBurnTime()");
    }

    @Shadow
    protected int getCookTime() {
        throw new IllegalStateException("Mixin failed to shadow getCookTime()");
    }

    @Shadow
    private void smelt(@Nullable IRecipe<?> recipe) {
        throw new IllegalStateException("Mixin failed to shadow smelt()");
    }
}
