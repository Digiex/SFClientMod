package net.minecraft.src;

import java.util.Date;

import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.input.Mouse;

public class GuiSFMailSlot extends GuiSlot {

	final GuiSFMailbox parentMailGui;
	private int heightofthis;

	public GuiSFMailSlot(GuiSFMailbox guiSFMailbox) {
		super(guiSFMailbox.mc, guiSFMailbox.width, guiSFMailbox.height, 32,
				guiSFMailbox.height - 64, 36);
		parentMailGui = guiSFMailbox;
	}

	protected int getSize() {
		return GuiSFMailbox.getSize(parentMailGui).size();
	}

	protected void elementClicked(int i, boolean flag) {
		GuiSFMailbox.onElementSelected(parentMailGui, i);
		boolean flag1 = GuiSFMailbox.getSelectedMail(parentMailGui) >= 0
				&& GuiSFMailbox.getSelectedMail(parentMailGui) < getSize();
		GuiSFMailbox.getReplyButton(parentMailGui).enabled = flag1;
		GuiSFMailbox.getDeleteButton(parentMailGui).enabled = flag1;
		if (flag && flag1) {
			try {
				parentMailGui.mc.displayGuiScreen(new GuiSFNewMail(
						parentMailGui, ((JSONObject) GuiSFMailbox.getSize(
								parentMailGui).get(i)).getString("from")));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				parentMailGui.mc.displayGuiScreen(new GuiSFNewMail(
						parentMailGui));
			}
		}
	}

	protected boolean isSelected(int i) {
		return i == GuiSFMailbox.getSelectedMail(parentMailGui);
	}

	protected int getContentHeight() {
		return GuiSFMailbox.getSize(parentMailGui).size() * 36;
	}

	protected void drawBackground() {
		parentMailGui.drawDefaultBackground();
	}

	protected void drawSlot(int i, int j, int k, int l, Tessellator tessellator) {
		try {
			JSONObject mail = (JSONObject) GuiSFMailbox.getSize(parentMailGui)
					.get(i);
			String s = mail.getString("formattedmsg");
			String[] lines = s.split("\n");
			String s1 = mail.getString("from");
			s1 = (new StringBuilder())
					.append(s1)
					.append(" (")
					.append(GuiSFMailbox.getDateFormatter(parentMailGui)
							.format(new Date(mail.getLong("timestamp"))))
					.toString();
			s1 = (new StringBuilder()).append(s1).append(")").toString();
			int nextstr = k + 1;
			int msgid = 0;
			for (String line : lines) {
				msgid++;
				parentMailGui.drawString(parentMailGui.fontRenderer, line,
						j + 2, nextstr, 0xffffff);
				nextstr = nextstr + 10;
				if (msgid > 1) {
					break;
				}
			}
			parentMailGui.drawString(parentMailGui.fontRenderer, s1, j + 2,
					nextstr, 0x808080);
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
