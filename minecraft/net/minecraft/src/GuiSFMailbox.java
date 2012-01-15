package net.minecraft.src;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.lwjgl.input.Keyboard;

public class GuiSFMailbox extends GuiScreen {
	public JSONArray maillist = new JSONArray();
 public GuiSFMailbox(JSONArray maillist){
	 this.maillist=maillist;
     screenTitle = "Mailbox";
     selected = false;
 }
 private final DateFormat dateFormatter = new SimpleDateFormat();
 protected GuiScreen parentScreen;
 protected String screenTitle;
 private boolean selected;
 private int selectedMail;
 private List<JSONObject> mailList;
 private GuiSFMailSlot mailSlotContainer;
 private boolean deleting;
 private GuiButton buttonReply;
 private GuiButton buttonDelete;
 private GuiButton buttonClear;
 private GuiButton buttonClose;
 private GuiButton buttonNew;

 public void initGui()
 {
     screenTitle = "Mailbox";
     loadMails();
     mailSlotContainer = new GuiSFMailSlot(this);
     mailSlotContainer.registerScrollButtons(controlList, 4, 5);
     initButtons();
 }

 private void loadMails()
 {
     mailList = new ArrayList();
     for (int i = 0; i < maillist.length(); i++)
     {
             try {
				mailList.add((JSONObject) maillist.get(i));
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

     }
     selectedMail = -1;
 }


 public void initButtons()
 {
     StringTranslate stringtranslate = StringTranslate.getInstance();
     controlList.add(buttonReply = new GuiButton(1, width / 2 - 154, height - 52, 150, 20, "Reply"));
     controlList.add(buttonClear = new GuiButton(6, width / 2 - 154, height - 28, 70, 20, "Clear"));
     controlList.add(buttonDelete = new GuiButton(2, width / 2 - 74, height - 28, 70, 20, "Delete"));
     controlList.add(buttonNew = new GuiButton(3, width / 2 + 4, height - 52, 150, 20, "New Mail"));
     controlList.add(buttonClose = new GuiButton(0, width / 2 + 4, height - 28, 150, 20, stringtranslate.translateKey("gui.cancel")));
     buttonReply.enabled = false;
     buttonDelete.enabled = false;
 }

 protected void actionPerformed(GuiButton guibutton)
 {
     if (!guibutton.enabled)
     {
         return;
     }
     if (guibutton.id == 2)
     {
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.field_44012_a = "simplefeatures";
			JSONObject msgjson = new JSONObject();
			try {
				msgjson.put("id", "deletemail");
				msgjson.put("mailid", mailList.get(selectedMail).getInt("id"));
				mailList.remove(selectedMail);
				selectedMail = -1;
				buttonReply.enabled = false;
			    buttonDelete.enabled = false;
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] msg = msgjson.toString().getBytes();
			packet.field_44010_b = msg.length;
			packet.field_44011_c = msg;
			mc.getSendQueue().addToSendQueue(packet);
     }
     else if (guibutton.id == 1)
     {
    	try {
			mc.displayGuiScreen(new GuiSFNewMail(this,mailList.get(selectedMail).getString("from")));
		} catch (JSONException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			mc.displayGuiScreen(new GuiSFNewMail(this));
		}
     }
     else if (guibutton.id == 3)
     {
    	 mc.displayGuiScreen(new GuiSFNewMail(this));
     }
     else if (guibutton.id == 6)
     {
			Packet250CustomPayload packet = new Packet250CustomPayload();
			packet.field_44012_a = "simplefeatures";
			JSONObject msgjson = new JSONObject();
			try {
				msgjson.put("id", "clearmailbox");
				selectedMail = -1;
				buttonReply.enabled = false;
			    buttonDelete.enabled = false;
			    mailList.clear();
			} catch (JSONException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			byte[] msg = msgjson.toString().getBytes();
			packet.field_44010_b = msg.length;
			packet.field_44011_c = msg;
			mc.getSendQueue().addToSendQueue(packet);
     }
     else if (guibutton.id == 0)
     {
         mc.displayGuiScreen(null);
     }
     else
     {
         mailSlotContainer.actionPerformed(guibutton);
     }
 }



 public void drawScreen(int i, int j, float f)
 {
     mailSlotContainer.drawScreen(i, j, f);
     drawCenteredString(fontRenderer, screenTitle, width / 2, 20, 0xffffff);
     super.drawScreen(i, j, f);
 }

 static List<JSONObject> getSize(GuiSFMailbox guiselectmail)
 {
     return guiselectmail.mailList;
 }

 static int onElementSelected(GuiSFMailbox guiselectmail, int i)
 {
     return guiselectmail.selectedMail = i;
 }

 static int getSelectedMail(GuiSFMailbox guiselectmail)
 {
     return guiselectmail.selectedMail;
 }

 static GuiButton getReplyButton(GuiSFMailbox guiselectmail)
 {
     return guiselectmail.buttonReply;
 }

 static GuiButton getDeleteButton(GuiSFMailbox guiselectmail)
 {
     return guiselectmail.buttonDelete;
 }

 static DateFormat getDateFormatter(GuiSFMailbox guiselectmail)
 {
     return guiselectmail.dateFormatter;
 }

}