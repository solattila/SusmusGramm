package com.solattila.susmusgramm;

import android.app.ProgressDialog;
import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;

import com.parse.ParseException;
import com.parse.ParseUser;
import com.parse.SaveCallback;
import com.shashank.sony.fancytoastlib.FancyToast;

import java.util.Objects;


/**
 * A simple {@link Fragment} subclass.
 */
public class ProfileTab extends Fragment {

    private EditText edtProfilename, edtProfileFest, edtProfileDrink, edtProfileFood, edtProfileJoke;
    private Button btnProfileUpdate;

    public ProfileTab() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_profile_tab, container, false);

        edtProfilename = view.findViewById(R.id.edtProfileName);
        edtProfileFest = view.findViewById(R.id.edtProfileFest);
        edtProfileDrink = view.findViewById(R.id.edtProfileDrink);
        edtProfileFood = view.findViewById(R.id.edtProfileFood);
        edtProfileJoke = view.findViewById(R.id.edtProfileJoke);

        btnProfileUpdate = view.findViewById(R.id.btnProfileUpdate);

        final ParseUser parseUser = ParseUser.getCurrentUser();

        if (parseUser.get("profileName") != null){
            edtProfilename.setText(Objects.requireNonNull(parseUser.get("profileName")+""));

        }
        if (parseUser.get("profileFest") != null){
            edtProfileFest.setText(Objects.requireNonNull(parseUser.get("profileFest")+""));

        }

        if (parseUser.get("profileDrink") != null){
            edtProfileDrink.setText(Objects.requireNonNull(parseUser.get("profileDrink")+""));

        }

        if (parseUser.get("profileFood") != null){
            edtProfileFood.setText(Objects.requireNonNull(parseUser.get("profileFood")+""));

        }

        if (parseUser.get("profileJoke") != null){
            edtProfileJoke.setText(Objects.requireNonNull(parseUser.get("profileJoke")+""));

        }


        btnProfileUpdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                parseUser.put("profileName", edtProfilename.getText().toString());
                parseUser.put("profileFest", edtProfileFest.getText().toString());
                parseUser.put("profileDrink", edtProfileDrink.getText().toString());
                parseUser.put("profileFood", edtProfileFood.getText().toString());
                parseUser.put("profileJoke", edtProfileJoke.getText().toString());

                final ProgressDialog progressDialog = new ProgressDialog(getContext());
                progressDialog.setMessage("Profil frissítése folyamatban");
                progressDialog.show();


                parseUser.saveInBackground(new SaveCallback() {
                    @Override
                    public void done(ParseException e) {



                        if (e == null){
                            FancyToast.makeText(getContext(), "Frissítés sikeres",
                                    FancyToast.LENGTH_SHORT, FancyToast.INFO, false).show();
                        }else {
                            FancyToast.makeText(getContext(), e.getMessage(),
                                    FancyToast.LENGTH_SHORT, FancyToast.ERROR, false).show();
                        }

                        progressDialog.dismiss();

                    }
                });


            }
        });

        return view;

    }
}
