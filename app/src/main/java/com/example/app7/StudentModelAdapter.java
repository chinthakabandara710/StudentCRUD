package com.example.app7;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.ArrayList;

public class StudentModelAdapter extends ArrayAdapter<StudentModel> {

    public StudentModelAdapter(@NonNull Context context, ArrayList<StudentModel> arrayList) {
        super(context, 0,arrayList);
    }


    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {

        // convertView which is recyclable view
        View currentItemView = convertView;

        // of the recyclable view is null then inflate the custom layout for the same
        if (currentItemView == null) {
            currentItemView = LayoutInflater.from(getContext()).inflate(R.layout.custom_list_view, parent, false);
        }

        // get the position of the view from the ArrayAdapter
        StudentModel currentNumberPosition = getItem(position);

        // then according to the position of the view assign the desired image for the same
        assert currentNumberPosition != null;

        // then according to the position of the view assign the desired TextView 1 for the same
        TextView textView1 = currentItemView.findViewById(R.id.textView1);
        String id = String.valueOf(currentNumberPosition.getId());
        textView1.setText(id);

        TextView textView2 = currentItemView.findViewById(R.id.textView2);
        textView2.setText(currentNumberPosition.getName());

        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView3 = currentItemView.findViewById(R.id.textView3);
        String age = String.valueOf(currentNumberPosition.getAge());
        textView3.setText(age);


        // then according to the position of the view assign the desired TextView 2 for the same
        TextView textView4 = currentItemView.findViewById(R.id.textView4);
        String isPass = currentNumberPosition.isPass() == true?"Pass":"Fail";
        textView4.setText(isPass);


        // then return the recyclable view
        return currentItemView;
    }

}
