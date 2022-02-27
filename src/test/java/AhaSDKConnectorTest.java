import com.bigid.connectors.api.DataSourceConnection;
import com.bigid.scanner.AhaConnector;
import com.bigid.scanner.AhaFileService;
import com.bigid.sdk.unstructured.tests.BasicUnstructuredConnectorTest;
import lombok.SneakyThrows;
import org.junit.Test;


public class AhaSDKConnectorTest extends BasicUnstructuredConnectorTest {

    @Override
    protected String getConnectorType() {
        return "aha!";
    }

    @Override
    public void setAdditionalDataSourceParameters(DataSourceConnection dataSourceConnection) {
        dataSourceConnection.set("subdomain", System.getenv().get("subdomain"));
        dataSourceConnection.set("partitionSize", "2 ");
    }

    @Test
    @SneakyThrows
    public void runTestsForAha() {
        String containerName = "features";
        AhaFileService ahaFileService = new AhaFileService();
        AhaConnector ahaConnector = new AhaConnector(ahaFileService);
        setMandatoryParameters(ahaConnector, "aha-demo", containerName, "");
        runAllTests();
    }
}
