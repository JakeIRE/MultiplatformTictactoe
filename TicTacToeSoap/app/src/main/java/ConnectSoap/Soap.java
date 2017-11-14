package ConnectSoap;

import org.ksoap2.SoapEnvelope;

import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class Soap{
    

    private static final String METHOD_NAME = "setOffline";
    private static final String NAMESPACE = "http://tttWebData/";
    private static final String SOAP_ACTION = "http://tttWebData/setOffline";
    private static final String URL = "http:/10.0.2.2:8080/TicTacToeWebClient/TicTacToeWebService?wsdl";
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

            public void setOffline(final String uname){
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                        request.addProperty("u", uname);

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                        envelope.setOutputSoapObject(request);
                        HttpTransportSE ht = new HttpTransportSE(URL);
                        try {
                            ht.call(SOAP_ACTION, envelope);

                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();

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