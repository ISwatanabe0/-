package com.winc.sake.dto;

public class ClientInfo {
	private int clientId;
	private String clientName;
	/**
	 * @return clientId
	 */
	public int getClientId() {
		return clientId;
	}

	/**
	 * @param clientId セットする clientId
	 */
	public void setClientId(int clientId) {
		this.clientId = clientId;
	}

	/**
	 * @return clientName
	 */
	public String getClientName() {
		return clientName;
	}

	/**
	 * @param clientName セットする clientName
	 */
	public void setClientName(String clientName) {
		this.clientName = clientName;
	}

	/**
	 * @return phoneNumber
	 */
	public String getPhoneNumber() {
		return phoneNumber;
	}

	/**
	 * @param phoneNumber セットする phoneNumber
	 */
	public void setPhoneNumber(String phoneNumber) {
		this.phoneNumber = phoneNumber;
	}

	private String phoneNumber;
	
	public ClientInfo() {}
	
	public ClientInfo(int clientId,String clientName,String phoneNumber) {
		this.clientId = clientId;
		this.clientName = clientName;
        this.phoneNumber = phoneNumber;
	}
	
	
}
