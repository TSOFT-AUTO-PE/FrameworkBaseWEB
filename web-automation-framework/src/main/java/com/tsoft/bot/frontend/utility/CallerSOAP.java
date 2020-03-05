/*@autor : Abraham Hernandez*/
package com.tsoft.bot.frontend.utility;

import com.tsoft.bot.frontend.exceptions.FrontEndException;
import org.w3c.dom.NodeList;

import javax.xml.soap.*;
import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;

public class CallerSOAP {

    public static String getValueTagXML(String getResponse, String getSpecificTag, int pos){

        String getVar1=null;

        try {

            String response = getResponse;

            InputStream inputStream = new ByteArrayInputStream(response.getBytes());

            SOAPMessage message = MessageFactory.newInstance().createMessage(null, inputStream);

            SOAPPart soapPart = message.getSOAPPart();

            SOAPEnvelope envelope = soapPart.getEnvelope();

            SOAPBody body = envelope.getBody();

            NodeList var1 = body.getElementsByTagNameNS("*", getSpecificTag);

            getVar1 = var1.item(pos).getChildNodes().item(0).getNodeValue();

            System.out.println(getVar1);

        }catch (Exception e){
            e.getMessage();
        }

        assert getVar1 != null;
        return getVar1;
    }

    public static String detailResponse(String urlExcel, String requestExcel) {

        StringBuffer response = null;

        try {

            String url = urlExcel;

            URL obj = new URL(url);

            long inicioDate = System.currentTimeMillis();

            HttpURLConnection con = (HttpURLConnection) obj.openConnection();

            con.setRequestMethod("POST");

//            con.setUseCaches (false);
//            con.setDefaultUseCaches (false);
            con.setRequestProperty("Content-Type", "text/xml; charset=UTF-8");

            con.setRequestProperty("Accept-Charset", "application/xml");


            String request = requestExcel;

            con.setDoOutput(true);

            DataOutputStream wr = new DataOutputStream(con.getOutputStream());

            wr.writeBytes(request);

            wr.flush();

            wr.close();

            int responseStatus = con.getResponseCode();

            if (responseStatus == 200) {
                System.out.println("Servicio culminó con éxito"  + " ==> " + "Estado : " +responseStatus);
            } else {

                if (responseStatus == 500) {
                    System.out.println("Error en el servicio"  + " ==> " + "Estado : " +responseStatus);
                }

                throw new FrontEndException("CR-001", "El servicio retornó estado: " + responseStatus);
            }

            BufferedReader in = new BufferedReader(new InputStreamReader(con.getInputStream()));

            String inputLine;

            response = new StringBuffer();

            while ((inputLine = in.readLine()) != null) { response.append(inputLine); }

            in.close();

            long finDate = System.currentTimeMillis();

            System.out.println("Tiempo de respuesta: " + ((finDate - inicioDate) / 1000.0) + " segs");

        } catch (Exception e) {
            System.out.println(e);
        }
        return response.toString();
    }

    public static String agregarComa(String responseValue){
        String verificar = responseValue.substring(responseValue.lastIndexOf(".") + 1);
        String subString;
        String result=null;
        if (Integer.parseInt(verificar)==0){
            result = responseValue.concat("0");
            int datend = responseValue.indexOf(".");
            if (datend != -1) {
                subString = responseValue.substring(0, datend);
                String decimales = responseValue.substring(responseValue.lastIndexOf(".") + 1);
                int validar = subString.length();
                switch (validar) {
                    case 1:
                        result = subString + "." + decimales.concat("0");;
                        break;
                    case 2:
                        result = subString + "." + decimales.concat("0");;
                        break;
                    case 3:
                        result = subString + "." + decimales.concat("0");;
                        break;
                    case 4:
                        String[] effective = subString.split("");
                        String primer = effective[0];
                        String primer2 = effective[1];
                        String primer3 = effective[2];
                        String primer4 = effective[3];
                        result = primer + "," + primer2 + primer3 + primer4 + "." + decimales.concat("0");;
                        break;
                    case 5:
                        String[] effectivee = subString.split("");
                        String primerr = effectivee[0];
                        String primerr2 = effectivee[1];
                        String primerr3 = effectivee[2];
                        String primerr4 = effectivee[3];
                        String primerr5 = effectivee[4];
                        result = primerr + primerr2 + "," + primerr3 + primerr4 + primerr5 + "." + decimales.concat("0");;
                        break;
                }
            }
        }else{
            int datend = responseValue.indexOf(".");
            if (datend != -1) {
                subString= responseValue.substring(0 , datend);
                String decimales = responseValue.substring(responseValue.lastIndexOf(".") + 1);
                int validar = subString.length();
                switch (validar){
                    case 1:
                        result = subString+"."+decimales;
                        break;
                    case 2:
                        result = subString+"."+decimales;
                        break;
                    case 3:
                        result = subString+"."+decimales;
                        break;
                    case 4:
                        String[] effective=subString.split("");
                        String primer = effective[0];
                        String primer2 = effective[1];
                        String primer3 = effective[2];
                        String primer4 = effective[3];
                        if (decimales.length()==1){
                            result = primer+","+ primer2+primer3+primer4+"."+decimales.concat("0");
                        }else {
                            result = primer + "," + primer2 + primer3 + primer4 + "." + decimales;
                        }
                        break;
                    case 5:
                        String[] effectivee=subString.split("");
                        String primerr = effectivee[0];
                        String primerr2 = effectivee[1];
                        String primerr3 = effectivee[2];
                        String primerr4 = effectivee[3];
                        String primerr5 = effectivee[4];
//                        if (decimales.length()==1){
//                            result = primerr5+","+ primerr2+primerr3+primerr4+"."+decimales.concat("0");
//                        }else {
                        result = primerr + "," + primerr2 + primerr3 + primerr4 + "." + decimales;

                        break;

                }
            }

        }
        return result;
    }

}
