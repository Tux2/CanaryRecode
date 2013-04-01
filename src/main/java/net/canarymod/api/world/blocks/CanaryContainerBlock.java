package net.canarymod.api.world.blocks;

import net.canarymod.api.inventory.CanaryItem;
import net.canarymod.api.inventory.Inventory;
import net.canarymod.api.inventory.Item;
import net.canarymod.api.inventory.ItemType;
import net.canarymod.api.nbt.CanaryCompoundTag;
import net.canarymod.config.Configuration;
import net.minecraft.server.IInventory;

/**
 * ContainerBlock buffer between ComplexBlock and those with Inventories
 * 
 * @author Jason (darkdiplomat)
 */
public abstract class CanaryContainerBlock extends CanaryComplexBlock implements Inventory {

    public CanaryContainerBlock(IInventory inventory) {
        super(inventory);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(ItemType type) {
        this.addItem(type.getId(), 1, (short) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId) {
        this.addItem(itemId, 1, (short) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, short damage) {
        this.addItem(itemId, 1, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, int amount) {
        this.addItem(itemId, amount, (short) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(ItemType type, int amount) {
        this.addItem(type.getId(), amount, (short) 0);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(int itemId, int amount, short damage) {
        this.addItem(new CanaryItem(itemId, amount, damage));
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(ItemType type, int amount, short damage) {
        this.addItem(type.getId(), amount, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void addItem(Item item) {
        this.insertItem(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item decreaseItemStackSize(int itemId, int amount) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item decreaseItemStackSize(int itemId, int amount, short damage) {
        // TODO Auto-generated method stub
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getEmptySlot() {
        for (int index = 0; index < getSize(); index++) {
            if (getSlot(index) == null) {
                return index;
            }
        }
        return -1;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public String getInventoryName() {
        return inventory.b();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getInventoryStackLimit() {
        return inventory.d();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(ItemType type) {
        return this.getItem(type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(ItemType type, int amount) {
        return this.getItem(type.getId(), amount);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id, int amount) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getAmount() == amount) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(int id, int amount, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getAmount() == amount && toCheck.getDamage() == damage) {
                toCheck.setSlot(index);
                return toCheck;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getItem(ItemType type, int amount, short damage) {
        return this.getItem(type.getId(), amount, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public int getSize() {
        return inventory.j_();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item getSlot(int index) {
        return inventory.a(index).getCanaryItem();
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId) {
        for (int index = 0; index < getSize(); index++) {
            if (getSlot(index) != null && getSlot(index).getId() == itemId) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(ItemType type) {
        return this.hasItem(type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(ItemType type, short damage) {
        return this.hasItem(type.getId(), damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItem(int itemId, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item item = getSlot(index);
            if (item != null && item.getId() == itemId && item.getDamage() == damage) {
                return true;
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(ItemType type, int amount) {
        return this.hasItemStack(type.getId(), amount, 64);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount) {
        return this.hasItemStack(itemId, amount, 64);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(ItemType type, int amount, short damage) {
        return this.hasItemStack(type.getId(), amount, 64, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int amount, short damage) {
        return hasItemStack(itemId, amount, 64, damage);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == itemId) {
                int am = toCheck.getAmount();
                if (am > minAmount && am < maxAmount) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean hasItemStack(int itemId, int minAmount, int maxAmount, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == itemId && toCheck.getDamage() == damage) {
                int am = toCheck.getAmount();
                if (am > minAmount && am < maxAmount) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public boolean insertItem(Item item) {
        int amount = item.getAmount();
        Item itemExisting;
        int maxAmount = item.getMaxAmount();

        while (amount > 0) {
            // Get an existing item with at least 1 spot free
            itemExisting = this.getItem(item.getId(), maxAmount - 1, (short) item.getDamage());

            // Add the items to the existing stack of items
            if (itemExisting != null && (!item.isEnchanted() || Configuration.getServerConfig().allowEnchantmentStacking())) {
                // Add as much items as possible to the stack
                int k = Math.min(maxAmount - itemExisting.getAmount(), item.getAmount());
                this.setSlot(item.getId(), itemExisting.getAmount() + k, (short) item.getDamage(), itemExisting.getSlot());
                amount -= k;
                continue;
            }
            // We still have slots, but no stack, create a new stack.
            if (this.getEmptySlot() != -1) {
                CanaryCompoundTag nbt = new CanaryCompoundTag("");
                ((CanaryItem) item).getHandle().b(nbt.getHandle());
                Item tempItem = new CanaryItem(item.getId(), amount, -1, item.getDamage());
                ((CanaryItem) tempItem).getHandle().c(nbt.getHandle());
                this.addItem(tempItem);
                amount = 0;
                continue;
            }

            // No free slots, no incomplete stacks: full
            // make sure the stored items are removed
            item.setAmount(amount);
            return false;
        }
        return true;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int itemId, short damage, int slot) {
        this.setSlot(itemId, 1, damage, slot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int itemId, int amount, short damage, int slot) {
        CanaryItem item = new CanaryItem(itemId, 1, damage);
        item.setSlot(slot);
        this.setSlot(item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(ItemType type, int amount, int slot) {
        this.setSlot(type.getId(), amount, (short) 0, slot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(ItemType type, int amount, short damage, int slot) {
        this.setSlot(type.getId(), amount, damage, slot);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(Item item) {
        this.setSlot(item.getSlot(), item);
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public void setSlot(int index, Item value) {
        inventory.a(index, ((CanaryItem) value).getHandle());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(Item item) {
        return this.removeItem(item.getId(), (short) item.getDamage());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int id) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id) {
                setSlot(index, null);
                return toCheck;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(int id, short damage) {
        for (int index = 0; index < getSize(); index++) {
            Item toCheck = getSlot(index);
            if (toCheck != null && toCheck.getId() == id && toCheck.getDamage() == damage) {
                setSlot(index, null);
                return toCheck;
            }
        }
        return null;
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(ItemType type) {
        return this.removeItem(type.getId());
    }

    /**
     * {@inheritDoc}
     */
    @Override
    public Item removeItem(ItemType type, short damage) {
        return this.removeItem(type.getId(), damage);
    }
}
