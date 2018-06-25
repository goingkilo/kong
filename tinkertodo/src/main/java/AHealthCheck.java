
import com.codahale.metrics.health.HealthCheck;

public class AHealthCheck extends HealthCheck {

    public AHealthCheck() {

    }

    @Override
    protected Result check() throws Exception {
        return Result.healthy();
    }

}