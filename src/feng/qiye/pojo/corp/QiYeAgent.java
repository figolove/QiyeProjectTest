package feng.qiye.pojo.corp;

/**
 * 企业号应用类
 * @author sunlight
 *
 */
public class QiYeAgent {
	private String name;
	private String useridlist;
	private UserContainer allowusers;
	private PartyContainer allowpartys;
	private int close;
	private String description;
	private String mode;
	private String callbackurl;
	private String urltoken;
	private String redirectdomain;
	private String agentid;
	private String report_location_flag;
	private String SquareUrl;
	private String RoundUrl;

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUseridlist() {
		return useridlist;
	}

	public void setUseridlist(String useridlist) {
		this.useridlist = useridlist;
	}

	public int getClose() {
		return close;
	}

	public void setClose(int close) {
		this.close = close;
	}


	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getMode() {
		return mode;
	}

	public void setMode(String mode) {
		this.mode = mode;
	}

	public String getCallbackurl() {
		return callbackurl;
	}

	public void setCallbackurl(String callbackurl) {
		this.callbackurl = callbackurl;
	}

	public String getUrltoken() {
		return urltoken;
	}

	public void setUrltoken(String urltoken) {
		this.urltoken = urltoken;
	}

	public String getRedirectdomain() {
		return redirectdomain;
	}

	public void setRedirectdomain(String redirectdomain) {
		this.redirectdomain = redirectdomain;
	}

	public String getAgentid() {
		return agentid;
	}

	public void setAgentid(String agentid) {
		this.agentid = agentid;
	}

	public String getReport_location_flag() {
		return report_location_flag;
	}

	public void setReport_location_flag(String reportLocationFlag) {
		report_location_flag = reportLocationFlag;
	}

	public String getSquareUrl() {
		return SquareUrl;
	}

	public void setSquareUrl(String squareUrl) {
		SquareUrl = squareUrl;
	}

	public String getRoundUrl() {
		return RoundUrl;
	}

	public void setRoundUrl(String roundUrl) {
		RoundUrl = roundUrl;
	}

	public UserContainer getAllowusers() {
		return allowusers;
	}

	public void setAllowusers(UserContainer allowusers) {
		this.allowusers = allowusers;
	}

	public PartyContainer getAllowpartys() {
		return allowpartys;
	}

	public void setAllowpartys(PartyContainer allowpartys) {
		this.allowpartys = allowpartys;
	}

	@Override
	public String toString() {
		return "MdlAgent [RoundUrl=" + RoundUrl + ", SquareUrl=" + SquareUrl
				+ ", agentid=" + agentid  + ", callbackurl=" + callbackurl
				+ ", close=" + close + ", description=" + description
				+ ", mode=" + mode + ", name=" + name + ", redirectdomain="
				+ redirectdomain + ", report_location_flag="
				+ report_location_flag + ", urltoken=" + urltoken
				+ ", useridlist=" + useridlist + "]";
	}

}
