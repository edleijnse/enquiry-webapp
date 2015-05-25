package com.example;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.nio.charset.StandardCharsets;
import java.util.Locale;
import java.util.ResourceBundle;

/**
 * Hello world!
 *
 */
public class Enquiry {

    private static String enquiryHeaderLocation;
    private static String labelQuestionary;
    private static String labelQuestionId;
    private static String labelQuestion;
    private static String labelAnswerId;
    private static String labelAnswer;

    public String getEnquiryFragment(String iFragment) throws IOException {
        String myHeader;
        myHeader = "";
        InputStream in = this.getClass().getClassLoader().getResourceAsStream(iFragment);
        BufferedReader br = new BufferedReader(new InputStreamReader(in, StandardCharsets.UTF_8));

        try {
            StringBuilder sb = new StringBuilder();
            myHeader = br.readLine();

            while (myHeader != null) {
                sb.append(myHeader);
                sb.append("\n");
                myHeader = br.readLine();
            }
            return sb.toString();
        } catch (IOException e) {
        } finally {
            br.close();
        }
        return myHeader;
    }

    public String createEnquiry() throws IOException {

        Enquiry enquiry = new Enquiry();

			// https://docs.oracle.com/javase/tutorial/displayCode.html?code=https://docs.oracle.com/javase/tutorial/i18n/resbundle/examples/PropertiesDemo.java
        ResourceBundle labels = ResourceBundle.getBundle("enquiry",
                Locale.GERMAN);
        enquiryHeaderLocation = labels.getString("header");
        
        labelQuestionary = labels.getString("txtquestionary");
        labelQuestionId = labels.getString("txtquestionid");
        labelQuestion = labels.getString("txtquestion");
        labelAnswerId = labels.getString("txtanswerid");
        labelAnswer = labels.getString("txtanswer");

        String myDocHeader = enquiry.getEnquiryFragment("docheader.html");
        String myEnquiryPrepare = myDocHeader;
        myEnquiryPrepare += enquiry.getEnquiryFragment("enquiry_01_prolog.html");
        String myEnquiry = myEnquiryPrepare.replace("#labelQuestionary", labelQuestionary);

        String myEnquiryQuestion01Prolog01 = enquiry.getEnquiryFragment("enquiryquestion_01_prolog.html");
        String myEnquiryQuestion01Prolog02 = myEnquiryQuestion01Prolog01.replace("#labelQuestionId", labelQuestionId);
        String myEnquiryQuestion01Prolog03 = myEnquiryQuestion01Prolog02.replace("#labelQuestion", labelQuestion);
        String myEnquiryQuestion01Prolog04 = myEnquiryQuestion01Prolog03.replace("#labelAnswerId", labelAnswerId);
        String myEnquiryQuestion01Prolog05 = myEnquiryQuestion01Prolog04.replace("#labelAnswer", labelAnswer);
        myEnquiry += myEnquiryQuestion01Prolog05;

        String myQuestion1 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html");
        String myNewstring1_1 = myQuestion1.replaceAll("#questionId", "1");
        String myNewstring1_2 = myNewstring1_1.replaceAll("#questionDescription", "Wieviele Zigaretten rauchen Sie?");
        String myNewstring1_3 = myNewstring1_2.replaceAll("#answerId", "Anzahl:");
        
        myEnquiry += myNewstring1_3;

        String myQuestion2 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html");
        myEnquiry += myQuestion2;

        String myQuestion3 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html");
        myEnquiry += myQuestion3;
        for (int i = 0; i < 100; i++) {
            String myQuestion4 = enquiry.getEnquiryFragment("enquiryquestion_02_body.html");
            String myNewstring = myQuestion4.replaceAll("#questionId", Integer.toString(i + 4));
            String myNewstring2 = myNewstring.replaceAll("#questionDescription", "Frage " + Integer.toString(i + 4));
            myEnquiry += myNewstring2;
        }

        myEnquiry += enquiry.getEnquiryFragment("enquiry_03_epilog.html");

        return myEnquiry;
    }

    public static void main(String[] args) {

        try {
            Enquiry enquiry = new Enquiry();
            System.out.println(enquiry.createEnquiry());


        } catch (Exception e) {

            e.printStackTrace();
        }

    }
}
