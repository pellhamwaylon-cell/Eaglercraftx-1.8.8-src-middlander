package net.minecraft.entity.item;

import net.minecraft.client.Minecraft;
import net.minecraft.entity.Entity;
import net.minecraft.entity.EntityHanging;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.init.Blocks;
import net.minecraft.init.Items;
import net.minecraft.item.Item;
import net.minecraft.item.ItemMap;
import net.minecraft.item.ItemStack;
import net.minecraft.nbt.NBTTagCompound;
import net.minecraft.util.BlockPos;
import net.minecraft.util.DamageSource;
import net.minecraft.util.EnumFacing;
import net.minecraft.world.World;
import net.minecraft.world.storage.MapData;

public class EntityItemFrame extends EntityHanging {
	private float itemDropChance = 1.0F;

	public EntityItemFrame(World worldIn) {
		super(worldIn);
	}

	public EntityItemFrame(World worldIn, BlockPos parBlockPos, EnumFacing parEnumFacing) {
		super(worldIn, parBlockPos);
		this.updateFacingWithBoundingBox(parEnumFacing);
	}

	protected void entityInit() {
		this.getDataWatcher().addObjectByDataType(8, 5);
		this.getDataWatcher().addObject(9, Byte.valueOf((byte) 0));
	}

	public float getCollisionBorderSize() {
		return 0.0F;
	}

	public boolean attackEntityFrom(DamageSource damagesource, float f) {
		if (this.isEntityInvulnerable(damagesource)) {
			return false;
		} else if (!damagesource.isExplosion() && this.getDisplayedItem() != null) {
			if (!this.worldObj.isRemote) {
				this.dropItemOrSelf(damagesource.getEntity(), false);
				this.setDisplayedItem((ItemStack) null);
			}

			return true;
		} else {
			return super.attackEntityFrom(damagesource, f);
		}
	}

	public int getWidthPixels() {
		return 12;
	}

	public int getHeightPixels() {
		return 12;
	}

	public boolean isInRangeToRenderDist(double d0) {
		double d1 = 16.0D;
		d1 = d1 * 64.0D * this.renderDistanceWeight;
		return d0 < d1 * d1;
	}

	public void onBroken(Entity entity) {
		this.dropItemOrSelf(entity, true);
	}

	public void dropItemOrSelf(Entity parEntity, boolean parFlag) {
		if (this.worldObj.getGameRules().getBoolean("doEntityDrops")) {
			ItemStack itemstack = this.getDisplayedItem();
			if (parEntity instanceof EntityPlayer) {
				EntityPlayer entityplayer = (EntityPlayer) parEntity;
				if (entityplayer.capabilities.isCreativeMode) {
					this.removeFrameFromMap(itemstack);
					return;
				}
			}

			if (parFlag) {
				this.entityDropItem(new ItemStack(Items.item_frame), 0.0F);
			}

			if (itemstack != null && this.rand.nextFloat() < this.itemDropChance) {
				itemstack = itemstack.copy();
				this.removeFrameFromMap(itemstack);
				this.entityDropItem(itemstack, 0.0F);
			}

		}
	}

	private void removeFrameFromMap(ItemStack parItemStack) {
		if (parItemStack != null) {
			if (parItemStack.getItem() == Items.filled_map) {
				MapData mapdata = ((ItemMap) parItemStack.getItem()).getMapData(parItemStack, this.worldObj);
				mapdata.mapDecorations.remove("frame-" + this.getEntityId());
			}

			parItemStack.setItemFrame((EntityItemFrame) null);
		}
	}

	public ItemStack getDisplayedItem() {
		return this.getDataWatcher().getWatchableObjectItemStack(8);
	}

	public void setDisplayedItem(ItemStack parItemStack) {
		this.setDisplayedItemWithUpdate(parItemStack, true);
	}

	private void setDisplayedItemWithUpdate(ItemStack parItemStack, boolean parFlag) {
		if (parItemStack != null) {
			parItemStack = parItemStack.copy();
			parItemStack.stackSize = 1;
			parItemStack.setItemFrame(this);
		}

		this.getDataWatcher().updateObject(8, parItemStack);
		this.getDataWatcher().setObjectWatched(8);
		if (parFlag && this.hangingPosition != null) {
			this.worldObj.updateComparatorOutputLevel(this.hangingPosition, Blocks.air);
		}

	}

	public int getRotation() {
		return this.getDataWatcher().getWatchableObjectByte(9);
	}

	public void setItemRotation(int parInt1) {
		this.func_174865_a(parInt1, true);
	}

	private void func_174865_a(int parInt1, boolean parFlag) {
		this.getDataWatcher().updateObject(9, Byte.valueOf((byte) (parInt1 % 8)));
		if (parFlag && this.hangingPosition != null) {
			this.worldObj.updateComparatorOutputLevel(this.hangingPosition, Blocks.air);
		}

	}

	public void writeEntityToNBT(NBTTagCompound nbttagcompound) {
		if (this.getDisplayedItem() != null) {
			nbttagcompound.setTag("Item", this.getDisplayedItem().writeToNBT(new NBTTagCompound()));
			nbttagcompound.setByte("ItemRotation", (byte) this.getRotation());
			nbttagcompound.setFloat("ItemDropChance", this.itemDropChance);
		}

		super.writeEntityToNBT(nbttagcompound);
	}

	public void readEntityFromNBT(NBTTagCompound nbttagcompound) {
		NBTTagCompound nbttagcompound1 = nbttagcompound.getCompoundTag("Item");
		if (nbttagcompound1 != null && !nbttagcompound1.hasNoTags()) {
			this.setDisplayedItemWithUpdate(ItemStack.loadItemStackFromNBT(nbttagcompound1), false);
			this.func_174865_a(nbttagcompound.getByte("ItemRotation"), false);
			if (nbttagcompound.hasKey("ItemDropChance", 99)) {
				this.itemDropChance = nbttagcompound.getFloat("ItemDropChance");
			}

			if (nbttagcompound.hasKey("Direction")) {
				this.func_174865_a(this.getRotation() * 2, false);
			}
		}

		super.readEntityFromNBT(nbttagcompound);
	}

	public boolean interactFirst(EntityPlayer entityplayer) {
		if (this.getDisplayedItem() == null) {
			ItemStack itemstack = entityplayer.getHeldItem();
			if (itemstack != null && !this.worldObj.isRemote) {
				this.setDisplayedItem(itemstack);
				if (!entityplayer.capabilities.isCreativeMode && --itemstack.stackSize <= 0) {
					entityplayer.inventory.setInventorySlotContents(entityplayer.inventory.currentItem,
							(ItemStack) null);
				}
			}
		} else if (!this.worldObj.isRemote) {
			this.setItemRotation(this.getRotation() + 1);
		}

		return true;
	}

	public int func_174866_q() {
		return this.getDisplayedItem() == null ? 0 : this.getRotation() % 8 + 1;
	}

	public boolean eaglerEmissiveFlag = false;

	protected void renderDynamicLightsEaglerAt(double entityX, double entityY, double entityZ, double renderX,
			double renderY, double renderZ, float partialTicks, boolean isInFrustum) {
		super.renderDynamicLightsEaglerAt(entityX, entityY, entityZ, renderX, renderY, renderZ, partialTicks,
				isInFrustum);
		eaglerEmissiveFlag = Minecraft.getMinecraft().entityRenderer.renderItemEntityLight(this, 0.1f);
	}

	protected float getEaglerDynamicLightsValueSimple(float partialTicks) {
		float f = super.getEaglerDynamicLightsValueSimple(partialTicks);
		ItemStack itm = this.getDisplayedItem();
		if (itm != null && itm.stackSize > 0) {
			Item item = itm.getItem();
			if (item != null) {
				float f2 = item.getHeldItemBrightnessEagler(itm) * 0.75f;
				f = Math.min(f + f2 * 0.5f, 1.0f) + f2 * 0.5f;
			}
		}
		return f;
	}
}
