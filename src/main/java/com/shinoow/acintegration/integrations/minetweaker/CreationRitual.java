package com.shinoow.acintegration.integrations.minetweaker;

import java.util.ArrayList;
import java.util.List;

import com.shinoow.abyssalcraft.api.ritual.NecronomiconCreationRitual;
import com.shinoow.abyssalcraft.api.ritual.NecronomiconRitual;
import com.shinoow.abyssalcraft.api.ritual.RitualRegistry;

import minetweaker.IUndoableAction;
import minetweaker.MineTweakerAPI;
import minetweaker.api.item.IIngredient;
import minetweaker.api.item.IItemStack;
import net.minecraft.item.ItemStack;
import stanhebben.zenscript.annotations.ZenClass;
import stanhebben.zenscript.annotations.ZenMethod;

@ZenClass("mods.abyssalcraft.CreationRitual")
public class CreationRitual {

	@ZenMethod
	public static void addRitual(String unlocalizedName, int bookType, int dimension, float requiredEnergy, boolean remnantHelp, IItemStack item, IIngredient...offerings){

		Object[] offers = ACMT.toObjects(offerings);

		NecronomiconCreationRitual ritual = new NecronomiconCreationRitual(unlocalizedName, bookType, dimension, requiredEnergy, remnantHelp, ACMT.toStack(item), offers);
		MineTweakerAPI.apply(new Add(ritual));
	}

	private static class Add implements IUndoableAction
	{

		private final NecronomiconCreationRitual ritual;

		public Add(NecronomiconCreationRitual ritual){

			this.ritual = ritual;
		}

		@Override
		public void apply() {

			RitualRegistry.instance().registerRitual(ritual);
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Adding Necronomicon Creation Ritual for " + ritual.getItem().getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Removing Necronomicon Creation Ritual for " + ritual.getItem().getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			RitualRegistry.instance().getRituals().remove(ritual);
		}
	}

	@ZenMethod
	public static void removeRitual(IItemStack item){

		MineTweakerAPI.apply(new Remove(ACMT.toStack(item)));
	}

	private static class Remove implements IUndoableAction
	{
		private final ItemStack item;
		List<NecronomiconCreationRitual> removedRituals;

		public Remove(ItemStack item){
			this.item = item;
		}

		@Override
		public void apply() {

			List<NecronomiconCreationRitual> temp = new ArrayList<NecronomiconCreationRitual>();
			for(NecronomiconRitual ritual : RitualRegistry.instance().getRituals()){
				if(ritual instanceof NecronomiconCreationRitual &&
						ritual.getClass().getSuperclass() != NecronomiconCreationRitual.class &&
						ritual.getClass().getSuperclass().getSuperclass() != NecronomiconCreationRitual.class)
					temp.add((NecronomiconCreationRitual) ritual);
			}
			for(NecronomiconCreationRitual ritual : temp){
				if(ritual.getItem() == item){
					removedRituals.add(ritual);
					RitualRegistry.instance().getRituals().remove(ritual);
				}
			}
		}

		@Override
		public boolean canUndo() {

			return true;
		}

		@Override
		public String describe() {

			return "Removing Necronomicon Creation Ritual for "+ item.getDisplayName();
		}

		@Override
		public String describeUndo() {

			return "Re-Adding Necronomicon Creation Ritual for "+ item.getDisplayName();
		}

		@Override
		public Object getOverrideKey() {

			return null;
		}

		@Override
		public void undo() {

			if(removedRituals != null)
				for(NecronomiconCreationRitual ritual : removedRituals)
					if(ritual != null)
						RitualRegistry.instance().registerRitual(ritual);
		}
	}
}