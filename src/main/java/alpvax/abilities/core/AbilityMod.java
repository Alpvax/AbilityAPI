package alpvax.abilities.core;


import alpvax.common.network.AlpPacketManager;
import alpvax.outdated.abilities.core.AbilityAPIHooks;
import net.minecraftforge.common.MinecraftForge;
import net.minecraftforge.fml.common.Mod;
import net.minecraftforge.fml.common.event.FMLPreInitializationEvent;

@Mod(modid = AbilitiesModConstants.MOD_ID, name = AbilitiesModConstants.MOD_NAME)
public class AbilityMod
{
	@Mod.Instance(AbilitiesModConstants.MOD_ID)
	public AbilityMod instance;

	public static AlpPacketManager packetHandler;

	@Mod.EventHandler
	public void preInit(FMLPreInitializationEvent e)
	{
		packetHandler = new AlpPacketManager(AbilitiesModConstants.MOD_ID);
		initPackets();

		CapabilityAbilityHandler.register();
		MinecraftForge.EVENT_BUS.register(new AbilityAPIHooks());
	}

	private void initPackets()
	{
		//TODO: packetHandler.registerMessage(PoweredPlayerPacket.Handler.class, PoweredPlayerPacket.class, Side.CLIENT);
	}
}
