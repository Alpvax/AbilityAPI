package alpvax.abilities.core;


import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AbilitiesAPIConstants.MOD_ID, name = AbilitiesAPIConstants.MOD_NAME)
public class AbilityMod
{
	@Mod.Instance(AbilitiesAPIConstants.MOD_ID)
	public AbilityMod instance;

	// public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		CapabilityAbilityHandler.register();
		MinecraftForge.EVENT_BUS.register(new AbilityAPIHooks());

		/*
		 * TODO:packetHandler = new AlpPacketManager(AbilitiesAPIConstants.MOD_ID); initPackets(); }
		 *
		 * private void initPackets() { //packetHandler.registerMessage(PoweredPlayerPacket.Handler.class,
		 * PoweredPlayerPacket.class, Side.CLIENT);
		 */
	}
}