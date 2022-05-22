package mutato115.mods.jesusmod.common.block;

import mutato115.mods.jesusmod.init.TagInit;
import net.minecraft.core.BlockPos;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.entity.AgeableMob;
import net.minecraft.world.entity.ai.targeting.TargetingConditions;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.npc.AbstractVillager;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.npc.Villager;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.SoundType;
import net.minecraft.world.level.block.state.BlockBehaviour;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.material.Material;
import net.minecraft.world.level.material.MaterialColor;
import net.minecraft.world.phys.AABB;
import net.minecraft.world.phys.BlockHitResult;

import java.util.List;

public class BlockAltar extends Block {

    private static final Properties props = BlockBehaviour.Properties.of(Material.STONE, MaterialColor.SAND).strength(3.0f, 2.0f).sound(SoundType.STONE).requiresCorrectToolForDrops();

    public BlockAltar() {
        super(props);
    }


    @SuppressWarnings("deprecation")
    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
        if (!world.isClientSide()) {
            if (TagInit.JItemTags.SHAREABLE_FOOD.contains(player.getItemInHand(hand).getItem())) {
                List<AgeableMob> villagerList = world.getNearbyEntities(AgeableMob.class, TargetingConditions.DEFAULT, player, new AABB(pos.getX()-20, pos.getY()-11, pos.getZ()-20, pos.getX()+20, pos.getY()+11, pos.getZ()+20));
                if (!villagerList.isEmpty()) {
                    for (AgeableMob vil : villagerList) {
                        if (vil instanceof Npc) {
                            ItemEntity food = new ItemEntity(world, vil.position().x, vil.position().y + 3, vil.position().z, new ItemStack(player.getItemInHand(hand).getItem()));
                            world.addFreshEntity(food);
                        }
                    }
                    return InteractionResult.SUCCESS;
                }
            }
        }

        return InteractionResult.PASS;
    }
}
