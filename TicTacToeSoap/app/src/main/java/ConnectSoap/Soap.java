package ConnectSoap;

import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class Soap{

    private final String NAMESPACE = "http://www.webserviceX.NET/";
    private final String URL = "http://www.webservicex.net/CurrencyConvertor.asmx";
    private final String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";
    private final String METHOD_NAME = "ConversionRate";

    private String webResponse = "";



    public void startWebAccess(){
                    SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                    PropertyInfo fromProp =new PropertyInfo();
                    fromProp.setName("FromCurrency");
                    fromProp.setValue(fromCurrency);
                    fromProp.setType(String.class);
                    request.addProperty(fromProp);

                    PropertyInfo toProp =new PropertyInfo();
                    toProp.setName("ToCurrency");
                    toProp.setValue(toCurrency);
                    toProp.setType(String.class);
                    request.addProperty(toProp);
                    if(true){

                    }

                    SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                    envelope.dotNet = true;
                    envelope.setOutputSoapObject(request);
                    HttpTransportSE androidHttpTransport = new HttpTransportSE(URL);

                    //androidHttpTransport.call(SOAP_ACTION, envelope);
                    //SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                    //webResponse = response.toString();
            }
}