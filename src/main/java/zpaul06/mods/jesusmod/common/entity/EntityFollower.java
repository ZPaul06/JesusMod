package zpaul06.mods.jesusmod.common.entity;

import zpaul06.mods.jesusmod.init.ItemRegistry;
import zpaul06.mods.jesusmod.init.TagInit;
import net.minecraft.server.level.ServerLevel;
import net.minecraft.world.Container;
import net.minecraft.world.InteractionHand;
import net.minecraft.world.InteractionResult;
import net.minecraft.world.SimpleContainer;
import net.minecraft.world.entity.*;
import net.minecraft.world.entity.ai.attributes.AttributeSupplier;
import net.minecraft.world.entity.ai.attributes.Attributes;
import net.minecraft.world.entity.ai.goal.*;
import net.minecraft.world.entity.item.ItemEntity;
import net.minecraft.world.entity.monster.*;
import net.minecraft.world.entity.npc.InventoryCarrier;
import net.minecraft.world.entity.npc.Npc;
import net.minecraft.world.entity.player.Player;
import net.minecraft.world.item.Item;
import net.minecraft.world.item.ItemStack;
import net.minecraft.world.item.crafting.Ingredient;
import net.minecraft.world.level.Level;

import javax.annotation.Nullable;

public class EntityFollower extends TamableAnimal implements Npc, InventoryCarrier {

    private TemptGoal temptGoal;
    private static final Ingredient TEMPT_INGREDIENT = Ingredient.of(ItemRegistry.TORAH.get());

    private final SimpleContainer inventory = new SimpleContainer(8);

    public EntityFollower(EntityType<EntityFollower> entity, Level world) {
        super(entity, world);
        this.setTame(false);
        this.setCanPickUpLoot(true);
    }


    protected void registerGoals() {
        this.temptGoal = new TemptGoal(this, 0.6D, TEMPT_INGREDIENT, true);
        this.goalSelector.addGoal(0, new FloatGoal(this));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zombie.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Evoker.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vindicator.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Vex.class, 8.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Pillager.class, 15.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Illusioner.class, 12.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new AvoidEntityGoal<>(this, Zoglin.class, 10.0F, 0.5D, 0.5D));
        this.goalSelector.addGoal(1, new PanicGoal(this, 0.5D));
        //this.goalSelector.addGoal(3, this.temptGoal);
        this.goalSelector.addGoal(4, new MoveTowardsRestrictionGoal(this, 0.35D));
        this.goalSelector.addGoal(6, new FollowOwnerGoal(this, 1.0D, 10.0F, 5.0F, false));
        this.goalSelector.addGoal(8, new WaterAvoidingRandomStrollGoal(this, 0.35D));
        this.goalSelector.addGoal(9, new InteractGoal(this, Player.class, 3.0F, 1.0F));
        this.goalSelector.addGoal(10, new LookAtPlayerGoal(this, Mob.class, 8.0F));
    }

    public static AttributeSupplier.Builder createAttributes() {
        return Mob.createMobAttributes().add(Attributes.MAX_HEALTH, 20.0D).add(Attributes.MOVEMENT_SPEED, 0.5D);
    }




    @Override
    public boolean canBeLeashed(Player p_21813_) {
        return false;
    }

    @Override
    public InteractionResult mobInteract(Player p_30412_, InteractionHand p_30413_) {
        ItemStack itemstack = p_30412_.getItemInHand(p_30413_);
        Item item = itemstack.getItem();
        if (this.level.isClientSide) {
            boolean flag = this.isOwnedBy(p_30412_) || this.isTame();
            return flag ? InteractionResult.CONSUME : InteractionResult.PASS;
        } else {
            if (this.isTame()) {
                InteractionResult interactionresult = super.mobInteract(p_30412_, p_30413_);
                return interactionresult;
            } else if (itemstack.is(ItemRegistry.TORAH.get())) {
                if (this.random.nextInt(3) == 0 && !net.minecraftforge.event.ForgeEventFactory.onAnimalTame(this, p_30412_)) {
                    this.tame(p_30412_);
                    this.navigation.stop();
                    this.setTarget((LivingEntity)null);
                    this.level.broadcastEntityEvent(this, (byte)7);
                } else {
                    this.level.broadcastEntityEvent(this, (byte)6);
                }

                return InteractionResult.SUCCESS;
            }

            return super.mobInteract(p_30412_, p_30413_);
        }
    }

    @Nullable
    @Override
    public AgeableMob getBreedOffspring(ServerLevel world, AgeableMob mob) {
        return null;
    }

    @Override
    public Container getInventory() {
        return this.inventory;
    }

    @Override
    public SlotAccess getSlot(int p_149995_) {
        int i = p_149995_ - 300;
        return i >= 0 && i < this.inventory.getContainerSize() ? SlotAccess.forContainer(this.inventory, i) : super.getSlot(p_149995_);
    }

    @Override
    protected void pickUpItem(ItemEntity p_35467_) {
        ItemStack itemstack = p_35467_.getItem();
        if (this.wantsToPickUp(itemstack)) {
            SimpleContainer simplecontainer = (SimpleContainer) this.getInventory();
            boolean flag = simplecontainer.canAddItem(itemstack);
            if (!flag) {
                return;
            }

            this.onItemPickup(p_35467_);
            this.take(p_35467_, itemstack.getCount());
            ItemStack itemstack1 = simplecontainer.addItem(itemstack);
            if (itemstack1.isEmpty()) {
                p_35467_.discard();
            } else {
                itemstack.setCount(itemstack1.getCount());
            }
        }

    }

    @Override
    public boolean wantsToPickUp(ItemStack itemStack) {
        Item item = itemStack.getItem();
        return (TagInit.JItemTags.SHAREABLE_FOOD.contains(item) && ((SimpleContainer)this.getInventory()).canAddItem(itemStack));
    }

    @Override
    public boolean canPickUpLoot() {
        return true;
    }
}
