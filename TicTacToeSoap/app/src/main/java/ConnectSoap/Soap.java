package ConnectSoap;

import org.kobjects.util.Strings;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.PropertyInfo;
import org.ksoap2.serialization.SoapObject;
import org.ksoap2.serialization.SoapPrimitive;
import org.ksoap2.serialization.SoapSerializationEnvelope;
import org.ksoap2.transport.HttpTransportSE;


public class Soap{


    private static final String NAMESPACE = "http://tttWebData/";
    private static final String URL = "http:/10.0.2.2:8080/TicTacToeWebClient/TicTacToeWebService?wsdl";
    private String returner;
    private String[] str;

    public String loginVerify(final String uname, final String pass){
                final String METHOD_NAME = "loginVerify";
                final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {

                        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                        request.addProperty("uname", uname);
                        request.addProperty("pass", pass);

                        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                        envelope.setOutputSoapObject(request);

                        HttpTransportSE ht = new HttpTransportSE(URL);
                        try {
                            ht.call(SOAP_ACTION, envelope);
                            try {
                                SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                                returner = envelope.getResponse().toString();
                            }
                            catch(Exception e){
                                returner = "";
                            }

                        } catch (Exception e) {
                            e.printStackTrace();
                            returner = "Error";
                        }
                    }
                });
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                return returner;
            }

    public String register(final String uname, final String pass){
        final String METHOD_NAME = "register";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("uname", uname);
                request.addProperty("pass", pass);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE ht = new HttpTransportSE(URL);
                try {
                    ht.call(SOAP_ACTION, envelope);
                    try {
                        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                        returner = envelope.getResponse().toString();
                    }
                    catch(Exception e){
                        returner = "";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returner = "Error";
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returner;
    }

    public String[] getOptions(final String uname){
        final String METHOD_NAME = "getOptions";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {

                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("u", uname);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE ht = new HttpTransportSE(URL);
                try {
                    ht.call(SOAP_ACTION, envelope);
                    try {
                        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                        str = envelope.getResponse().toString().split(",");
                    }
                    catch(Exception e){
                        returner = "";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returner = "Error";
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return str;
    }
            public void setOffline(final String uname){
                final String METHOD_NAME = "setOffline";
                final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
                Thread t = new Thread(new Runnable() {
                    @Override
                    public void run() {
                        SoapObject request = new SoapObject(NAMESPACE, "setOffline");
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
                });
                t.start();
                try {
                    t.join();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }

    public void setOnline(final String uname){
        final String METHOD_NAME = "setOnline";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SoapObject request = new SoapObject(NAMESPACE, "setOffline");
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
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public String getJoiner(final String uname){
        final String METHOD_NAME = "getJoiner";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {


                SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);
                request.addProperty("u", uname);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);

                envelope.setOutputSoapObject(request);

                HttpTransportSE ht = new HttpTransportSE(URL);
                try {
                    ht.call(SOAP_ACTION, envelope);
                    try {
                        SoapPrimitive response = (SoapPrimitive) envelope.getResponse();
                        returner = envelope.getResponse().toString();
                    }
                    catch(Exception e){
                        returner = "";
                    }

                } catch (Exception e) {
                    e.printStackTrace();
                    returner = "Error";
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return returner;
    }

    public void beginGame(final String uname, final String joiner){
        final String METHOD_NAME = "beeginGame";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SoapObject request = new SoapObject(NAMESPACE, "setOffline");
                request.addProperty("uname", uname);
                request.addProperty("joiner", joiner);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE ht = new HttpTransportSE(URL);

                try {
                    ht.call(SOAP_ACTION, envelope);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
    public void denyGame(final String uname){
        final String METHOD_NAME = "denyGame";
        final String SOAP_ACTION = NAMESPACE+"/"+METHOD_NAME;
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                SoapObject request = new SoapObject(NAMESPACE, "setOffline");
                request.addProperty("uname", uname);

                SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
                envelope.setOutputSoapObject(request);
                HttpTransportSE ht = new HttpTransportSE(URL);

                try {
                    ht.call(SOAP_ACTION, envelope);

                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
        t.start();
        try {
            t.join();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
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