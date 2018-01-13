package cn.edu.zucc.elves.model.user;

public class UserView extends User{
	private long lastUpdateTime;

	public long getLastUpdateTime() {
		return lastUpdateTime;
	}

	public void setLastUpdateTime(long lastUpdateTime) {
		this.lastUpdateTime = lastUpdateTime;
	}
}
