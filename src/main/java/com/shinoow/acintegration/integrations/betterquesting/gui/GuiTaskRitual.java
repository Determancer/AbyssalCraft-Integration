//package com.shinoow.acintegration.integrations.betterquesting.gui;
//
//import net.minecraft.util.text.translation.I18n;
//
//import com.shinoow.acintegration.integrations.betterquesting.tasks.TaskRitual;
//
//import betterquesting.client.gui.GuiQuesting;
//import betterquesting.client.gui.misc.GuiEmbedded;
//import betterquesting.client.themes.ThemeRegistry;
//
//public class GuiTaskRitual extends GuiEmbedded {
//
//	TaskRitual task;
//
//	public GuiTaskRitual(TaskRitual task, GuiQuesting screen, int posX, int posY, int sizeX,
//			int sizeY) {
//		super(screen, posX, posY, sizeX, sizeY);
//		this.task = task;
//	}
//
//	@Override
//	public void drawGui(int mx, int my, float partialTick) {
//
//		screen.mc.fontRendererObj.drawString("Ritual to perform:", posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth("Ritual to Perform:")/2 - 10, posY + sizeY/2 - 17 + 2, ThemeRegistry.curTheme().textColor().getRGB());
//		screen.mc.fontRendererObj.drawString(I18n.translateToLocal("ac.ritual." + task.name), posX + sizeX/2 - screen.mc.fontRendererObj.getStringWidth(I18n.translateToLocal("ac.ritual." + task.name))/2 - 10, posY + sizeY/2 - 17 + 10, ThemeRegistry.curTheme().textColor().getRGB());
//	}
//}