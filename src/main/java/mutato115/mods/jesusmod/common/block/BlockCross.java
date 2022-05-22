package mutato115.mods.jesusmod.common.block;

import mutato115.mods.jesusmod.common.world.dim.HeavenTeleporter;
import mutato115.mods.jesusmod.init.DimensionRegistry;
import net.minecraft.core.BlockPos;
import net.minecraft.core.Direction;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.effect.MobEffectInstance;
import net.minecraft.world.effect.MobEffects;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.context.BlockPlaceContext;
import net.minecraft.world.level.BlockGetter;
import net.minecraft.world.level.Level;
import net.minecraft.world.level.LevelAccessor;
import net.minecraft.world.level.block.Block;
import net.minecraft.world.level.block.HorizontalDirectionalBlock;
import net.minecraft.world.level.block.Mirror;
import net.minecraft.world.level.block.Rotation;
import net.minecraft.world.level.block.state.BlockState;
import net.minecraft.world.level.block.state.StateDefinition;
import net.minecraft.world.level.block.state.properties.DirectionProperty;
import net.minecraft.world.level.dimension.DimensionType;
import net.minecraft.world.phys.BlockHitResult;
import net.minecraft.world.phys.shapes.BooleanOp;
import net.minecraft.world.phys.shapes.CollisionContext;
import net.minecraft.world.phys.shapes.Shapes;
import net.minecraft.world.phys.shapes.VoxelShape;

import javax.annotation.Nullable;

public class BlockCross extends Block {

    public static final DirectionProperty FACING = HorizontalDirectionalBlock.FACING;

    public static final VoxelShape NORTH = makeNorthSouthShape();
    public static final VoxelShape SOUTH = makeNorthSouthShape();
    public static final VoxelShape EAST = makeEastWestShape();
    public static final VoxelShape WEST = makeEastWestShape();

    public BlockCross(Properties props) {
        super(props);

        this.registerDefaultState(this.defaultBlockState().setValue(FACING, Direction.NORTH));
    }

    @Override
    public InteractionResult use(BlockState state, Level world, BlockPos pos, Player player, InteractionHand hand, BlockHitResult res) {
        if (!world.isClientSide()) {
            if (!world.dimension().equals(DimensionRegistry.HEAVEN)) {
                ServerLevel heaven = world.getServer().getLevel(DimensionRegistry.HEAVEN);
                if (heaven != null) {
                    Runnable effects = new Runnable() {
                        public void run() {
                            player.addEffect(new MobEffectInstance(MobEffects.GLOWING, 1200, 1, false, false, false, (MobEffectInstance)null));
                            player.addEffect(new MobEffectInstance(MobEffects.JUMP, 1200, 3, false, false, false, (MobEffectInstance)null));
                            player.addEffect(new MobEffectInstance(MobEffects.SLOW_FALLING, 1200, 3, false, false, false, (MobEffectInstance)null));
                            try {
                                Thread.sleep(40000);
                            } catch (InterruptedException e) {}
                            player.addEffect(new MobEffectInstance(MobEffects.LEVITATION, 400, 1, false, false, false, (MobEffectInstance)null));
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {}
                            player.addEffect(new MobEffectInstance(MobEffects.INVISIBILITY, 200, 1, false, false, false, (MobEffectInstance)null));
                            try {
                                Thread.sleep(10000);
                            } catch (InterruptedException e) {}
                            player.changeDimension(heaven, new HeavenTeleporter(pos));
                        }
                    };

                    player.removeAllEffects();
                    new Thread(effects).start();

                    return InteractionResult.CONSUME;
                }
            }
        }

        return InteractionResult.PASS;
    }

    @Override
    protected void createBlockStateDefinition(StateDefinition.Builder<Block, BlockState> builder) {
        builder.add(FACING);
        super.createBlockStateDefinition(builder);
    }

    @Override
    public VoxelShape getShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return NORTH;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            default:
                return NORTH;
        }
    }

    @Override
    public VoxelShape getCollisionShape(BlockState state, BlockGetter getter, BlockPos pos, CollisionContext ctx) {
        switch (state.getValue(FACING)) {
            case NORTH:
                return NORTH;
            case EAST:
                return EAST;
            case SOUTH:
                return SOUTH;
            case WEST:
                return WEST;
            default:
                return NORTH;
        }
    }

    @Nullable
    @Override
    public BlockState getStateForPlacement(BlockPlaceContext ctx) {
        return this.defaultBlockState().setValue(FACING, ctx.getHorizontalDirection().getOpposite());
    }

    @Override
    public BlockState mirror(BlockState state, Mirror mirror) {
        return state.rotate(mirror.getRotation(state.getValue(FACING)));
    }

    @Override
    public BlockState rotate(BlockState state, LevelAccessor world, BlockPos pos, Rotation direction) {
        return state.setValue(FACING, direction.rotate(state.getValue(FACING)));
    }



    private static VoxelShape makeNorthSouthShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 2, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(-0.25, 1, 0.25, 0.25, 1.5, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.75, 1, 0.25, 1.25, 1.5, 0.75), BooleanOp.OR);

        return shape;
    }
    
    private static VoxelShape makeEastWestShape() {
        VoxelShape shape = Shapes.empty();
        shape = Shapes.join(shape, Shapes.box(0.25, 0, 0.25, 0.75, 2, 0.75), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 1, -0.25, 0.75, 1.5, 0.25), BooleanOp.OR);
        shape = Shapes.join(shape, Shapes.box(0.25, 1, 0.75, 0.75, 1.5, 1.25), BooleanOp.OR);

        return shape;
    }
}
