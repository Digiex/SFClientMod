package net.minecraft.src;

import java.util.List;

import org.lwjgl.opengl.GL11;

import net.minecraft.client.Minecraft;

public class GuiSFQuestion extends GuiScreen {

	private String message1;
	private String message2;
	private String buttonText1;
	private String buttonText2;
	private String buttonCmd1;
	private String buttonCmd2;
	private GuiSmallButton btn1;
	private GuiSmallButton btn2;
	public GuiSFQuestion(String message1, String message2, String button1text,
			String button2text, String button1command, String button2command) {
		this.message1 = message1;
		this.message2 = message2;
		buttonText1 = button1text;
		buttonText2 = button2text;
		buttonCmd1 = button1command;
		buttonCmd2 = button2command;
	}

	public void initGui() {
		controlList.add(btn1=new GuiSmallButton(0, (width / 2 - 155) + 0,
				height / 6 + 96, buttonText1));
		controlList.add(btn2=new GuiSmallButton(1, (width / 2 - 155) + 160,
				height / 6 + 96, buttonText2));
	}

	protected void actionPerformed(GuiButton guibutton) {
		switch (guibutton.id) {
		case 0:
			mc.thePlayer.sendChatMessage("/" + buttonCmd1);
			break;
		case 1:
			mc.thePlayer.sendChatMessage("/" + buttonCmd2);
			break;
		}

		mc.displayGuiScreen(null);
		mc.setIngameFocus();
	}
	public void setMsg1(String msg1){
		this.message1 = msg1;
		this.updateScreen();
	}
	public void setMsg2(String msg2){
		this.message2 = msg2;
		this.updateScreen();
	}
	public void setBtn1(String btn1){
		this.buttonText1 = btn1;
		this.btn1.displayString =btn1;
		this.updateScreen();
	}
	public void setBtn2(String btn2){
		this.buttonText2 = btn2;
		this.btn2.displayString =btn2;
		this.updateScreen();
	}
	public void setBtn1Cmd(String btn1cmd){
		this.buttonCmd1 = btn1cmd;
	}
	public void setBtn2Cmd(String btn2cmd){
		this.buttonCmd2 = btn2cmd;
	}
	public void drawScreen(int i, int j, float f) {
		drawDefaultBackground();
		drawCenteredString(fontRenderer, message1, width / 2, 70, 0xffffff);
		drawCenteredString(fontRenderer, message2, width / 2, 90, 0xffffff);
		super.drawScreen(i, j, f);
	}

	public boolean doesGuiPauseGame() {
		return false;
	}
}
