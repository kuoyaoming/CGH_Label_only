package com.example.lable_only;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.navigation.fragment.NavHostFragment;

import java.security.PublicKey;

import static android.app.Activity.RESULT_OK;

public class MenuFragment extends Fragment {

    private static final int PICKFILE_RESULT_CODE = 1;
    private static final String TAG = "MenuFragment";
    private String PATH;
    private TextView textPath;

    @Override
    public View onCreateView(
            LayoutInflater inflater, ViewGroup container,
            Bundle savedInstanceState
    ) {
        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_menu, container, false);
        textPath = (TextView) view.findViewById(R.id.textPath);

        return inflater.inflate(R.layout.fragment_menu, container, false);
    }

    public void onViewCreated(@NonNull View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        view.findViewById(R.id.buttonOpen).setOnClickListener(view1 -> openFileChooser());

        view.findViewById(R.id.buttonLabel).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Bundle bundle = new Bundle();
                bundle.putString("edttext", "From Activity");
                // set Fragmentclass Arguments
                Fragmentclass fragobj = new Fragmentclass();
                fragobj.setArguments(bundle);
                NavHostFragment.findNavController(MenuFragment.this)
                        .navigate(R.id.action_MenuFragment_to_WorkspaceFragment);
            }
        });
    }

    private void openFileChooser(){
        Intent intent = new Intent(Intent.ACTION_GET_CONTENT);
        intent.setType("audio/*");
        startActivityForResult(intent,PICKFILE_RESULT_CODE);
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICKFILE_RESULT_CODE && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            PATH = data.getData().getPath();
            String[] bits = PATH.split("/");
            setText("Select PATH: " + PATH.substring(0, PATH.length() - bits[bits.length-1].length()));
        }
    }

    public void setText(String text){
        TextView textView = (TextView) getView().findViewById(R.id.textPath);
        textView.setText(text);
    }
}