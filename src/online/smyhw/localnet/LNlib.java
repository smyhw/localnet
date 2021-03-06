package online.smyhw.localnet;

import java.io.IOException;
import java.io.OutputStream;
import java.util.ArrayList;
import java.util.Iterator;

import online.smyhw.localnet.network.Client_sl;

public class LNlib 
{
	/**
	 * 初始化
	 */
	public static void call_back(){}
	
	/**
	 * 
	 * 将信息发送到所有连接到本地的客户端
	 * @param msg 要发送的信息
	 */
	public static void SendAll(String msg)
	{
		message.info("sendALL:"+msg);
		ArrayList<Client_sl> temp1 = (ArrayList<Client_sl>) LN.client_list.clone();
		Iterator<Client_sl> temp2 = temp1.iterator();
		while(temp2.hasNext())
		{
			Client_sl temp3 = temp2.next();
			message.info("SENDALL_ID:"+temp3.ID);
			temp3.Smsg(msg);
		}
	}
	
	/**
	 * 
	 * 检查传入的ID是否已经存在
	 * @param UserName
	 * @return 如果为true,则已存在
	 */
	public static boolean ID_repeat(String UserName)
	{
		ArrayList<Client_sl> temp1 = (ArrayList<Client_sl>) LN.client_list.clone();
		Iterator<Client_sl> temp2 = temp1.iterator();
		while(temp2.hasNext())
		{
			Client_sl temp3 = temp2.next();
			if(temp3.ID.equalsIgnoreCase(UserName)) {return true;}
		}
		return false;
	}
	
	/**
	 * 
	 * 检查出入的ID是否符合正则
	 * @param UserName
	 * @return 
	 */
	public static boolean ID_rightful(String UserName)
	{
		if(UserName.matches("^[A-Za-z0-9]+$")) {return true;}
		else {return false;}
	}
	
	/**
	 * 
	 * 检查输入的ID是否合法（是否重复，是否符合正则）
	 * @param UserName
	 * @return 为true则合法
	 */
	public static boolean ID_test(String UserName)
	{
		if(!ID_repeat(UserName) && ID_rightful(UserName)) {return true;}
		else {return false;}
	}
	
}

class lnStream extends OutputStream
{

	@Override
	public void write(byte[] b) throws IOException 
	{
		String Sdata;
		Sdata=new String(b,"UTF-8").trim();
		message.show(Sdata);
	}

	@Override
	public void write(int b) throws IOException 
	{
		message.warning("本地虚拟输出流的方法“write(int b)”被调用了，但是这个方法是空的，它不应该被调用！");	
	}

	
}
