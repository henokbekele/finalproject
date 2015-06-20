package projsde.rest.model;

import java.util.List;

import javax.xml.bind.annotation.XmlRootElement;


@XmlRootElement
public class HealthprofileGoalstatuscheckers {

	private List<HealthprofileGoalstatuschecker> healthprofileGoalstatuschecker;

	public List<HealthprofileGoalstatuschecker> getHealthprofileGoalstatuschecker() {
		return healthprofileGoalstatuschecker;
	}

	public void setHealthprofileGoalstatuschecker(
			List<HealthprofileGoalstatuschecker> healthprofileGoalstatuschecker) {
		this.healthprofileGoalstatuschecker = healthprofileGoalstatuschecker;
	}

	public HealthprofileGoalstatuscheckers(
			List<HealthprofileGoalstatuschecker> healthprofileGoalstatuschecker) {
		super();
		this.healthprofileGoalstatuschecker = healthprofileGoalstatuschecker;
	}

	public HealthprofileGoalstatuscheckers() {
		super();
	}

}
