import com.bigid.connectors.api.DataSourceConnection;
import com.bigid.scanner.AhaClient;
import com.bigid.scanner.properties.AhaProperties;
import org.junit.Before;
import org.junit.Test;


public class ApiTest {

    AhaClient ahaClient;

    @Before
    public void before() {
        DataSourceConnection dataSourceConnection = new DataSourceConnection();
        dataSourceConnection.set("subdomain", "epam-1");
        AhaProperties ahaProperties = new AhaProperties(dataSourceConnection);
        ahaClient = new AhaClient(ahaProperties);
    }


    @Test
    public void test() {
        ahaClient.setProperty("path", "features");
        

    }

}


