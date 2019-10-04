package com.hardcastle.sqlite;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListAdapter extends BaseAdapter {

    Context context;
    ArrayList<String> ID;
    ArrayList<String> Name;
    ArrayList<String> PhoneNumber;
    ArrayList<String> Register;
    ArrayList<String> Owner;
    ArrayList<String> Contact;


    public ListAdapter(
            Context context2,
            ArrayList<String> id,
            ArrayList<String> name,
            ArrayList<String> phone,
            ArrayList<String> register,
            ArrayList<String> owner,
            ArrayList<String> contact
    ) {

        this.context = context2;
        this.ID = id;
        this.Name = name;
        this.PhoneNumber = phone;
        this.Register = register;
        this.Owner = owner;
        this.Contact = contact;
    }

    public int getCount() {
        // TODO Auto-generated method stub
        return ID.size();
    }

    public Object getItem(int position) {
        // TODO Auto-generated method stub
        return null;
    }

    public long getItemId(int position) {
        // TODO Auto-generated method stub
        return 0;
    }

    public View getView(int position, View child, ViewGroup parent) {

        Holder holder;

        LayoutInflater layoutInflater;

        if (child == null) {
            layoutInflater = (LayoutInflater) context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);

            child = layoutInflater.inflate(R.layout.items, null);

            holder = new Holder();

            holder.ID_TextView = (TextView) child.findViewById(R.id.textViewID);
            holder.Name_TextView = (TextView) child.findViewById(R.id.textViewNAME);
            holder.PhoneNumberTextView = (TextView) child.findViewById(R.id.textViewPHONE_NUMBER);
            holder.Register_Text = (TextView) child.findViewById(R.id.textViewreg);
            holder.Owner_Text = (TextView) child.findViewById(R.id.textViewOwner);
            holder.Contact_Text = (TextView) child.findViewById(R.id.textViewContact);

            child.setTag(holder);

        } else {

            holder = (Holder) child.getTag();
        }
        holder.ID_TextView.setText(ID.get(position));
        holder.Name_TextView.setText(Name.get(position));
        holder.PhoneNumberTextView.setText(PhoneNumber.get(position));
        holder.Register_Text.setText(Register.get(position));
        holder.Owner_Text.setText(Owner.get(position));
        holder.Contact_Text.setText(Contact.get(position));

        return child;
    }

    public class Holder {

        TextView ID_TextView;
        TextView Name_TextView;
        TextView PhoneNumberTextView;
        TextView Register_Text;
        TextView Owner_Text;
        TextView Contact_Text;
    }

}
