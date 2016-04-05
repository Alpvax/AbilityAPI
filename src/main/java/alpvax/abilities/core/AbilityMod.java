package alpvax.abilities.core;

import alpvax.abilities.api.capabilities.CapabilityAbilityHandler;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AbilitiesAPIConstants.MOD_ID, name = AbilitiesAPIConstants.MOD_NAME)
public class AbilityMod
{
	@Mod.Instance(AbilitiesAPIConstants.MOD_ID)
	public AbilityMod instance;

	//public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		CapabilityAbilityHandler.register();
		//TODO:MinecraftForge.EVENT_BUS.register(new AbilityAPIHooks());

		/*TODO:packetHandler = new AlpPacketManager(AbilitiesAPIConstants.MOD_ID); initPackets();
		}

		private void initPackets()
		{
		//TODO: packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);*/
	}
}