package test.tt;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import org.ksoap2.SoapEnvelope;
import org.ksoap2.serialization.*;
import org.ksoap2.transport.HttpTransportSE;

public class MainActivity extends Activity {
    /** Called when the activity is first created. */
 private final String NAMESPACE = "http://hiber/";
 private final String URL = "http://192.168.2.77:8080/WebServAndr1/clientAuthentWS?wsdl";
 private final String SOAP_ACTION = "http://hiber/clientauthent";
 private final String METHOD_NAME = "clientauthent";
 
    TextView tv;
    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.main);
        tv=(TextView) findViewById(R.id.TextView01);

        SoapObject request = new SoapObject(NAMESPACE, METHOD_NAME);           

     request.addProperty("loginclient", "client1");  
    request.addProperty("pwdclient", "client1pwd");       
        
        SoapSerializationEnvelope envelope = new SoapSerializationEnvelope(SoapEnvelope.VER11);
        envelope.dotNet=false;
        envelope.setOutputSoapObject(request);

        HttpTransportSE ht = new HttpTransportSE(URL);


        try{
            ht.call(SOAP_ACTION, envelope);
            //tv.setText("http set");
            
            SoapPrimitive sp= (SoapPrimitive) envelope.getResponse();
            tv.setText("Msg from service: "+sp);


        }
        catch(Exception e)
            
            
        {
             tv.setText("Msg from service: "+e);
            e.printStackTrace();
        }
    }
}