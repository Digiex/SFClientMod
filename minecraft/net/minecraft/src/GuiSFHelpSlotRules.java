package net.minecraft.src;

import net.minecraft.client.Minecraft;

public class GuiSFHelpSlotRules extends GuiSlot {
    final GuiSFHelp field_27276_a;

    public GuiSFHelpSlotRules(GuiSFHelp guisfhelp)
    {
        super(GuiSFHelp.getMinecraft(guisfhelp), guisfhelp.width, guisfhelp.height, 32, guisfhelp.height - 64, 10);
        field_27276_a = guisfhelp;
        func_27258_a(false);
    }

    protected int getSize()
    {
        return StatList.generalStats.size();
    }

    protected void elementClicked(int i, boolean flag)
    {
    }

    protected boolean isSelected(int i)
    {
        return false;
    }

    protected int getContentHeight()
    {
        return getSize() * 10;
    }

    protected void drawBackground()
    {
        field_27276_a.drawDefaultBackground();
    }

    protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator)
    {
        StatBase statbase = (StatBase)StatList.generalStats.get(i);
        field_27276_a.drawString(GuiSFHelp.getFontRenderer1(field_27276_a), StatCollector.translateToLocal(statbase.func_44020_i()), j + 2, k + 1, i % 2 != 0 ? 0x909090 : 0xffffff);
        String s = "testing";
        field_27276_a.drawString(GuiSFHelp.getFontRenderer2(field_27276_a), s, (j + 2 + 213) - GuiSFHelp.getFontRenderer3(field_27276_a).getStringWidth(s), k + 1, i % 2 != 0 ? 0x909090 : 0xffffff);
    }

}
