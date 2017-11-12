
package SoapConnector;

import java.net.MalformedURLException;
import java.net.URL;
import javax.xml.namespace.QName;
import javax.xml.ws.Service;
import javax.xml.ws.WebEndpoint;
import javax.xml.ws.WebServiceClient;
import javax.xml.ws.WebServiceException;
import javax.xml.ws.WebServiceFeature;


/**
 * This class was generated by the JAX-WS RI.
 * JAX-WS RI 2.2.6-1b01 
 * Generated source version: 2.2
 * 
 */
@WebServiceClient(name = "TicTacToeWebService", targetNamespace = "http://tttWebData/", wsdlLocation = "http://localhost:8080/TicTacToeWebClient/TicTacToeWebService?wsdl")
public class TicTacToeWebService_Service
    extends Service
{

    private final static URL TICTACTOEWEBSERVICE_WSDL_LOCATION;
    private final static WebServiceException TICTACTOEWEBSERVICE_EXCEPTION;
    private final static QName TICTACTOEWEBSERVICE_QNAME = new QName("http://tttWebData/", "TicTacToeWebService");

    static {
        URL url = null;
        WebServiceException e = null;
        try {
            url = new URL("http://localhost:8080/TicTacToeWebClient/TicTacToeWebService?wsdl");
        } catch (MalformedURLException ex) {
            e = new WebServiceException(ex);
        }
        TICTACTOEWEBSERVICE_WSDL_LOCATION = url;
        TICTACTOEWEBSERVICE_EXCEPTION = e;
    }

    public TicTacToeWebService_Service() {
        super(__getWsdlLocation(), TICTACTOEWEBSERVICE_QNAME);
    }

    public TicTacToeWebService_Service(WebServiceFeature... features) {
        super(__getWsdlLocation(), TICTACTOEWEBSERVICE_QNAME, features);
    }

    public TicTacToeWebService_Service(URL wsdlLocation) {
        super(wsdlLocation, TICTACTOEWEBSERVICE_QNAME);
    }

    public TicTacToeWebService_Service(URL wsdlLocation, WebServiceFeature... features) {
        super(wsdlLocation, TICTACTOEWEBSERVICE_QNAME, features);
    }

    public TicTacToeWebService_Service(URL wsdlLocation, QName serviceName) {
        super(wsdlLocation, serviceName);
    }

    public TicTacToeWebService_Service(URL wsdlLocation, QName serviceName, WebServiceFeature... features) {
        super(wsdlLocation, serviceName, features);
    }

    /**
     * 
     * @return
     *     returns TicTacToeWebService
     */
    @WebEndpoint(name = "TicTacToeWebServicePort")
    public TicTacToeWebService getTicTacToeWebServicePort() {
        return super.getPort(new QName("http://tttWebData/", "TicTacToeWebServicePort"), TicTacToeWebService.class);
    }

    /**
     * 
     * @param features
     *     A list of {@link javax.xml.ws.WebServiceFeature} to configure on the proxy.  Supported features not in the <code>features</code> parameter will have their default values.
     * @return
     *     returns TicTacToeWebService
     */
    @WebEndpoint(name = "TicTacToeWebServicePort")
    public TicTacToeWebService getTicTacToeWebServicePort(WebServiceFeature... features) {
        return super.getPort(new QName("http://tttWebData/", "TicTacToeWebServicePort"), TicTacToeWebService.class, features);
    }

    private static URL __getWsdlLocation() {
        if (TICTACTOEWEBSERVICE_EXCEPTION!= null) {
            throw TICTACTOEWEBSERVICE_EXCEPTION;
        }
        return TICTACTOEWEBSERVICE_WSDL_LOCATION;
    }

}
