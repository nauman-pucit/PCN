package com.TechScope.nauman.pakchinanews;


import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;


/**
 * A simple {@link Fragment} subclass.
 */
public class PrivacyPolicy extends Fragment {


    public PrivacyPolicy() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_privacy_policy, container, false);
        TextView TV  = (TextView) rootView.findViewById(R.id.TVPrivacy);
        String str = "pakchinanews.pk and its owners take a proactive approach to user privacy and ensure that the necessary steps are taken to protect the privacy of its users throughout their visiting experience. This website complies with all Pakistan & US national laws and requirements for user privacy.\n" +
                "\bUse of Cookies\b\n" +
                "This website uses cookies to better the users experience while visiting the website. Cookies are small files saved to the user’s computer hard drive that track, save and store information about the user’s interactions and usage of the website. This allows the website, through its server to provide the users with a tailored experience within this website.\n" +
                "Users are advised that if they wish to deny the use and saving of cookies from this website on to their computers hard drive they should take necessary steps within their web browsers security settings to block all cookies from this website and its external serving vendors. We also monitor our traffic to analyze visitor’s information, such as, but not limited to, operating system, browsers, screen resolutions and similar. To monitor our traffic we use Google Analytics and Facebook Pixel .which uses cookies to track visitor usage. The software will save a cookie to your computer’s hard drive in order to track and monitor your engagement and usage of the website, but will not store, save or collect personal information. You can read Google’s or Facebook privacy policy here for further information [ http://www.google.com/privacy.html , https://facebook.com/policy.php ].\n" +
                "Other cookies may be stored to your computer’s hard drive by external vendors when this website uses referral programs, sponsored links or adverts. Such cookies are used for conversion and referral tracking and typically expire after 30 days, though some may take longer.\n" +
                "\bContact & Communication\b\n" +
                "While using our Site, we may ask you to provide us with certain personally identifiable information that can be used to contact or identify you. Personally identifiable information may include, Email, Name, Cell Number.\n" +
                "\bLog Data\b\n" +
                "Like many site operators, we collect information that your browser sends whenever you visit our Site (“Log Data”).This Log Data may include information such as your computer’s Internet Protocol (“IP”) address, browser type, browser version, the pages of our Site that you visit, the time and date of your visit, the time spent on those pages and other statistics.In addition, we may use third party services such as Google Analytics and Facebook Pixel that collect, monitor and analyze Log data.\n" +
                "\bCommunications\b\n" +
                "We may use your Personal Information to contact you with newsletters, marketing or promotional materials and other information.\n" +
                "\bSecurity\b\n" +
                "The security of your Personal Information is important to us, but remembers that no method of transmission over the Internet, or method of electronic storage, is 100% secure. While we strive to use commercially acceptable means to protect your Personal Information, we cannot guarantee its absolute security\n" +
                "\bChanges to This Privacy Policy\b\n" +
                "This Privacy Policy is effective as of 8th august,2016 and will remain in effect except with respect to any changes in its provisions in the future, which will be in effect immediately after being posted on this page. We reserve the right to update or change our Privacy Policy at any time and you should check this Privacy Policy periodically. Your continued use of the Service after we post any modifications to the Privacy Policy on this page will constitute your acknowledgment of the modifications and your consent to abide and be bound by the modified Privacy Policy. If we make any material changes to this Privacy Policy, we will notify you either through the email address you have provided us, or by placing a prominent notice on our website.\n" +
                "\bContact Us\b\n" +
                "If you have any questions about this Privacy Policy. Please contact us.";


        TV.setText(str);

        // Inflate the layout for this fragment
        return rootView;
    }

}
