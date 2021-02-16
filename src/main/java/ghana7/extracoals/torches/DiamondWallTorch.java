package ghana7.extracoals.torches;

import ghana7.extracoals.ExtraCoals;
import net.minecraft.block.AbstractBlock;
import net.minecraft.block.BlockState;
import net.minecraft.block.SoundType;
import net.minecraft.block.WallTorchBlock;
import net.minecraft.block.material.Material;
import net.minecraft.particles.IParticleData;
import net.minecraft.particles.ParticleTypes;
import net.minecraft.util.math.BlockPos;
import net.minecraft.world.World;
import net.minecraftforge.api.distmarker.Dist;
import net.minecraftforge.api.distmarker.OnlyIn;

import java.util.Random;

public class DiamondWallTorch extends WallTorchBlock {
    public DiamondWallTorch() {
        super(AbstractBlock.Properties.create(Material.MISCELLANEOUS).doesNotBlockMovement().zeroHardnessAndResistance().setLightLevel((state) -> {
            return 15;
        }).sound(SoundType.WOOD).lootFrom(ExtraCoals.DIAMOND_TORCH.get()), ParticleTypes.FLAME);
    }
    @Override
    @OnlyIn(Dist.CLIENT)
    public void animateTick(BlockState stateIn, World worldIn, BlockPos pos, Random rand) {
        double d0 = (double)pos.getX() + 0.5D;
        double d1 = (double)pos.getY() + 0.7D;
        double d2 = (double)pos.getZ() + 0.5D;
        worldIn.addParticle(ParticleTypes.SMOKE, d0, d1, d2, 0.0D, 0.0D, 0.0D);
    }
}
