package net.canarymod.api.entity.vehicle;


import net.canarymod.api.MobSpawnerLogic;
import net.minecraft.server.EntityMinecartMobSpawner;


/**
 * MobSpawnerMinecart wrapper implementation
 * 
 * @author Jason (darkdiplomat)
 */
public class CanaryMobSpawnerMinecart extends CanaryMinecart implements MobSpawnerMinecart {

    public CanaryMobSpawnerMinecart(EntityMinecartMobSpawner entity) {
        super(entity);
    }

    @Override
    public MobSpawnerLogic getMobSpawnerLogic() {
        return ((EntityMinecartMobSpawner) entity).a.logic;
    }

}
