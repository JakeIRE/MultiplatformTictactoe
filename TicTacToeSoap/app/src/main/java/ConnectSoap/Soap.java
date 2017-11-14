package ConnectSoap;

import org.ksoap2.SoapEnvelope;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class Soap{

<<<<<<< HEAD
    private final String NAMESPACE ="http://localhost:8080/"+ "TicTacToeWebClient/TicTacToeWebService?wsdl";
    private final String URL = "http://www.webservicex.net/CurrencyConvertor.asmx";
    private final String SOAP_ACTION = "http://www.webserviceX.NET/ConversionRate";
    private final String METHOD_NAME = "ConversionRate";
=======
>>>>>>> 0546907b554aecff65896853329692f00ab7492a

    private static final String METHOD_NAME = "loginVerify";
    private static final String NAMESPACE = "http://tttWebData/";
    private static final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
    private static final String URL = "http:/localhost/TicTacToeWebClient/TicTacToeWebService?wsdl";

    public String loginVerify(String uname, String pass){
                SoapObject request = new SoapObject(NAMESPACE, "loginVerify");
                request.addProperty("uname", uname);
                request.addProperty("pass", pass);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE ht = new HttpTransportSE(URL);
                try {
                    ht.call(SOAP_ACTION, envelope);
                    SoapPrimitive response = (SoapPrimitive)envelope.getResponse();
                    return response.toString();

                } catch (Exception e) {
                    e.printStackTrace();
                }
                return "Error";
            }


                   /* fromProp.setName("FromCurrency");
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
                    //webResponse = response.toString();*/
}