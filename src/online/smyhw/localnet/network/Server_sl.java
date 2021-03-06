package online.smyhw.localnet.network;

import java.net.Socket;

import online.smyhw.localnet.LN;
import online.smyhw.localnet.LNlib;
import online.smyhw.localnet.message;
import online.smyhw.localnet.lib.TCP_LK;

public class Server_sl extends TCP_LK
{
	public String ID;
	public Server_sl(Socket s)
	{
		super(s,2);//这里，调用父类构造方法
		try
		{
			byte[] temp = new byte[1024];
			s.getInputStream().read(temp);
			ID=new String(temp,"UTF-8");
			this.Smsg("&"+LN.ID);//发送自身ID

		}catch(Exception e){message.info("服务器\""+ID+"\"连接异常！丢弃线程");e.printStackTrace();return;}

	}
	public void CLmsg(String msg)
	{
		if(ID==null && !(msg.startsWith("&")))
		{message.info("此服务器尝试在未发送身份信息的情况下发送其他消息，不安全，断开连接！");}
		switch(msg.charAt(0))
		{
		case '&':
			msg=msg.replace('&', ' ');
			msg=msg.trim();
			if(LNlib.ID_repeat(msg)) 
			{
				message.show("服务器ID重复（与本机?）");
				return;
			}
			if(!LNlib.ID_rightful(msg))
			{
				message.show("服务器使用了不合法的ID!");
				return;
			}
			this.ID=msg;
			break;
		default:
			//我tm看谁控制台分辨率这么高????
			System.out.print("\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n\n");
			message.show(msg);
			break;
		}
	}
	public void Serr_u()
	{

		StackTraceElement[] temp=Thread.currentThread().getStackTrace();
		StackTraceElement temp2=(StackTraceElement)temp[5];
		message.warning("连接到服务器出错，丢弃连接，位置："+temp2.getFileName()+":"+temp2.getClassName()+":"+temp2.getMethodName()+":"+temp2.getLineNumber());
		return;
	}
}


