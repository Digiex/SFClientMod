package net.minecraft.src;

import java.awt.List;
import java.util.ArrayList;

import net.minecraft.client.Minecraft;

import org.lwjgl.opengl.GL11;

public class GuiSFHelp extends GuiScreen {
	public GuiSFHelp(){
        statsTitle = "Help and Rules";
        selectedSlot = null;
		parentGui = null;
	}
	private static RenderItem renderItem = new RenderItem();
    protected GuiScreen parentGui;
    protected String statsTitle;
    private GuiSFHelpSlotRules slotRules;
    private GuiSFHelpSlotCommands slotCommands;
    private GuiSFHelpSlotWebsite slotWebsite;
    private GuiSlot selectedSlot;
    public static ArrayList<String> commands = new ArrayList<String>();
    public GuiSFHelp(GuiScreen guiscreen)
    {
        statsTitle = "Help and Rules";
        selectedSlot = null;
        parentGui = guiscreen;
    }

    public void initGui()
    {
        statsTitle = "Help and Rules";
        slotRules = new GuiSFHelpSlotRules(this);
        slotRules.registerScrollButtons(controlList, 1, 1);
        slotCommands = new GuiSFHelpSlotCommands(this);
        slotCommands.registerScrollButtons(controlList, 1, 1);
        slotWebsite = new GuiSFHelpSlotWebsite(this);
        slotWebsite.registerScrollButtons(controlList, 1, 1);
        selectedSlot = slotRules;
        addHeaderButtons();
        commands.add("help");
        commands.add("test");
        commands.add("tpa");
        commands.add("tpahere");
        commands.add("adm");
        commands.add("mail");
    }

    public void addHeaderButtons()
    {
        StringTranslate stringtranslate = StringTranslate.getInstance();
        controlList.add(new GuiButton(0, width / 2 + 4, height - 28, 150, 20, stringtranslate.translateKey("gui.done")));
        controlList.add(new GuiButton(1, width / 2 - 154, height - 52, 100, 20, stringtranslate.translateKey("stat.generalButton")));
        GuiButton guibutton;
        controlList.add(guibutton = new GuiButton(2, width / 2 - 46, height - 52, 100, 20, stringtranslate.translateKey("stat.blocksButton")));
        GuiButton guibutton1;
        controlList.add(guibutton1 = new GuiButton(3, width / 2 + 62, height - 52, 100, 20, stringtranslate.translateKey("stat.itemsButton")));
        if (slotWebsite.getSize() == 0)
        {
            guibutton.enabled = false;
        }
        if (slotCommands.getSize() == 0)
        {
            guibutton1.enabled = false;
        }
    }

    protected void actionPerformed(GuiButton guibutton)
    {
        if (!guibutton.enabled)
        {
            return;
        }
        if (guibutton.id == 0)
        {
            mc.displayGuiScreen(parentGui);
        }
        else if (guibutton.id == 1)
        {
            selectedSlot = slotRules;
        }
        else if (guibutton.id == 3)
        {
            selectedSlot = slotCommands;
        }
        else if (guibutton.id == 2)
        {
            selectedSlot = slotWebsite;
        }
        else
        {
            selectedSlot.actionPerformed(guibutton);
        }
    }

    public void drawScreen(int i, int j, float f)
    {
        selectedSlot.drawScreen(i, j, f);
        drawCenteredString(fontRenderer, statsTitle, width / 2, 20, 0xffffff);
        super.drawScreen(i, j, f);
    }

    private void drawItemSprite(int i, int j, int k)
    {
        drawButtonBackground(i + 1, j + 1);
        GL11.glEnable(32826 /*GL_RESCALE_NORMAL_EXT*/);
        RenderHelper.func_41089_c();
        renderItem.drawItemIntoGui(fontRenderer, mc.renderEngine, k, 0, Item.itemsList[k].getIconFromDamage(0), i + 2, j + 2);
        RenderHelper.disableStandardItemLighting();
        GL11.glDisable(32826 /*GL_RESCALE_NORMAL_EXT*/);
    }

    private void drawButtonBackground(int i, int j)
    {
        drawSprite(i, j, 0, 0);
    }

    private void drawSprite(int i, int j, int k, int l)
    {
        int i1 = mc.renderEngine.getTexture("/gui/slot.png");
        GL11.glColor4f(1.0F, 1.0F, 1.0F, 1.0F);
        mc.renderEngine.bindTexture(i1);
        Tessellator tessellator = Tessellator.instance;
        tessellator.startDrawingQuads();
        tessellator.addVertexWithUV(i + 0, j + 18, zLevel, (float)(k + 0) * 0.0078125F, (float)(l + 18) * 0.0078125F);
        tessellator.addVertexWithUV(i + 18, j + 18, zLevel, (float)(k + 18) * 0.0078125F, (float)(l + 18) * 0.0078125F);
        tessellator.addVertexWithUV(i + 18, j + 0, zLevel, (float)(k + 18) * 0.0078125F, (float)(l + 0) * 0.0078125F);
        tessellator.addVertexWithUV(i + 0, j + 0, zLevel, (float)(k + 0) * 0.0078125F, (float)(l + 0) * 0.0078125F);
        tessellator.draw();
    }

    static Minecraft getMinecraft(GuiSFHelp guisfhelp)
    {
        return guisfhelp.mc;
    }

    static FontRenderer getFontRenderer1(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer2(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer3(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static Minecraft getMinecraft1(GuiSFHelp guisfhelp)
    {
        return guisfhelp.mc;
    }

    static void drawSprite(GuiSFHelp guisfhelp, int i, int j, int k, int l)
    {
        guisfhelp.drawSprite(i, j, k, l);
    }

    static Minecraft getMinecraft2(GuiSFHelp guisfhelp)
    {
        return guisfhelp.mc;
    }

    static FontRenderer getFontRenderer4(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer5(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer6(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer7(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer8(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static void drawGradientRect(GuiSFHelp guisfhelp, int i, int j, int k, int l, int i1, int j1)
    {
        guisfhelp.drawGradientRect(i, j, k, l, i1, j1);
    }

    static FontRenderer getFontRenderer9(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static FontRenderer getFontRenderer10(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static void drawGradientRect1(GuiSFHelp guisfhelp, int i, int j, int k, int l, int i1, int j1)
    {
        guisfhelp.drawGradientRect(i, j, k, l, i1, j1);
    }

    static FontRenderer getFontRenderer11(GuiSFHelp guisfhelp)
    {
        return guisfhelp.fontRenderer;
    }

    static void drawItemSprite(GuiSFHelp guisfhelp, int i, int j, int k)
    {
        guisfhelp.drawItemSprite(i, j, k);
    }
}
