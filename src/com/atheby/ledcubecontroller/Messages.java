package com.atheby.ledcubecontroller;

public interface Messages {
	public static int REQ_REFRESH_UI = 0x0001;
	public static int ACL_CONNECTED = 0x0002;
	public static int ACL_DISCONNECTED = 0x0003;
	public static int CON_SOCKET_OPENED = 0x0103;
	public static int CON_SOCKET_CLOSED = 0x0104;
	public static int DATA_BEGIN = 0x0201;
	public static int DATA_END = 0x0202;
	public static int DATA_RETRANSMIT = 0x0203;
	public static int DATA_CONFIRM = 0x0204;
	public static int CMD_TURN_ON = 0x0301;
	public static int CMD_TURN_OFF = 0x0302;
	public static int CMD_BRIGHTNESS = 0x0303;
	public static int CMD_SLEEP = 0x0304;
}
