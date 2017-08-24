package com.zenchn.vo;

public class MsgHeader {
	/// <summary>
    /// 消息ID
    /// </summary>
    public int msgId;

    /// <summary>
    /// 消息体属性  byte[2-3]
    /// </summary>
    public int msgBodyPropsField;

    /// <summary>
    /// 消息体长度
    /// </summary>
    public int msgBodyLength;

    /// <summary>
    /// 数据加密方式
    /// </summary>
    public int encryptionType;

    /// <summary>
    /// 是否分包,true==>有消息包封装项
    /// </summary>
    public boolean hasSubPackage;

    /// <summary>
    /// 保留位[14-15]
    /// </summary>
    public String reservedBit;

    /// <summary>
    /// 终端手机号
    /// </summary>
    public String terminalPhone;

    /// <summary>
    /// 流水号
    /// </summary>
    public int flowId;

    /// <summary>
    /// 消息包封装项 byte[12-15]
    /// </summary>
    public int packageInfoField;

    /// <summary>
    /// 消息包总数(word(16))
    /// </summary>
    public long totalSubPackage;

    /// <summary>
    /// 包序号(word(16))这次发送的这个消息包是分包中的第几个消息包, 从 1 开始
    /// </summary>
    public long subPackageSeq;

    /**消息ID**/
    public int getMsgId() {
		return msgId;
	}

	public void setMsgId(int msgId) {
		this.msgId = msgId;
	}

	public int getMsgBodyPropsField() {
		return msgBodyPropsField;
	}

	public void setMsgBodyPropsField(int msgBodyPropsField) {
		this.msgBodyPropsField = msgBodyPropsField;
	}

	public int getMsgBodyLength() {
		return msgBodyLength;
	}

	public void setMsgBodyLength(int msgBodyLength) {
		this.msgBodyLength = msgBodyLength;
	}

	public int getEncryptionType() {
		return encryptionType;
	}

	public void setEncryptionType(int encryptionType) {
		this.encryptionType = encryptionType;
	}

	public boolean isHasSubPackage() {
		return hasSubPackage;
	}

	public void setHasSubPackage(boolean hasSubPackage) {
		this.hasSubPackage = hasSubPackage;
	}

	public String getReservedBit() {
		return reservedBit;
	}

	public void setReservedBit(String reservedBit) {
		this.reservedBit = reservedBit;
	}

	/**终端手机号**/
	public String getTerminalPhone() {
		return terminalPhone;
	}

	public void setTerminalPhone(String terminalPhone) {
		this.terminalPhone = terminalPhone;
	}

	/**流水号**/
	public int getFlowId() {
		return flowId;
	}

	public void setFlowId(int flowId) {
		this.flowId = flowId;
	}

	public int getPackageInfoField() {
		return packageInfoField;
	}

	public void setPackageInfoField(int packageInfoField) {
		this.packageInfoField = packageInfoField;
	}

	public long getTotalSubPackage() {
		return totalSubPackage;
	}

	public void setTotalSubPackage(long totalSubPackage) {
		this.totalSubPackage = totalSubPackage;
	}

	public long getSubPackageSeq() {
		return subPackageSeq;
	}

	public void setSubPackageSeq(long subPackageSeq) {
		this.subPackageSeq = subPackageSeq;
	}

	public String toString()
    {
        return "MsgHeader [msgId=" + msgId + ", msgBodyPropsField=" + msgBodyPropsField + ", msgBodyLength="
                + msgBodyLength + ", encryptionType=" + encryptionType + ", hasSubPackage=" + hasSubPackage
                + ", reservedBit=" + reservedBit + ", terminalPhone=" + terminalPhone + ", flowId=" + flowId
                + ", packageInfoField=" + packageInfoField + ", totalSubPackage=" + totalSubPackage
                + ", subPackageSeq=" + subPackageSeq + "]";
    }
}
