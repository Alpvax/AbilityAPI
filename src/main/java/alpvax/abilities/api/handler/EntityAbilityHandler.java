package alpvax.abilities.api.handler;

import net.minecraft.entity.Entity;
import net.minecraft.entity.player.EntityPlayer;

public class EntityAbilityHandler extends SimpleAbilityHandler
{
	private final Entity entity;

	public EntityAbilityHandler(Entity handled)
	{
		super(handled);
		entity = (EntityPlayer)handled;
	}

	public Entity getEntity()
	{
		return entity;
	}

	public static class PlayerAbilityHandler extends EntityAbilityHandler
	{
		private final EntityPlayer player;

		public PlayerAbilityHandler(Entity handled)
		{
			super(handled);
			player = (EntityPlayer)handled;
		}

		public EntityPlayer getPlayer()
		{
			return player;
		}
	}
}
