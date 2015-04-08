package ashjack.SUKReloaded2.core.util;

import java.io.File;
import java.lang.reflect.Field;
import java.util.List;

import net.minecraft.client.Minecraft;
import net.minecraft.client.resources.FileResourcePack;
import net.minecraft.client.resources.IResourcePack;
import ashjack.SUKReloaded2.core.SUK2Log;
import cpw.mods.fml.relauncher.Side;
import cpw.mods.fml.relauncher.SideOnly;

public class ExternalTextureLoader /*extends FolderResourcePack implements IResource*/{
	
	//public ExternalTextureLoader() 
	//{
	//	super(new File(SUK2IO.getSimukraftFolder("Races")));
	//}
	
	@SideOnly(Side.CLIENT)
	public static void pushTextures()
	{
		try 
		{
			SUK2FilePackager.packageAll();
			
			Minecraft mc = Minecraft.getMinecraft();
			Class classMC = mc.getClass();
			
			Field field = Minecraft.class.getDeclaredField("defaultResourcePacks");
			field.setAccessible(true);
			
			List<IResourcePack> list = (List<IResourcePack>) field.get(Minecraft.getMinecraft());
			List<IResourcePack> changedList = list;
						
			FileResourcePack racePack = new FileResourcePack(new File(SUK2IO.getTempFolder("Resources") + "/Race.zip"));
			
			changedList.add(racePack);
			
			field.set(Minecraft.getMinecraft(), changedList);

			Minecraft.getMinecraft().refreshResources();
			/*
			Type listType = method.getGenericParameterTypes()[0];
			if (listType instanceof ParameterizedType) {
			    Type elementType = ((ParameterizedType) listType).getActualTypeArguments()[0];
			}*/
		} 
		catch (Exception e) 
		{
			e.printStackTrace();
		} 
	}

	/*@Override
	public InputStream getInputStream() 
	{
		return null;
	}

	@Override
	public boolean hasMetadata() 
	{
		return false;
	}

	@Override
	public IMetadataSection getMetadata(String p_110526_1_) 
	{
		return null;
	}*/

}
