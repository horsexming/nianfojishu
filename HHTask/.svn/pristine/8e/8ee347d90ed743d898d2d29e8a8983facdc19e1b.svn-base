package com.task.test;

import java.io.IOException;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.InetAddress;

/**
 * 向指定的137端口发出固定格式的查询命令，返回结果中即含有客户端mac地址
 * 
 * @author Administrator
 * 
 */
public class UdpGetClientMac {
	private String sRemoteAddr;
	private int iRemotePort = 137;
	private byte[] buffer = new byte[1024];
	private DatagramSocket ds = null;

	public static void main(String[] args) {
		try {
			// nbtstat -a 192.168.1.120
			UdpGetClientMac mac = new UdpGetClientMac("192.168.0.114");
			String ma = mac.GetRemoteMacAddr();
			System.out.println(ma);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public UdpGetClientMac(String strAddr) throws Exception {
		sRemoteAddr = strAddr;
		ds = new DatagramSocket();
	}

	protected final DatagramPacket send(final byte[] bytes) throws IOException {
		DatagramPacket dp = new DatagramPacket(bytes, bytes.length, InetAddress
				.getByName(sRemoteAddr), iRemotePort);
		ds.send(dp);
		return dp;
	}

	protected final DatagramPacket receive() throws Exception {
		DatagramPacket dp = new DatagramPacket(buffer, buffer.length);
		ds.receive(dp);
		return dp;
	}

	// 询问包结构:
	// Transaction ID 两字节（16位） 0x00 0x00
	// Flags 两字节（16位） 0x00 0x10
	// Questions 两字节（16位） 0x00 0x01
	// AnswerRRs 两字节（16位） 0x00 0x00
	// AuthorityRRs 两字节（16位） 0x00 0x00
	// AdditionalRRs 两字节（16位） 0x00 0x00
	// Name:array [12..45] 0x20 0x43 0x4B 0x41(30个) 0x00 ;
	// Type:NBSTAT 两字节 0x00 0x21
	// Class:INET 两字节（16位）0x00 0x01
	protected byte[] GetQueryCmd() throws Exception {
		byte[] t_ns = new byte[50];
		t_ns[0] = 0x00;// transaction[0]
		t_ns[1] = 0x00;// transaction[1]
		t_ns[2] = 0x00;// flags[0]
		t_ns[3] = 0x10;// flags[1]
		t_ns[4] = 0x00;// questions[0]
		t_ns[5] = 0x01;// questions[1]
		t_ns[6] = 0x00;
		t_ns[7] = 0x00;
		t_ns[8] = 0x00;
		t_ns[9] = 0x00;
		t_ns[10] = 0x00;
		t_ns[11] = 0x00;
		t_ns[12] = 0x20;
		t_ns[13] = 0x43;
		t_ns[14] = 0x4B;

		for (int i = 15; i < 45; i++) {
			t_ns[i] = 0x41;
		}

		t_ns[45] = 0x00;
		t_ns[46] = 0x00;// nbstat
		t_ns[47] = 0x21;// nbstat
		t_ns[48] = 0x00;
		t_ns[49] = 0x01;
		return t_ns;
	}

	// 表1 “UDP－NetBIOS－NS”应答包的结构及主要字段一览表
	// 序号 字段名 长度
	// 1 Transaction ID 两字节（16位）
	// 2 Flags 两字节（16位）
	// 3 Questions 两字节（16位）
	// 4 AnswerRRs 两字节（16位）
	// 5 AuthorityRRs 两字节（16位）
	// 6 AdditionalRRs 两字节（16位）
	// 7 Name<Workstation/Redirector> 34字节（272位）
	// 8 Type:NBSTAT 两字节（16位）
	// 9 Class:INET 两字节（16位）
	// 10 Time To Live 四字节（32位）
	// 11 Length 两字节（16位）
	// 12 Number of name 一个字节（8位）,名字长度---56
	// NetBIOS Name Info 18×Number Of Name字节--占 这么多的字节
	// Unit ID 6字节（48位)-->接下来就是mac地址
	protected final String GetMacAddr(byte[] brevdata) throws Exception {
		int i = brevdata[56] * 18 + 56;
		String sAddr = "";
		StringBuffer sb = new StringBuffer(17);

		for (int j = 1; j < 7; j++) {
			sAddr = Integer.toHexString(0xFF & brevdata[i + j]);
			if (sAddr.length() < 2) {
				sb.append(0);
			}
			sb.append(sAddr.toUpperCase());
			if (j < 6) {
				sb.append(':');
			}
		}
		return sb.toString();
	}

	public final void close() {
		try {
			ds.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}

	public final String GetRemoteMacAddr() throws Exception {
		byte[] bqcmd = GetQueryCmd();
		send(bqcmd);
		DatagramPacket dp = receive();
		String smac = GetMacAddr(dp.getData());
		close();
		return smac;
	}
}
