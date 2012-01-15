package net.minecraft.src;

import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;

public class GuiSFNewMail extends GuiScreen {
	    private GuiTextField mailto;
	    private GuiTextField mailtext;
	    private String to1 = "";
	    private GuiScreen guiscreen;
	    public GuiSFNewMail(GuiScreen guiscreen)
	    {
	    	this.guiscreen =guiscreen;
	    }
	    public GuiSFNewMail(GuiScreen guiscreen, String to)
	    {
	    	this.guiscreen =guiscreen;
	    	to1 = to;
	    }
	    public void updateScreen()
	    {
	        mailto.updateCursorCounter();
	        mailtext.updateCursorCounter();
	    }

	    public void initGui()
	    {
	        StringTranslate stringtranslate = StringTranslate.getInstance();
	        Keyboard.enableRepeatEvents(true);
	        controlList.clear();
	        controlList.add(new GuiButton(0, width / 2 - 100, height / 4 + 96 + 12, "Send!"));
	        controlList.add(new GuiButton(1, width / 2 - 100, height / 4 + 120 + 12, stringtranslate.translateKey("gui.cancel")));
	        mailto = new GuiTextField(this, fontRenderer, width / 2 - 100, 76, 200, 20, to1);
	        mailtext = new GuiTextField(this, fontRenderer, width / 2 - 100, 116, 200, 20, "");
	        if(to1.length() > 0){
	        	mailto.isFocused = false;
	        	mailtext.isFocused = true;
	        }else{
	        	mailto.isFocused = true;
	        	mailtext.isFocused = false;
	        }
	        ((GuiButton)controlList.get(0)).enabled = mailtext.getText().length() > 0 && mailtext.getText().length() > 0;
	    }

	    public void onGuiClosed()
	    {
	        Keyboard.enableRepeatEvents(false);
	    }

	    protected void actionPerformed(GuiButton guibutton)
	    {
	        if (!guibutton.enabled)
	        {
	            return;
	        }
	        if (guibutton.id == 1)
	        {
	        	mc.displayGuiScreen(guiscreen);
	        }
	        else if (guibutton.id == 0)
	        {
	            System.out.println("Send the message to "+mailto.getText()+" with body: "+mailtext.getText());
				Packet250CustomPayload packet = new Packet250CustomPayload();
				packet.field_44012_a = "simplefeatures";
				JSONObject msgjson = new JSONObject();
				try {
					msgjson.put("id", "sendmail");
					msgjson.put("to", mailto.getText());
					msgjson.put("mail", mailtext.getText());
				} catch (JSONException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				
				byte[] msg = msgjson.toString().getBytes();
				packet.field_44010_b = msg.length;
				packet.field_44011_c = msg;
				mc.getSendQueue().addToSendQueue(packet);
				mc.displayGuiScreen(null);
	        }
	    }

	    protected void keyTyped(char c, int i)
	    {
	        mailto.textboxKeyTyped(c, i);
	        mailtext.textboxKeyTyped(c, i);
	        if (c == '\t')
	        {
	            if (mailto.isFocused)
	            {
	                mailto.isFocused = false;
	                mailtext.isFocused = true;
	            }
	            else
	            {
	                mailto.isFocused = true;
	                mailtext.isFocused = false;
	            }
	        }
	        if (c == '\r')
	        {
	            actionPerformed((GuiButton)controlList.get(0));
	        }
	        ((GuiButton)controlList.get(0)).enabled = mailto.getText().length() > 0 && mailtext.getText().length() > 0;
	        if (((GuiButton)controlList.get(0)).enabled)
	        {
	            String s = mailtext.getText().trim();
	            String as[] = s.split(":");
	            if (as.length > 2)
	            {
	                ((GuiButton)controlList.get(0)).enabled = false;
	            }
	        }
	    }

	    protected void mouseClicked(int i, int j, int k)
	    {
	        super.mouseClicked(i, j, k);
	        mailtext.mouseClicked(i, j, k);
	        mailto.mouseClicked(i, j, k);
	    }

	    public void drawScreen(int i, int j, float f)
	    {
	        StringTranslate stringtranslate = StringTranslate.getInstance();
	        drawDefaultBackground();
	        drawCenteredString(fontRenderer, "Send mail", width / 2, (height / 4 - 60) + 20, 0xffffff);
	        drawString(fontRenderer, "To:", width / 2 - 100, 63, 0xa0a0a0);
	        drawString(fontRenderer, "Mail:", width / 2 - 100, 104, 0xa0a0a0);
	        mailto.drawTextBox();
	        mailtext.drawTextBox();
	        super.drawScreen(i, j, f);
	    }
}
