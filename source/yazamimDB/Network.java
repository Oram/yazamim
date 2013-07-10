package yazamimDB;

public class Network {

	private int networkId;
	private String networkName;

	public Network(int networkId, String networkName) {
		this.networkId = networkId;
		this.networkName = networkName;
	}

	public int getNetworkId() {
		return networkId;
	}

	public void setNetworkId(int networkId) {
		this.networkId = networkId;
	}

	public String getNetworkName() {
		return networkName;
	}

	public void setNetworkName(String networkName) {
		this.networkName = networkName;
	}

	@Override
	public String toString() {
		return "Network [networkId=" + networkId + ", networkName="
				+ networkName + "]";
	}

}
