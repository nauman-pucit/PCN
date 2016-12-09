package com.TechScope.nauman.pakchinanews;


import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;


/**
 * A simple {@link Fragment} subclass.
 */
public class ContactUs extends Fragment implements View.OnClickListener{

    private String Name,Email,Phone,Subject,Message;

    public ContactUs() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_contact_us, container, false);
        EditText name = (EditText) rootView.findViewById(R.id.full_name);
        EditText email = (EditText)rootView.findViewById(R.id.email);
        EditText phone = (EditText)rootView.findViewById(R.id.phone);
        EditText subject = (EditText)rootView.findViewById(R.id.subject);
        EditText message = (EditText)rootView.findViewById(R.id.message);

        Name=name.getText().toString();
        Phone=phone.getText().toString();
        Email=email.getText().toString();
        Subject=subject.getText().toString();
        Message=message.getText().toString();

        return rootView;
    }
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.submitMessage:
                sendEmail();
                break;

        }

    }

    private void sendEmail() {
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setData(Uri.parse("mailto:"));
        String to = "bitf12m017@pucit.edu.pk";
        intent.putExtra(Intent.EXTRA_EMAIL,to);
        intent.putExtra(Intent.EXTRA_SUBJECT,Subject);
        intent.putExtra(Intent.EXTRA_TEXT,Name+"\n"+Phone+"\n"+Email+"\n"+Message);
        intent.setType("message/rfc822");
        Intent chooser = Intent.createChooser(intent, "Send Email");
        startActivity(chooser);

    }
}
