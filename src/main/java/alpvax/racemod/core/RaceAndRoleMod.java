package alpvax.racemod.core;

import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = RaceModConstants.MOD_ID, name = RaceModConstants.MOD_NAME)
public class RaceAndRoleMod
{
	@Mod.Instance(RaceModConstants.MOD_ID)
	public static RaceAndRoleMod instance;

	//public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		CapabilityRaceHandler.register();
		MinecraftForge.EVENT_BUS.register(new RaceModHooks());

		/*TODO:packetHandler = new AlpPacketManager(AbilitiesAPIConstants.MOD_ID); initPackets();
		}
		
		private void initPackets()
		{
		//TODO: packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);*/
	}
}
